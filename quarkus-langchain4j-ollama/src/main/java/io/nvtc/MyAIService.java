package io.nvtc;

import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService
public interface MyAIService {
     public String chat(@UserMessage String message);
     
} 