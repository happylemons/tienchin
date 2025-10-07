package org.emilia.tienchin.controller.common;

import java.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.emilia.tienchin.pojo.AjaxResult;
import org.emilia.tienchin.service.SysConfigService;
import org.emilia.tienchin.service.SysLoginService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired(required = false)
    private SysConfigService configService;

    @GetMapping("/captchaImage")
    public AjaxResult getCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean result = configService.captchaOnOff();
        AjaxResult success = AjaxResult.success();

        if (result) {
            return success.put("captchaOnOff", false);
        }
        return AjaxResult.error();
    }
}
