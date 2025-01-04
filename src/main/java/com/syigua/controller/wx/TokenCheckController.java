package com.syigua.controller.wx;

import com.syigua.params.wx.TokenCheckParams;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;


/**
 * @author huangfuliang
 * 微信token校验
 */
@RestController
@RequestMapping("/wx")
public class TokenCheckController {

    private static final String TOKEN = "907e8b5b4888e320c9e2c60c3b4c8c00";

    @GetMapping("/checkToken")
    public String checkToken(TokenCheckParams tokenCheckParams) {
        String[] tmpArr = {TOKEN, tokenCheckParams.getTimestamp(), tokenCheckParams.getNonce()};
        Arrays.sort(tmpArr);
        String tmpStr = implode(tmpArr);
        tmpStr = sha1(tmpStr);
        if (tmpStr.equals(tokenCheckParams.getSignature())) {
            return tokenCheckParams.getEchostr();
        } else {
            return "";
        }
    }


    @PostMapping("/checkToken")
    public String handleMessage(@RequestBody String requestBody) {
        // 处理接收到的消息
        System.out.println(requestBody);
        // ...
        return "接收到消息";
    }

    private String implode(String[] arr) {
        StringBuilder sb = new StringBuilder();
        for (String str : arr) {
            sb.append(str);
        }
        return sb.toString();
    }
    private static String sha1(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] bytes = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}

