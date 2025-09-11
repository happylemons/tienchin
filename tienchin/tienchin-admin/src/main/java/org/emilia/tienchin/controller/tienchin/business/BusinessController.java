//package org.emilia.tienchin.web.controller.tienchin.business;
//
//import org.emilia.tienchin.activity.service.IActivityService;
//import org.emilia.tienchin.business.domain.Business;
//import org.emilia.tienchin.business.domain.vo.BusinessFollow;
//import org.emilia.tienchin.business.domain.vo.BusinessSummary;
//import org.emilia.tienchin.business.domain.vo.BusinessSummaryEnhance;
//import org.emilia.tienchin.business.domain.vo.BusinessVO;
//import org.emilia.tienchin.business.service.IBusinessService;
//import org.emilia.tienchin.channel.service.IChannelService;
//import org.emilia.tienchin.common.annotation.Log;
//import org.emilia.tienchin.common.core.controller.BaseController;
//import org.emilia.tienchin.web.controller.pojo.AjaxResult;
//import org.emilia.tienchin.web.controller.pojo.TableDataInfo;
//import org.emilia.tienchin.common.enums.BusinessType;
//import org.emilia.tienchin.course.service.ICourseService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/tienchin/business")
//public class BusinessController extends BaseController {
//
//    @Autowired
//    IBusinessService businessService;
//
//    @Autowired
//    IChannelService channelService;
//
//    @Autowired
//    IActivityService activityService;
//
//    @Autowired
//    ICourseService courseService;
//
//    @PreAuthorize("hasAnyPermissions('tienchin:business:follow','tienchin:business:view')")
//    @GetMapping("/course/{type}")
//    public AjaxResult getCourseByCourseType(@PathVariable Integer type) {
//        return null;
//
//    }
//
//    @PreAuthorize("hasAnyPermissions('tienchin:business:follow','tienchin:business:view')")
//    @GetMapping("/all_course")
//    public AjaxResult getAllCourse() {
//        return null;
//
//    }
//
//    @PreAuthorize("hasAnyPermissions('tienchin:business:follow','tienchin:business:view')")
//    @GetMapping("/{id}")
//    public AjaxResult getBusinessById(@PathVariable Integer id) {
//        return null;
//
//    }
//
//    @PreAuthorize("hasPermission('tienchin:clue:list')")
//    @GetMapping("/list")
//    public TableDataInfo list(BusinessVO businessVO) {
//        return null;
//
//    }
//
//    @PreAuthorize("hasPermission('tienchin:business:follow')")
//    @PostMapping("/follow")
//    public AjaxResult follow(@RequestBody @Validated BusinessFollow businessFollow) {
//        return null;
//
//    }
//
//    @PreAuthorize("hasPermission('tienchin:clue:create')")
//    @Log(title = "商机管理", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@Validated @RequestBody Business business) {
//        return null;
//
//    }
//
//    @PreAuthorize("hasPermission('tienchin:business:create')")
//    @GetMapping("/channels")
//    public AjaxResult getAllChannels() {
//        return null;
//
//    }
//
//    @PreAuthorize("hasPermission('tienchin:business:create')")
//    @GetMapping("/activity/{channelId}")
//    public AjaxResult getActivityByChannelId(@PathVariable Integer channelId) {
//        return null;
//
//    }
//
//    /**
//     * 根据商机的 ID 查询一个商机的摘要信息
//     * @param businessId
//     * @return
//     */
//    @GetMapping("/summary/{businessId}")
//    @PreAuthorize("hasPermission('tienchin:business:edit')")
//    public AjaxResult getBusinessSummaryByBusinessId(@PathVariable Integer businessId) {
//        return null;
//
//    }
//
//
//    @PreAuthorize("hasPermission('tienchin:business:edit')")
//    @PutMapping
//    public AjaxResult updateBusiness(@RequestBody BusinessSummaryEnhance businessSummaryEnhance) {
//        return null;
//
//    }
//
//    @PreAuthorize("hasPermission('tienchin:business:remove')")
//    @DeleteMapping("/{businessIds}")
//    public AjaxResult deleteBusinessById(@PathVariable Integer[] businessIds) {
//        return null;
//
//    }
//
//}
