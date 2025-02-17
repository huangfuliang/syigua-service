package com.syigua.controller.ys;


import com.syigua.params.bzml.BzmlParams;
import com.syigua.services.bzml.BzmlGenerateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ys")
@Slf4j
public class YsGenerateController {

    @Autowired
    private BzmlGenerateService bzmlGenerateService;

    @RequestMapping("/generateBzml/{uid}")
    @CrossOrigin
    public boolean generateBzml(@RequestBody BzmlParams bzmlParams, @PathVariable("uid") String uid) {
        log.info("bzmlParams：{}, uid : {} ", bzmlParams.toString(), uid);
        return bzmlGenerateService.generateBzml(bzmlParams, uid);
    }

    @GetMapping("/finish/{uid}")
    public boolean finish(@PathVariable("uid") String uid, String fileName) {
        return bzmlGenerateService.finish(uid, fileName);
    }

    @GetMapping("/bgList/{uid}")
    public String bgList() {
        return null;
    }

}
