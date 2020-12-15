package com.lemon.validate.bean;

import com.lemon.validate.validation.OptionalEnum;

/**
 * Created on 2020/12/15 9:27.
 *
 * @author lemon
 */
public class ValidDtoIn {

    @OptionalEnum(values = {LogTypeEnum.class},message = "日志类型不合法")
    private String logType;

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }
}
