slides: 

openai: 
- https://platform.openai.com/docs/overview (no Java example)
- https://platform.openai.com/settings/proj_zPQfmJvS87hzTXNB5xwnIxLj (project)
- https://platform.openai.com/usage (usage)

requirement to provide org id, project id and 

spring ai:
- start.spring.io (show dependencies)
- show .env file
- show pom.xml
- docs: https://docs.spring.io/spring-ai/reference/api/index.html

simple approach:

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

generic approach:

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

parametrized type:
```Java
    @GetMapping("/vfb")
    public List<String> promptVfB(){

        return client.prompt()
            .user("List the years of VfB Stuttgart being German champion")
            .call()
            .entity(new ParameterizedTypeReference<List<String>>() {});
        }
```

own object/record:








does it make sense?
"this meeting could have been an email"
"this prompt could have been answered by a google query"