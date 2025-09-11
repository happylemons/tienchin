package org.emilia.tienchin.controller.common;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import org.emilia.tienchin.pojo.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 验证码操作处理
 *
 * @author tienchin
 */
@RestController
public class CaptchaController {
    /**
     * 生成验证码
     */
    @GetMapping("/captchaImage")
    public AjaxResult getCode(HttpServletResponse response) throws IOException {
        return AjaxResult.success();
    }
}
