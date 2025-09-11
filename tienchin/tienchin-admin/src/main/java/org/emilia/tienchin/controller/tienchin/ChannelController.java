//package org.emilia.tienchin.web.controller.tienchin;
//
//import org.emilia.tienchin.channel.domain.Channel;
//import org.emilia.tienchin.channel.domain.vo.ChannelVO;
//import org.emilia.tienchin.channel.service.IChannelService;
//import org.emilia.tienchin.common.annotation.Log;
//import org.emilia.tienchin.common.core.controller.BaseController;
//import org.emilia.tienchin.web.controller.pojo.AjaxResult;
//import org.emilia.tienchin.web.controller.pojo.TableDataInfo;
//import org.emilia.tienchin.common.enums.BusinessType;
//import org.emilia.tienchin.common.utils.poi.ExcelUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.http.HttpServletResponse;
//import java.util.List;
//
///**
// * <p>
// * 前端控制器
// * </p>
// *
// * @author emilia
// * @since 2022-12-03
// */
//@RestController
//@RequestMapping("/tienchin/channel")
//public class ChannelController extends BaseController {
//
//    @Autowired
//    IChannelService channelService;
//
//    @PreAuthorize("hasPermission('tienchin:channel:list')")
//    @GetMapping("/list")
//    public TableDataInfo list(ChannelVO channelVO) {
//        return null;
//
//    }
//
//    /**
//     * 添加渠道
//     *
//     * @return
//     */
//    @PreAuthorize("hasPermission('tienchin:channel:create')")
//    @Log(title = "渠道管理", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@Validated @RequestBody ChannelVO channelVO) {
//        return null;
//
//    }
//
//
//    /**
//     * 修改保存角色
//     */
//    @PreAuthorize("hasPermission('tienchin:channel:edit')")
//    @Log(title = "渠道管理", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public AjaxResult edit(@Validated @RequestBody ChannelVO channelVO) {
//        return null;
//
//    }
//
//
//    /**
//     * 根据渠道 ID 查询一个具体的渠道
//     *
//     * @param channelId
//     * @return
//     */
//    @PreAuthorize("hasPermission('tienchin:channel:edit')")
//    @GetMapping("/{channelId}")
//    public AjaxResult getInfo(@PathVariable Long channelId) {
//        return null;
//
//    }
//
//    /**
//     * 删除渠道
//     */
//    @PreAuthorize("hasPermission('tienchin:channel:remove')")
//    @Log(title = "渠道管理", businessType = BusinessType.DELETE)
//    @DeleteMapping("/{channelIds}")
//    public AjaxResult remove(@PathVariable Long[] channelIds) {
//        return null;
//
//    }
//
//    @Log(title = "渠道管理", businessType = BusinessType.EXPORT)
//    @PreAuthorize("hasPermission('tienchin:channel:export')")
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, ChannelVO channelVO) {
//
//    }
//
//    @PostMapping("/importTemplate")
//    public void importTemplate(HttpServletResponse response) {
//
//
//    }
//
//    @Log(title = "渠道管理", businessType = BusinessType.IMPORT)
//    @PreAuthorize("hasPermission('tienchin:channel:import')")
//    @PostMapping("/importData")
//    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
//        return null;
//
//    }
//
//
//}
