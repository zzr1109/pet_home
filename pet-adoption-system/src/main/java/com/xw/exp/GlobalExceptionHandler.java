package com.xw.exp;

import com.xw.common.Constants;
import com.xw.common.Result;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 自定义异常全局捕捉
     * @param e
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    public Result<?> serviceException(ServiceException e) {
        return new Result<>(e.getCode(),e.getMessage());
    }

    /**
     * JSR303参数校验全局异常捕捉
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        return new Result<>(Constants.PARAMETER_ERROR,bindingResult.getFieldErrors().get(0).getDefaultMessage());
    }
}
