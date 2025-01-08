package com.syigua.dto;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
public class ParamsModel {

    private String userDate;

    private String bz;

    private String wx;

    private String wxbp;

    private String curDate;

    private String nextYearDate;

    public ParamsModel () {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = new Date();
        this.curDate = sdf.format(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, 1); // 增加一年
        Date nextYearDate = calendar.getTime();
        this.nextYearDate = sdf.format(nextYearDate);
    }


    public Map<String, String> getMap() {
        Map<String, String> map = new HashMap<>();
        map.put("zw_userDate", userDate);
        map.put("zw_bz", bz);
        map.put("zw_wx", wx);
        map.put("zw_wxbp", wxbp);
        map.put("zw_curDate", curDate);
        map.put("zw_nextYearDate", nextYearDate);
        return map;
    }


}
