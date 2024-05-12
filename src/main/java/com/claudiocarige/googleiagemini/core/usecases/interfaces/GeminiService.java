package com.claudiocarige.googleiagemini.core.usecases.interfaces;

import com.claudiocarige.googleiagemini.core.domain.entities.GeminiRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface GeminiService {
    String generate(GeminiRequest geminiRequest) throws JsonProcessingException;
}
