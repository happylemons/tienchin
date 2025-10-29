package org.emilia.tienchin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletResponse;
import org.emilia.tienchin.config.PageImpl;
import org.emilia.tienchin.controller.dto.user.*;
import org.emilia.tienchin.pojo.AjaxResult;
import org.emilia.tienchin.pojo.entity.SysRole;
import org.emilia.tienchin.pojo.entity.SysUser;
import org.emilia.tienchin.pojo.sys.SysConfig;
import org.emilia.tienchin.pojo.sys.SysPost;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SysUserService {

    SysUser findUser(String username);

    void updateUserProfile(SysUser user);

    List<SysUser> selectUsers(ListSysUserReq user, PageImpl<SysUser> page);

    List<SysRole> selectRoles();

    List<SysPost> selectPosts();

    SysUser selectByUserId(Long userId);

    boolean validateAdmin(Long userId);

    AjaxResult add(AddSysUserReq user);

    AjaxResult edit(EditSysUserReq user);

    AjaxResult remove(Long[] userIds);

    AjaxResult resetPwd(Long userId, String password);

    AjaxResult changeStatus(ChangeStatusUserReq user);

    List<SysRole> authRole(Long userId);

    AjaxResult insertAuthRole(Long userId, Long[] roleIds);

    void export();

    AjaxResult importData(MultipartFile file, boolean updateSupport);
}
