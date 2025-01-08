package com.syigua.po;

import lombok.Data;

import java.util.Date;

@Data
public class SygPromptPO {

    private Integer id;

    private Integer promptCode;

    private String prompt;

    private Date createdAt;

}
