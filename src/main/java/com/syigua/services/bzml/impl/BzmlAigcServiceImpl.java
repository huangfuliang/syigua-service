package com.syigua.services.bzml.impl;

import com.alibaba.fastjson.JSONObject;
import com.ctc.wstx.util.StringUtil;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.syigua.dto.ModelPromptParams;
import com.syigua.dto.ModelResponse;
import com.syigua.dto.ParamsModel;
import com.syigua.params.bzml.BzmlParams;
import com.syigua.po.SygPromptPO;
import com.syigua.services.bzml.BzmlAigcService;
import com.syigua.services.models.QwenModelsService;
import com.syigua.services.models.SygPromptService;
import com.syigua.vo.BzmlVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class BzmlAigcServiceImpl implements BzmlAigcService {

    @Autowired
    private SygPromptService sygPromptService;

    @Autowired
    private QwenModelsService qwenModelsService;

    /** 八字 */
    private static final Integer PROMPT_CODE_BZ = 8001;

    /** 农历日期 */
    private static final Integer PROMPT_CODE_RL = 8002;

    /** 八字排盘 */
    private static final Integer PROMPT_CODE_BZPB = 8003;


    private static final Integer PROMPT_CODE_BZZS = 8004;

    private static final Integer PROMPT_CODE_FX = 8005;

    private static final Integer PROMPT_CODE_WLYN = 8006;

    public BzmlAigcServiceImpl() {
    }


    /**
     * 根据性别、年、月、日、时查询八字命理
     * @param bzmlParams
     * @return
     */
    boolean aigcBzml(BzmlParams bzmlParams) {
        SygPromptPO sygPrompt = sygPromptService.getPromptByCode(PROMPT_CODE_BZ);
        if (Objects.nonNull(sygPrompt)) {
            String prompt = sygPrompt.getPrompt().replace("{userDate}", wrapStr(bzmlParams));
            ModelResponse modelResponse = qwenModelsService.getRes(prompt);
            if (Objects.nonNull(modelResponse) && modelResponse.getCode() == 200) {
                String response = modelResponse.getResponse();
                System.out.println(response);
                return true;
            }
        }
        return false;
    }

    @Override
    public BzmlVO getBzml(BzmlParams bzmlParams) {
        BzmlVO bzmlVO = new BzmlVO();
        ParamsModel paramsModel = new ParamsModel();
        String rl =null;
        if (bzmlParams.getCode().equals(1)) {
           paramsModel.setUserDate(wrapStr(bzmlParams));
           rl = getBz(paramsModel.getMap(), PROMPT_CODE_RL);
           bzmlVO.setNlrq(rl);
        }
        String bz = getBz(paramsModel.getMap(), PROMPT_CODE_BZ);
        JSONObject bzRe = JSONObject.parseObject(bz);
        bzmlVO.setBz(bzRe.getString("bazi"));
        bzmlVO.setWuxing(bzRe.getString("wuxing"));
        bzmlVO.setShuxiang(bzRe.getString("shuxiang"));

        paramsModel.setUserDate(rl);
        String pzpb = getBz(paramsModel.getMap(), PROMPT_CODE_BZPB);
        bzmlVO.setBzpb(pzpb);

        paramsModel.setBz(bzRe.getString("bazi"));
        String bzzs = getBz(paramsModel.getMap(), PROMPT_CODE_BZZS);
        bzmlVO.setBzzs(bzzs);

        paramsModel.setWx(bzRe.getString("wuxing"));
        paramsModel.setWxbp(bzRe.getString("shuxiang"));
        String fx = getBz(paramsModel.getMap(), PROMPT_CODE_FX);
        JSONObject fxRe = JSONObject.parseObject(fx);
        bzmlVO.setXgfx(fxRe.getString("xgfx"));
        bzmlVO.setAqfx(fxRe.getString("aqfx"));
        bzmlVO.setCyfx(fxRe.getString("cyfx"));
        bzmlVO.setSyfx(fxRe.getString("syfx"));
        bzmlVO.setJkfx(fxRe.getString("jkfx"));
        String wlyn = getBz(paramsModel.getMap(), PROMPT_CODE_WLYN);
        bzmlVO.setWlyn(wlyn);

        return bzmlVO;
    }

    public String getBz(Map<String, String> bzmlParams, Integer promptCode) {
        SygPromptPO sygPrompt = sygPromptService.getPromptByCode(promptCode);
        if (Objects.nonNull(sygPrompt)) {
            String prompt = wrapPrompt(bzmlParams, sygPrompt.getPrompt());
            ModelResponse modelResponse = qwenModelsService.getRes(prompt);
            if (Objects.nonNull(modelResponse) && modelResponse.getCode() == 200) {
                String response = modelResponse.getResponse();
                System.out.println(response);
                JSONObject choices = JSONObject.parseObject(response);
                JSONObject choice = choices.getJSONArray("choices").getJSONObject(0);
                return choice.getJSONObject("message").getString("content");
            }
        }
        return "";
    }

    private String wrapStr(BzmlParams bzmlParams) {
        return bzmlParams.getBirthYear() + "年" + bzmlParams.getBirthMonth()+"月" + bzmlParams.getBirthDay()+"日" + bzmlParams.getBirthHour()+"时";
    }



    private String wrapPrompt(Map<String, String> map, final String sygPrompt) {
        return map.entrySet().stream()
                .filter(entry -> Objects.nonNull(entry.getValue()))
                .reduce(sygPrompt, (prompt, entry) -> prompt.replaceAll(entry.getKey(), entry.getValue()),
                        (prompt1, prompt2) -> prompt1.replaceAll(prompt2, ""));
    }


    public static void main(String[] args) {
        ParamsModel paramsModel = new ParamsModel();
        paramsModel.setUserDate("1980年11月15日");
        paramsModel.setBz("dsd");
        BzmlAigcServiceImpl bzmlAigcService = new BzmlAigcServiceImpl();
        String str = bzmlAigcService.wrapPrompt(paramsModel.getMap(), "你是一个专业zw_bz的命理师，根据《万年历》《渊海子平》的内容 根据农历zw_userDate信息，计算五行八字、属相以及五行；直接输出结果，不需要过程，直接按照样例输出结果");
        System.out.println(str);
    }

}
