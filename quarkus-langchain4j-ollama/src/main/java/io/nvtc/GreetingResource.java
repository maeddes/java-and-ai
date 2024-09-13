package io.nvtc;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

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
