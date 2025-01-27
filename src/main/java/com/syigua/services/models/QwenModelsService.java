package com.syigua.services.models;

import com.syigua.dto.ChatHistoryParams;
import com.syigua.dto.ModelPromptParams;
import com.syigua.dto.ModelResponse;
import org.springframework.stereotype.Service;

import java.util.Queue;

public interface QwenModelsService {

    ModelResponse getRes(String prompt);

    ModelResponse getXms(String prompt);

    ModelResponse getResByHistory(String prompt, String userName);

}
