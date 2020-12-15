package com.lemon.validate.bean;

import javax.validation.constraints.NotNull;

/**
 * Created on 2020/12/15 9:27.
 *
 * @author lemon
 */
public class ValidDtoIn2 {

    /** 枚举类在反序列化时 前端可以通过传递 ordinal以及name 绑定 */
    @NotNull
    private LogTypeEnum logType;

    public LogTypeEnum getLogType() {
        return logType;
    }

    public void setLogType(LogTypeEnum logType) {
        this.logType = logType;
    }
}
