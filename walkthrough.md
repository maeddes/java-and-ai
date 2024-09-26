# walkthrough

slides: 

# openai

- https://platform.openai.com/docs/overview (no Java example)
- https://platform.openai.com/settings/proj_zPQfmJvS87hzTXNB5xwnIxLj (project)
- https://platform.openai.com/usage (usage)

requirement to provide org id, project id and 

# spring ai

### steps

- start.spring.io (show dependencies)
- show .env file
- show pom.xml
- docs: https://docs.spring.io/spring-ai/reference/api/index.html

### simple approach

```Java
	public SpringAiOpenaiApplication(OpenAiChatModel model){

		this.model = model;

	}

	@GetMapping("/")
	public String sayHello(){

		String prompt = "Hello. Who are you?";

		return model.call(prompt);

	}
```

### generic approach

```Java
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
```

### own object/record

```Java
    record FootballTitles(List<String> titles) {
    }

    FootballTitles titles = client.prompt()
        .user("List all titles of VfB Stuttgart in their history in the form title and year")
        .call()
        .entity(FootballTitles.class);
```

### parametrized type

```Java
    @GetMapping("/vfb")
    public List<String> promptVfB(){

        return client.prompt()
            .user("List the years of VfB Stuttgart being German champion")
            .call()
            .entity(new ParameterizedTypeReference<List<String>>() {});
        }
```

# Ollama

### install

```bash
curl -fsSL https://ollama.com/install.sh | sh

brew install ollama
````

### use

```bash
ollama serve

ollama pull llama3.1

ollama ls 
ollama list

ollama run gemma2:2b

ollama ps

ollama list
```

### Quarkus and Langchain4j

Docs:
- https://docs.langchain4j.dev/
- https://docs.quarkiverse.io/quarkus-langchain4j/dev/index.html

```xml
        <dependency>
            <groupId>io.quarkiverse.langchain4j</groupId>
            <artifactId>quarkus-langchain4j-ollama</artifactId>
            <version>0.17.2</version>
        </dependency>
```

```java
@RegisterAiService
public interface MyAIService {
     public String chat(@UserMessage String message);   
} 
```

```java
@Path("/ai")
public class GreetingResource {

    @Inject
    MyAIService myAIService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {

        String prompt = """
                Hallo llama, how are you doing?
                """;
        return myAIService.chat(prompt);
    }

    @Path("/{prompt}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String generateResponse(@PathParam("prompt") String prompt){

        System.out.println(prompt);
        return myAIService.chat(prompt);

    }

}
```



does it make sense?
"this meeting could have been an email"
"this prompt could have been answered by a google query"