package org.emilia.tienchin.service.impl;

import com.alibaba.fastjson2.support.csv.CSVWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.ibatis.executor.BatchResult;
import org.apache.poi.ss.formula.functions.T;
import org.emilia.tienchin.config.PageImpl;
import org.emilia.tienchin.controller.dto.user.*;
import org.emilia.tienchin.exception.ParamValidException;
import org.emilia.tienchin.mapper.*;
import org.emilia.tienchin.pojo.AjaxResult;
import org.emilia.tienchin.pojo.entity.SysDept;
import org.emilia.tienchin.pojo.entity.SysRole;
import org.emilia.tienchin.pojo.entity.SysUser;
import org.emilia.tienchin.pojo.sys.SysPost;
import org.emilia.tienchin.pojo.sys.SysUserPost;
import org.emilia.tienchin.pojo.sys.SysUserRole;
import org.emilia.tienchin.service.SysUserService;
import org.redisson.client.protocol.decoder.ObjectSetReplayDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.emilia.tienchin.exception.ParamValidException.buildParamException;


@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    private final SysUserMapper sysUserMapper;
    private final SysRoleMapper sysRoleMapper;
    private final SysPostMapper sysPostMapper;
    private final SysDeptMapper sysDeptMapper;
    private final SysUserRoleMapper userRoleMapper;
    private final SysUserPostMapper userPostMapper;
    private final HttpServletResponse response;
