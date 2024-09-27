package io.nvtc.spring_ai_openai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/alt")
public class AlternativeController {

    private ChatClient client;

    record FootballTitles(List<String> titles) {
    }

    public AlternativeController(ChatClient.Builder builder){

        client = builder.build();

    }

    @GetMapping("/movie")
    public String generateResponse(){

        return client.prompt()
            .user("Explain a famous movie scene and name the movie title and year")
            .system("Articulate like an idiot")
            .call()
            .content();

    }

    @GetMapping("/champions")
    public List<String> titles(){

        return client.prompt()
            .user("List the years of VfB Stuttgart being German champion")
            .call()
            .entity(new ParameterizedTypeReference<List<String>>() {});
        }

    @GetMapping("/titles")
    public String champions(){

        FootballTitles titles = client.prompt()
            .user("List all titles of VfB Stuttgart in their history in the form title and year")
            .call()
            .entity(FootballTitles.class);

        System.out.println("Titles: "+titles);
        
        return titles.toString();
    }

}
