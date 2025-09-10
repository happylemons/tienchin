//package org.javaboy.tienchin.web.controller.common;
//
//import java.io.IOException;
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletResponse;
//
//import org.javaboy.tienchin.web.controller.pojo.AjaxResult;
//import org.javaboy.tienchin.common.core.redis.RedisCache;
//import org.javaboy.tienchin.system.service.ISysConfigService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import com.google.code.kaptcha.Producer;
//
///**
// * 验证码操作处理
// *
// * @author tienchin
// */
//@RestController
//public class CaptchaController {
//    @Resource(name = "captchaProducer")
//    private Producer captchaProducer;
//
//    @Resource(name = "captchaProducerMath")
//    private Producer captchaProducerMath;
//
//    @Autowired
//    private RedisCache redisCache;
//
//    @Autowired
//    private ISysConfigService configService;
//
//    /**
//     * 生成验证码
//     */
//    @GetMapping("/captchaImage")
//    public AjaxResult getCode(HttpServletResponse response) throws IOException {
//        return null;
//    }
//}
