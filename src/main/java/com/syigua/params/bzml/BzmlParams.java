package com.syigua.params.bzml;

import lombok.Data;

@Data
public class BzmlParams {

    private String gender; // 性别

    private String birthYear; // 出生年

    private String birthMonth; // 出生月

    private String birthDay; // 出生日

    private String birthHour; // 出生时

    /**
     * 1 阳历 2 农历
     */
    private Integer code;
}
