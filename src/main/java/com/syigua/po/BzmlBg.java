package com.syigua.po;

import lombok.Data;

import java.util.Date;

/**
 * create table bzml_bg (
 *       ID INTEGER not null,
 *       UID VARCHAR(200),
 *       FILE_NAME varchar(200),
 *       FILE_URL VARCHAR(200),
 *     create_time datetime default CURRENT_TIMESTAMP,
 *     uodate_time datetime default CURRENT_TIMESTAMP,
 *       primary key (ID),
 *       index (UID)
 * );
 */
@Data
public class BzmlBg {

    private Integer id;

    private String uid; // 用户id

    private String fileName; // 文件名

    private String fileUrl; // 文件地址

    private Date createTime; // 创建时间

    private Date updateTime; // 更新时间
}
