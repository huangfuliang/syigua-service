package com.syigua.params.bzml;

import lombok.Data;

@Data
public class BzmlParams {

    private String uid;

    private String name;

    private String gender;

    private String calendarType;

    private String birthdate;

    private String query;

    private String datehour;


    public String toString() {
        return "姓名:" + name + "；性别:" + gender + "；生辰:" + birthdate + datehour + "时" + "；查询内容" + query;
    }

}
