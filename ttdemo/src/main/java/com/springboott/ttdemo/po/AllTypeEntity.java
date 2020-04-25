package com.springboott.ttdemo.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class AllTypeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private byte byteSmValue;
    private boolean booleanSmValue;
    private short shortSmValue;
    private char charSmValue;
    private int intSmValue;
    private long longSmValue;
    private float floatSmValue;
    private double doubleSmValue;

    private Byte byteBigValue;
    private Boolean booleanBigValue;
    private Short shortBigValue;
    private Character characterBigValue;
    private Integer integerBigValue;
    private Long longBigValue;
    private Float floatBigValue;
    private Double doubleBigValue;

    private String stringOfValue;

    private Date dateOfValue;
    private Timestamp timestampOfValue;
    private LocalDate localDateOfValue;
    private LocalDateTime localDateTimeOfValue;
}
