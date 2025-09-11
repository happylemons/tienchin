//package org.emilia.tienchin.web.controller.tienchin.clue;
//
//import org.emilia.tienchin.activity.service.IActivityService;
//import org.emilia.tienchin.channel.service.IChannelService;
//import org.emilia.tienchin.clue.domain.Clue;
//import org.emilia.tienchin.clue.domain.vo.ClueDetails;
//import org.emilia.tienchin.clue.domain.vo.ClueSummary;
//import org.emilia.tienchin.clue.domain.vo.ClueVO;
//import org.emilia.tienchin.clue.service.IClueService;
//import org.emilia.tienchin.common.annotation.Log;
//import org.emilia.tienchin.common.core.controller.BaseController;
//import org.emilia.tienchin.web.controller.pojo.AjaxResult;
//import org.emilia.tienchin.web.controller.pojo.TableDataInfo;
//import org.emilia.tienchin.common.enums.BusinessType;
//import org.emilia.tienchin.common.validator.CreateGroup;
//import org.emilia.tienchin.common.validator.EditGroup;
//import org.emilia.tienchin.system.service.ISysUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//
///**
// * <p>
// *  前端控制器
// * </p>
// *
// * @author emilia
// * @since 2022-12-14
// */
//@RestController
//@RequestMapping("/tienchin/clue")
//public class ClueController extends BaseController {
//
//    @Autowired
//    IClueService clueService;
//    @Autowired
//    IChannelService channelService;
//
//    @Autowired
//    ISysUserService sysUserService;
//
//    @Autowired
//    IActivityService activityService;
//
//    @PreAuthorize("hasPermission('tienchin:clue:create')")
//    @Log(title = "线索管理", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@Validated(CreateGroup.class) @RequestBody Clue clue) {
//        return null;
//
//    }
//
//    @PreAuthorize("hasPermission('tienchin:clue:create')")
//    @GetMapping("/channels")
//    public AjaxResult getAllChannels() {
//        return null;
//
//    }
//
//    @PreAuthorize("hasPermission('tienchin:clue:create')")
//    @GetMapping("/activity/{channelId}")
//    public AjaxResult getActivityByChannelId(@PathVariable Integer channelId) {
//        return null;
//
//    }
//
//    @PreAuthorize("hasPermission('tienchin:clue:list')")
//    @GetMapping("/list")
//    public TableDataInfo list(ClueVO clueVO) {
//        return null;
//
//    }
//
//
//    @GetMapping("/users/{deptId}")
//    @PreAuthorize("hasPermission('tienchin:clue:assignment')")
//    public AjaxResult getUsersByDeptId(@PathVariable Long deptId) {
//        return null;
//
//    }
//
//
//    @GetMapping("/{clueId}")
//    @PreAuthorize("hasAnyPermissions('tienchin:clue:view','tienchin:clue:follow')")
//    public AjaxResult getClueDetailsByClueId(@PathVariable Integer clueId) {
//        return null;
//
//    }
//
//    @PreAuthorize("hasPermission('tienchin:clue:follow')")
//    @PostMapping("/follow")
//    public AjaxResult clueFollow(@RequestBody ClueDetails clueDetails) {
//        return null;
//
//    }
//
//    @PreAuthorize("hasPermission('tienchin:clue:follow')")
//    @PostMapping("/to_business/{clueId}")
//    public AjaxResult clue2Business(@PathVariable Integer clueId) {
//        return null;
//
//    }
//    @PreAuthorize("hasPermission('tienchin:clue:follow')")
//    @PostMapping("/invalid")
//    public AjaxResult invalidClueFollow(@RequestBody ClueDetails clueDetails) {
//        return null;
//
//    }
//
//    @GetMapping("/summary/{clueId}")
//    @PreAuthorize("hasPermission('tienchin:clue:edit')")
//    public AjaxResult getClueSummaryByClueId(@PathVariable Integer clueId) {
//        return null;
//
//    }
//
//
//    @PreAuthorize("hasPermission('tienchin:clue:edit')")
//    @PutMapping
//    public AjaxResult updateClue(@Validated(EditGroup.class) @RequestBody Clue clue) {
//        return null;
//
//    }
//
//    //localhost:8080/clueIds=1&clueIds=2
//    @PreAuthorize("hasPermission('tienchin:clue:remove')")
//    @DeleteMapping("/{clueIds}")
//    public AjaxResult deleteClueById(@PathVariable Integer[] clueIds) {
//        return null;
//
//    }
//
//
//}