//    @Autowired(required = false)
//    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public SysUser findUser(String username) {
        return sysUserMapper.findUser(username);
    }

    @Override
    public void updateUserProfile(SysUser user) {
    }

    @Override
    public List<SysUser> selectUsers(ListSysUserReq user, PageImpl<SysUser> page) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        if (user != null) {
            if (user.getUserName() != null) {
                String userName = user.getUserName().replaceAll("^\\s+", "");
                queryWrapper.like("username", userName);
            }
            if (user.getPhonenumber() != null) {
                queryWrapper.like("phonenumber", user.getPhonenumber());
            }
            if (user.getStatus() != null) {
                queryWrapper.eq("status", user.getStatus());
            }
            if (user.getBeginTime() != null) {
                queryWrapper.ge("create_time", user.getBeginTime());
            }
            if (user.getEndTime() != null) {
                queryWrapper.le("create_time", user.getEndTime());
            }
        }
        return sysUserMapper.selectPage(page, queryWrapper).getRecords();
    }

    @Override
    public List<SysRole> selectRoles() {
        List<SysRole> sysRoles = sysRoleMapper.selectList(null);
        return sysRoles;
    }

    @Override
    public List<SysPost> selectPosts() {
        List<SysPost> posts = sysPostMapper.selectList(null);
        return posts;
    }

    @Override
    public SysUser selectByUserId(Long userId) {
        SysUser user = sysUserMapper.selectOne(new QueryWrapper<SysUser>().eq("user_id", userId));
        if (user == null) {
            throw buildParamException("userId", "不存在当前userId: " + userId);
        }
        Long deptId = user.getDeptId();
        SysDept sysDept = sysDeptMapper.selectByDeptId(deptId);
        user.setDept(sysDept);
        List<SysUserRole> list = userRoleMapper.selectList(new QueryWrapper<SysUserRole>().eq("user_id", userId));
        Long[] roleIds = list.stream().map(SysUserRole::getRoleId).toArray(Long[]::new);
        user.setRoleIds(roleIds);
        ArrayList<SysRole> roles = new ArrayList<>();
        for (Long roleId : roleIds) {
            SysRole sysRole = sysRoleMapper.selectById(roleId);
            roles.add(sysRole);
        }
        user.setRoles(roles);
        List<SysUserPost> userPost = userPostMapper.selectList(new QueryWrapper<SysUserPost>().eq("user_id", userId));
        Long[] posts = userPost.stream().map(SysUserPost::getPostId).toArray(Long[]::new);
        user.setPostIds(posts);
        return user;
    }

    @Override
    public boolean validateAdmin(Long userId) {
        List<SysUserRole> list = userRoleMapper.selectList(new QueryWrapper<SysUserRole>().eq("user_id", userId));
        List<Long> roleIds = list.stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
        return roleIds.contains(1L);
    }


    @Override
    @Transactional
    public AjaxResult add(AddSysUserReq user) {
        if (user.getUserName() != null) {
            validateUserNameNotExists(user.getUserName());
        }
        if (user.getPassword().length() < 6) {
            throw buildParamException("password", "密码长度不能少于6位");
        }
        if (user.getPhonenumber() != null) {
            validatePhonenumberNotExists(user.getPhonenumber());
        }
        if (user.getDeptId() != null) {
            validateDeptExists(user.getDeptId());
        }
        if (user.getRoleIds() != null) {
            validateRoleIds(user.getRoleIds());
        }
        if (user.getPostIds() != null) {
            validatePostIds(user.getPostIds());
        }
//        String encodePassword = passwordEncoder.encode(user.getPassword());
        SysUser sysUser = user.buildEntity();
        for (SysRole role : sysUser.getRoles()) {
            userRoleMapper.insert(new SysUserRole(sysUser.getUserId(), role.getRoleId()));
        }
        for (Long postId : sysUser.getPostIds()) {
            userPostMapper.insert(new SysUserPost(sysUser.getUserId(), postId));
        }
//        sysUser.setPassword(encodePassword);
        sysUser.setCreateTime(new Date(System.currentTimeMillis()));
        sysUserMapper.insert(sysUser);
        return AjaxResult.success();
    }

    @Override
    @Transactional
    public AjaxResult edit(EditSysUserReq user) {
        Long userId = user.getUserId();
        SysUser old = sysUserMapper.selectOne(new QueryWrapper<SysUser>().eq("user_id", userId));
        if (old == null) {
            throw buildParamException("userId ", "当前用户不存在" + userId);
        }
        if (user.getPhonenumber() != null) {
            validatePhonenumberNotExists(user.getPhonenumber());
        }
        if (user.getDeptId() != null) {
            validateDeptExists(user.getDeptId());
        }
        if (user.getRoleIds() != null) {
            validateRoleIds(user.getRoleIds());
        }
        if (user.getPostIds() != null) {
            validatePostIds(user.getPostIds());
        }
        SysUser sysUser = user.buildEntity();
        for (SysRole role : sysUser.getRoles()) {
            userRoleMapper.insert(new SysUserRole(sysUser.getUserId(), role.getRoleId()));
        }
        for (Long postId : sysUser.getPostIds()) {
            userPostMapper.insert(new SysUserPost(sysUser.getUserId(), postId));
        }
        sysUser.setUpdateTime(new Date(System.currentTimeMillis()));
        sysUserMapper.updateById(sysUser);
        return AjaxResult.success();
    }

    @Override
    public AjaxResult remove(Long[] userIds) {
        for (Long userId : userIds) {
            validateUserIdExists(userId);
        }
        for (Long userId : userIds) {
            sysUserMapper.delete(new QueryWrapper<SysUser>().eq("user_id", userId));
        }
        return AjaxResult.success();
    }

    @Override
    public AjaxResult resetPwd(Long userId, String password) {
        SysUser sysUser = validateUserIdExists(userId);
        sysUser.setPassword(password);
        sysUser.setUpdateTime(new Date(System.currentTimeMillis()));
        sysUserMapper.insertOrUpdate(sysUser);
        return AjaxResult.success();
    }

    @Override
    public AjaxResult changeStatus(ChangeStatusUserReq user) {
        Long userId = user.getUserId();
        SysUser sysUser = validateUserIdExists(userId);
        sysUser.setUpdateTime(new Date(System.currentTimeMillis()));
        sysUser.setStatus(user.getStatus());
        sysUserMapper.updateById(sysUser);
        return AjaxResult.success();
    }

    @Override
    public List<SysRole> authRole(Long userId) {
        List<SysRole> sysRoles = sysRoleMapper.selectList(null);
        List<SysRole> collect = sysRoles.stream().filter(r -> !r.getRoleId().equals(1L)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public AjaxResult insertAuthRole(Long userId, Long[] roleIds) {
        validateUserIdExists(userId);
        validateRoleIds(roleIds);
        userRoleMapper.delete(new QueryWrapper<SysUserRole>().eq("user_id", userId));
        for (Long roleId : roleIds) {
            userRoleMapper.insert(new SysUserRole(userId, roleId));
        }
        return AjaxResult.success();
    }

    @Override
    @SneakyThrows
    public void export() {
        List<SysUser> sysUsers = sysUserMapper.selectList(null);
        List<SysUserCsvDto> list = sysUsers.stream()
                .map(SysUserCsvDto::new)
                .collect(Collectors.toList());
        StatefulBeanToCsv<SysUserCsvDto> beanToCsv = new StatefulBeanToCsvBuilder<SysUserCsvDto>(response.getWriter())
                .build();

        // 写入对象列表
        beanToCsv.write(list);
    }

    @Override
    @Transactional
    public AjaxResult importData(MultipartFile file, boolean updateSupport) {
        if (file == null || file.isEmpty()) {
            throw buildParamException("file", "导入文件不能为空");
        }
        System.out.println("1---------------");
        try {
            HeaderColumnNameMappingStrategy<SysUserCsvDto> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(SysUserCsvDto.class);
            CsvToBean<SysUserCsvDto> list = new CsvToBeanBuilder<SysUserCsvDto>(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))
                    .withType(SysUserCsvDto.class)
                    .withMappingStrategy(strategy)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            List<SysUserCsvDto> userList = list.parse();
            System.out.println("2-------------"+userList);
            for (SysUserCsvDto user : userList) {
                validateUserIdNotExists(user.getUserId());
                validateDeptExists(user.getDeptId());
                validateUserNameNotExists(user.getUserName());
                validatePhonenumberNotExists(user.getPhonenumber());
                SysUser sysUser = user.toSysUser(user);
                sysUserMapper.insert(sysUser);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success();
    }

    private SysUser validateUserIdExists(Long userId) {
        SysUser old = sysUserMapper.selectOne(new QueryWrapper<SysUser>().eq("user_id", userId));
        if (old == null) {
            throw buildParamException("userId ", "当前用户不存在: " + userId);
        }
        return old;
    }

    private void validateUserIdNotExists(Long userId) {
        SysUser old = sysUserMapper.selectOne(new QueryWrapper<SysUser>().eq("user_id", userId));
        if (old != null) {
            throw buildParamException("userId ", "当前用户已存在: " + userId);
        }

    }


    private void validateRoleIds(Long[] roleIds) {
        for (Long roleId : roleIds) {
            SysRole sysRole = sysRoleMapper.selectOne(new QueryWrapper<SysRole>().eq("role_id", roleId));
            if (sysRole == null) {
                throw buildParamException("roleId", "当前角色不存在: " + roleId);
            }
        }
    }

    private void validatePostIds(Long[] postIds) {
        for (Long postId : postIds) {
            SysDept sysDept = sysDeptMapper.selectOne(new QueryWrapper<SysDept>().eq("post_id", postId));
            if (sysDept == null) {
                throw buildParamException("postId", "当前部门不存在: " + postId);
            }
        }
    }

    private void validateUserNameNotExists(String userName) {
        SysUser old = sysUserMapper.selectOne(new QueryWrapper<SysUser>().eq("user_name", userName));
        if (old != null) {
            throw buildParamException("userName", "当前用户名称已存在: " + userName);
        }
    }

    private void validatePhonenumberNotExists(String phonenumber) {

        SysUser old = sysUserMapper.selectOne(new QueryWrapper<SysUser>().eq("phonenumber", phonenumber));
        if (old != null) {
            throw buildParamException("phonenumber", "当前手机号码已存在: " + phonenumber);
        }
    }

    private void validateDeptExists(Long deptId) {
        SysUser old = sysUserMapper.selectOne(new QueryWrapper<SysUser>().eq("dept_id", deptId));
        if (old != null) {
            throw buildParamException("deptId", "当前部门不存在: " + deptId);
        }
    }


}