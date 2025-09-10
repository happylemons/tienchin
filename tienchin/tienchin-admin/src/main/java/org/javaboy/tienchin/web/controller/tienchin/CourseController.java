//package org.javaboy.tienchin.web.controller.tienchin;
//
//import org.javaboy.tienchin.common.annotation.Log;
//import org.javaboy.tienchin.common.core.controller.BaseController;
//import org.javaboy.tienchin.web.controller.pojo.AjaxResult;
//import org.javaboy.tienchin.web.controller.pojo.TableDataInfo;
//import org.javaboy.tienchin.common.enums.BusinessType;
//import org.javaboy.tienchin.common.utils.poi.ExcelUtil;
//import org.javaboy.tienchin.common.validator.EditGroup;
//import org.javaboy.tienchin.course.domain.Course;
//import org.javaboy.tienchin.course.domain.vo.CourseVO;
//import org.javaboy.tienchin.course.service.ICourseService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletResponse;
//import java.util.List;
//
///**
// * <p>
// *  前端控制器
// * </p>
// *
// * @author javaboy
// * @since 2022-12-13
// */
//@RestController
//@RequestMapping("/tienchin/course")
//public class CourseController extends BaseController {
//
//    @Autowired
//    ICourseService courseService;
//
//    @PreAuthorize("hasPermission('tienchin:course:list')")
//    @GetMapping("/list")
//    public TableDataInfo list(CourseVO courseVO) {
//        return null;
//
//    }
//
//    @PreAuthorize("hasPermission('tienchin:course:create')")
//    @Log(title = "课程管理", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@Validated @RequestBody Course course) {
//        return null;
//
//    }
//
//    /**
//     * 修改课程
//     */
//    @PreAuthorize("hasPermission('tienchin:course:edit')")
//    @Log(title = "课程管理", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public AjaxResult edit(@Validated(EditGroup.class) @RequestBody Course course) {
//        return null;
//
//    }
//
//    /**
//     * 根据课程 ID 查询一个具体的课程
//     *
//     * @param courseId
//     * @return
//     */
//    @PreAuthorize("hasPermission('tienchin:course:edit')")
//    @GetMapping("/{courseId}")
//    public AjaxResult getInfo(@PathVariable Long courseId) {
//        return null;
//
//    }
//
//    @PreAuthorize("hasPermission('tienchin:course:remove')")
//    @Log(title = "课程管理", businessType = BusinessType.DELETE)
//    @DeleteMapping("/{courseIds}")
//    public AjaxResult remove(@PathVariable Long[] courseIds) {
//        return null;
//    }
//
//    @Log(title = "课程管理", businessType = BusinessType.EXPORT)
//    @PreAuthorize("hasPermission('tienchin:course:export')")
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, CourseVO courseVO) {
//
//    }
//}
