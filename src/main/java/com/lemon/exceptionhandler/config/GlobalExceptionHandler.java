package com.lemon.exceptionhandler.config;

import com.lemon.exceptionhandler.annotation.ExceptionCode;
import com.lemon.exceptionhandler.enums.ResultCode;
import com.lemon.exceptionhandler.exception.APIException;
import com.lemon.exceptionhandler.vo.ResultVO;
import lombok.SneakyThrows;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import java.lang.reflect.Field;
import java.util.Optional;

import static com.lemon.exceptionhandler.enums.ResultCode.VALIDATE_FAILED;

/**
 * @author lemon
 * @description 全局异常处理 继承默认处理类
 *
 * 多个ExceptionHandler中 按优先级排序处理
 * 在每个处理类中map<异常类，方法> 中去找出能处理当前异常的异常类集合 并按异常到目标异常的深度去排序 找出深度最小的
 * 也就是最匹配的方法去处理 如果该类没有适配的去找下一个处理类
 *
 * 优先匹配到异常的去处理返回 处理逻辑见
 * {@link org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver#doResolveHandlerMethodException }
 * {@link org.springframework.web.servlet.mvc.annotation.ResponseStatusExceptionResolver#doResolveException  }
 * {@link DefaultHandlerExceptionResolver#doResolveException }
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(APIException.class)
    public ResultVO<String> apiExceptionHandler(APIException e) {
        return new ResultVO<>(ResultCode.FAILED, e.getMsg());
    }

    @SneakyThrows
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        // 只抛出第一个错误信息
        FieldError fieldError = e.getBindingResult().getFieldError();
        ExceptionCode annotation = null;
        if(fieldError != null){
            // 参数的Class对象
            Class<?> parameterType = e.getParameter().getParameterType();
            Field field = ReflectionUtils.findField(parameterType,fieldError.getField());
            if(field != null){
                // 获取Field对象上的自定义注解
                annotation = field.getAnnotation(ExceptionCode.class);
            }
        }

        ResultVO<Object> body = Optional.ofNullable(annotation).map(ResultVO::new).orElse(new ResultVO<>(VALIDATE_FAILED,null));

        return new ResponseEntity<>(body, headers, status);
    }

}
