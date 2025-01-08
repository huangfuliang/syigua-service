package com.syigua.controller.question;

import com.syigua.vo.BaseResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangfuliang
 * 对话接口
 */
@RestController
@RequestMapping("/question")
public class QuestionController {

    public BaseResponse<String> question(String question) {
        return null;
    }

}
