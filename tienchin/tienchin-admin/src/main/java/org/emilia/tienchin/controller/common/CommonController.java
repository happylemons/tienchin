//package org.emilia.tienchin.web.controller.common;
//
//import java.util.ArrayList;
//import java.util.List;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import org.emilia.tienchin.web.controller.pojo.AjaxResult;
//import org.emilia.tienchin.common.utils.StringUtils;
//import org.emilia.tienchin.common.utils.file.FileUploadUtils;
//import org.emilia.tienchin.common.utils.file.FileUtils;
//import org.emilia.tienchin.framework.config.ServerConfig;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//import org.emilia.tienchin.common.config.TienChinConfig;
//import org.emilia.tienchin.common.constant.Constants;
//
///**
// * 通用请求处理
// *
// * @author tienchin
// */
//@RestController
//@RequestMapping("/common")
//public class CommonController {
//    private static final Logger log = LoggerFactory.getLogger(CommonController.class);
//
//    @Autowired
//    private ServerConfig serverConfig;
//
//    private static final String FILE_DELIMETER = ",";
//
//    /**
//     * 通用下载请求
//     *
//     * @param fileName 文件名称
//     * @param delete   是否删除
//     */
//    @GetMapping("/download")
//    public void fileDownload(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request) {
//
//    }
//
//    /**
//     * 通用上传请求（单个）
//     */
//    @PostMapping("/upload")
//    public AjaxResult uploadFile(MultipartFile file) throws Exception {
//       return null;
//    }
//
//    /**
//     * 通用上传请求（多个）
//     */
//    @PostMapping("/uploads")
//    public AjaxResult uploadFiles(List<MultipartFile> files) throws Exception {
//      return null;
//    }
//
//    /**
//     * 本地资源通用下载
//     */
//    @GetMapping("/download/resource")
//    public void resourceDownload(String resource, HttpServletRequest request, HttpServletResponse response)
//            throws Exception {
//    }
//}
