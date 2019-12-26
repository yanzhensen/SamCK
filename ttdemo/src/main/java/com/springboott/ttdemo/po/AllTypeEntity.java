package com.springboott.ttdemo.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class AllTypeEntity {
    private byte byteValue;
    private boolean booleanValue;
    private short shortValue;
    private char charValue;
    private int intValue;
    private long longValue;
    private float floatValue;
    private double doubleValue;

    private Byte bByteValue;
    private Boolean bBooleanValues;
    private Short bShortValue;
    private Character characterValue;
    private Integer bIntegerValue;
    private Long bLongValue;
    private Float bFloatValue;
    private Double bDoubleValue;

    private String bstringValue;

    private Date dateValue;
    private Timestamp timestampValue;
    private LocalDate localDateValue;
    private LocalDateTime localDateTimeValue;
}
