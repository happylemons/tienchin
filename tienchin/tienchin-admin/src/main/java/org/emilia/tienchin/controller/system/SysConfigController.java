package org.emilia.tienchin.controller.system;


import org.emilia.tienchin.controller.common.BaseController;
import org.emilia.tienchin.pojo.AjaxResult;
import org.emilia.tienchin.pojo.business.TableDataInfo;
import org.emilia.tienchin.pojo.sys.SysConfig;
import org.emilia.tienchin.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;

/**
 * 参数配置 信息操作处理
 *
 * @author tienchin
 */
@RestController
@RequestMapping("/system/config")
public class SysConfigController extends BaseController {
    @Autowired(required = false)
    private SysConfigService configService;

    /**
     * 获取参数配置列表
     */
    @PreAuthorize("hasPermission('system:config:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysConfig config) {
        return null;

    }

//    @Log(title = "参数管理", businessType = BusinessType.EXPORT)
    @PreAuthorize("hasPermission('system:config:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysConfig config) {


    }

    /**
     * 根据参数编号获取详细信息
     */
    @PreAuthorize("hasPermission('system:config:query')")
    @GetMapping(value = "/{configId}")
    public AjaxResult getInfo(@PathVariable Long configId) {
        return null;

    }

    /**
     * 根据参数键名查询参数值
     */
    @GetMapping(value = "/configKey/{configKey}")
    public AjaxResult getConfigKey(@PathVariable String configKey) {
        return null;

    }

    /**
     * 新增参数配置
     */
    @PreAuthorize("hasPermission('system:config:add')")
//    @Log(title = "参数管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysConfig config) {
        return null;

    }

    /**
     * 修改参数配置
     */
    @PreAuthorize("hasPermission('system:config:edit')")
//    @Log(title = "参数管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysConfig config) {
        return null;

    }

    /**
     * 删除参数配置
     */
    @PreAuthorize("hasPermission('system:config:remove')")
//    @Log(title = "参数管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{configIds}")
    public AjaxResult remove(@PathVariable Long[] configIds) {
        return null;

    }

    /**
     * 刷新参数缓存
     */
    @PreAuthorize("hasPermission('system:config:remove')")
//    @Log(title = "参数管理", businessType = BusinessType.CLEAN)
    @DeleteMapping("/refreshCache")
    public AjaxResult refreshCache() {
        return null;

    }
}
