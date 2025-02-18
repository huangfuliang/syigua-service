package com.syigua.controller;

import com.syigua.params.bzml.BzmlParams;
import com.syigua.services.bzml.impl.BzmlAigcServiceImpl;
import com.syigua.vo.BzmlVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
public class ViewController {

    @Autowired
    private BzmlAigcServiceImpl bzmlAigcService;

    @GetMapping("/reult")
    public String reult(Model model, BzmlParams bzmlParams) {
        long start = System.currentTimeMillis();
        BzmlVO bzml = bzmlAigcService.getBzml(bzmlParams);
        model.addAttribute("bz", bzml.getBz());
        model.addAttribute("nlrq", bzml.getNlrq());
        model.addAttribute("sx", bzml.getShuxiang());
        model.addAttribute("wx", bzml.getWuxing());
        model.addAttribute("bzpb", bzml.getBzpb());
        model.addAttribute("bzzs", bzml.getBzzs());
        model.addAttribute("aqfx", bzml.getAqfx());
        model.addAttribute("cyfx", bzml.getCyfx());
        model.addAttribute("xgfx", bzml.getXgfx());
        model.addAttribute("syfx", bzml.getSyfx());
        model.addAttribute("jkfx", bzml.getJkfx());
        model.addAttribute("wlyn", bzml.getWlyn());
        System.out.println(System.currentTimeMillis() - start);
        return "reslut";
    }

    @GetMapping("/ysbzml")
    public String ysbzml(Model model, HttpSession session){
        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString();
        model.addAttribute("uid", uuidStr);
        session.setAttribute("uid", uuidStr);
        return "ysbzml";
    }

}
