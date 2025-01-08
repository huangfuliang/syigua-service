package com.syigua.mapper;

import com.syigua.po.SygPromptPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SygPromptMapper {

    SygPromptPO getPromptByCode(Integer code);


}
