package io.nvtc;

import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import io.quarkiverse.langchain4j.ollama;

@RegisterAiService
public interface MyAIService {
     public String chat(@UserMessage String message);
     
} 