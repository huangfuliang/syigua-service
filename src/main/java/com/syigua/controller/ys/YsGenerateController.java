package com.syigua.controller.ys;


import com.syigua.params.bzml.BzmlFinishParams;
import com.syigua.params.bzml.BzmlParams;
import com.syigua.services.bzml.BzmlGenerateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ys")
@Slf4j
public class YsGenerateController {

    @Autowired
    private BzmlGenerateService bzmlGenerateService;

    @RequestMapping("/generateBzml")
    @CrossOrigin
    public boolean generateBzml(@RequestBody BzmlParams bzmlParams) {
        if (!StringUtils.hasLength(bzmlParams.getUid())) {
            return false;
        }
        log.info("bzmlParams：{}, uid : {} ", bzmlParams.toString(), bzmlParams.getUid());
        return bzmlGenerateService.generateBzml(bzmlParams, bzmlParams.getUid());
    }

    @PostMapping("/finish/{uid}")
    public boolean finish(@PathVariable("uid") String uid, @RequestBody BzmlFinishParams bzmlFinishParams) {
        log.info("uid = {}, fileName = {}", uid, bzmlFinishParams.getFileName());
        return bzmlGenerateService.finish(uid, bzmlFinishParams.getFileName());
    }

    @GetMapping("/bgList/{uid}")
    public String bgList() {
        return null;
    }

}
