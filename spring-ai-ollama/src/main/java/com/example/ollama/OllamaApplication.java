package com.example.ollama;

import java.util.Map;
import java.util.Random;

import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@SpringBootApplication
@RestController
public class OllamaApplication {

	private final OllamaChatModel chatModel;

	@Autowired
	public OllamaApplication(OllamaChatModel chatModel){

		this.chatModel = chatModel;
	}

	@GetMapping("/")
	public String home() {

		int rand = (new Random().nextInt(100))+1920;

		String prompt = """
				
			Dear machine! Tell me sample movie quote including title from the year %d , please!

		""".formatted(rand);

		String response = chatModel.call(prompt);

		return response;
	}

	@GetMapping("/client")
	public String client() {
		
		RestClient restClient = RestClient.create();

		String result = restClient.get().uri("http://localhost:5000/random").retrieve().body(String.class);

		return result;

	}

	@GetMapping("/movie")
    public Map<String,String> generate() {

		int rand = (new Random().nextInt(100))+1920;

		//RestClient restClient = RestClient.create();
		//String rand = restClient.get().uri("http://localhost:5000/").retrieve().body(String.class);
		
		System.out.println(rand);

		String prompt = """
				
			Dear machine! Tell me sample movie quote including title from the year %d , please!

		""".formatted(rand);

        return Map.of(prompt, chatModel.call(prompt));

    }

    @GetMapping("/ai/generate")
    public Map<String,String> generate(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        System.out.println(message);
		return Map.of("generation", chatModel.call(message));
    }

	public static void main(String[] args) {
		SpringApplication.run(OllamaApplication.class, args);
	  }

}
