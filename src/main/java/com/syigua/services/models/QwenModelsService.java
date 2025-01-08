package com.syigua.services.models;

import com.syigua.dto.ModelPromptParams;
import com.syigua.dto.ModelResponse;
import org.springframework.stereotype.Service;

public interface QwenModelsService {

    ModelResponse getRes(String prompt);

    ModelResponse getXms(String prompt);

}
