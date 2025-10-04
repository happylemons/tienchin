package org.emilia.tienchin.handler;

import org.emilia.tienchin.exception.ParamValidException;
import org.emilia.tienchin.pojo.AjaxResult;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public AjaxResult handleException(MethodArgumentNotValidException e) {
        String msg = e.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("; "));
        return AjaxResult.error("参数校验失败： " +msg);

    }


    @ExceptionHandler(ParamValidException.class)
    @ResponseBody
    public AjaxResult handleException(ParamValidException e) {
        return AjaxResult.error("参数"+e.getParamName()+"校验失败： " +e.getMessage());
    }
}
