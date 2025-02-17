package com.syigua.services.bzml;

import com.syigua.params.bzml.BzmlParams;

public interface BzmlGenerateService {

    boolean generateBzml(BzmlParams bzmlParams, String uid);

    boolean finish(String uid, String fileName);

}
