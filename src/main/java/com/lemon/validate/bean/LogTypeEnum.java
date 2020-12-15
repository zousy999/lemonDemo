package com.lemon.validate.bean;


import com.lemon.validate.validation.BaseEnum;

/**
 * Created on 2020/12/10 18:02.
 * @author lemon
 */
public enum LogTypeEnum implements BaseEnum<String> {
    /**
     * 接口类型
     */
    CITIC_BANK_API("1","银行接口")
    ,FACEID_KYC("2","faceid接口")
    ,VERIFY_ENTERPRISE("3","工商四要素接口")
    ,SEND_SMS("4","短信接口");
    private String code;
    private String label;

    public void setCode(String code) {
        this.code = code;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    LogTypeEnum(String code, String label) {
        this.code = code;
        this.label = label;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getLabel() {
        return label;
    }
}
