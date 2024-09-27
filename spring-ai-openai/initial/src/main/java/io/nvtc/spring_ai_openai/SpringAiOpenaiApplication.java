package io.nvtc.spring_ai_openai;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringAiOpenaiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAiOpenaiApplication.class, args);
	}

}
