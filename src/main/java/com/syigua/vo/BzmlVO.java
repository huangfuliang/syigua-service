package com.syigua.vo;

import lombok.Data;

@Data
public class BzmlVO {

    /**
     * 八字
     */
    private String bz;

    /**
     * 农历日期
     */
    private String nlrq;

    /**
     * 阳历日期
     */
    private String ylrq;

    /**
     * 属相
     */
    private String shuxiang;

    /**
     * 五行
     */
    private String wuxing;

    /**
     * 八字排盘
     */
    private String bzpb;

    /**
     * 八字综述
     */
    private String bzzs;

    /**
     * 性格分析
     */
    private String xgfx;

    /**
     * 爱情分析
     */
    private String aqfx;

    /**
     * 事业分析
     */
    private String syfx;

    /**
     * 财运分析
     */
    private String cyfx;

    /**
     * 健康分析
     */
    private String jkfx;

    /**
     * 三名通会
     */
    private String smth;

    /**
     * 未来一年
     */
    private String wlyn;

}
