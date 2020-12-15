package com.lemon.validate.validation;

/**
 * Created on 2020/12/10 17:56.
 *
 * @author lemon
 */
public interface BaseEnum<K> {
    /**
     * 枚举字典code
     */
    K getCode();
    /**
     * 枚举字典说明
     */
    String getLabel();

    String name();

}
