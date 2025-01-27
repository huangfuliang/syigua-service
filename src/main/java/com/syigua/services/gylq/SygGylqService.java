package com.syigua.services.gylq;

public interface SygGylqService {

    String getQwByTjlx(String tjlx);

    /**
     * 解签
     * @param contentQw 包含问题 和 签文
     * @return
     */
    String getJqByQw(String contentQw, String userName);

}
