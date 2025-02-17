package com.syigua.mapper;

import com.syigua.po.BzmlBg;

public interface BzmlBgMapper {

    void insert(BzmlBg bzmlBg);

    BzmlBg getByUid(String uid);

    void update(BzmlBg bzmlBg);

}
