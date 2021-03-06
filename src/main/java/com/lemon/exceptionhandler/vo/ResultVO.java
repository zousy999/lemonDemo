package com.lemon.exceptionhandler.vo;

import com.lemon.exceptionhandler.annotation.ExceptionCode;
import com.lemon.exceptionhandler.enums.ResultCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

/**
 * @author lemon
 * @description 自定义统一响应体
 */
@Getter
@ApiModel
public class ResultVO<T> {
    @ApiModelProperty(value = "状态码", notes = "默认1000是成功")
    private int code;
    @ApiModelProperty(value = "响应信息", notes = "来说明响应情况")
    private String msg;
    @ApiModelProperty(value = "响应的具体数据")
    private T data;

    public ResultVO(T data) {
        this(ResultCode.SUCCESS, data);
    }
    public static ResultVO fail(String msg){
        return new ResultVO<>(1001,msg,null);
    }

    public ResultVO(ResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.data = data;
    }

    public ResultVO(ExceptionCode annotation) {
        this.code = annotation.value();
        this.msg = annotation.message();
    }


    public ResultVO(ExceptionCode annotation, T data) {
        this(annotation);
        this.data = data;
    }

    public ResultVO(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
