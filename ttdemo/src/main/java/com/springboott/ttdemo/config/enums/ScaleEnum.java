package com.springboott.ttdemo.config.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.springboott.ttdemo.config.exception.UnknownEnumException;

/**
 * <p>
 * 小数点枚举
 * </p>
 *
 * @author yzs
 */
public enum ScaleEnum implements IEnum {

    /**
     * 小数点 0位
     */
    ZERO(0),

    /**
     * 小数点 1位
     */
    ONE(1),
    /**
     * 小数点 2位
     */
    TWO(2),
    /**
     * 小数点 3位
     */
    THREE(3),
    /**
     * 小数点 4位
     */
    FOUR(4),
    /**
     * 小数点 5位
     */
    FIVE(5);

    @EnumValue
    private final int value;

    ScaleEnum(final int value) {
        this.value = value;
    }

    @Override
    @JsonValue
    public int getValue() {
        return this.value;
    }


    @JsonCreator
    public static ScaleEnum getEnum(int value) {
        for (ScaleEnum menuTypeEnum : ScaleEnum.values()) {
            if (menuTypeEnum.getValue() == value) {
                return menuTypeEnum;
            }
        }
        throw new UnknownEnumException("Error: Invalid MenuTypeEnum type value: " + value);
    }
}
