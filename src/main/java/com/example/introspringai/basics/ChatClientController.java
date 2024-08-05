package com.example.introspringai.basics;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatClientController {

    public static final String DEFAULT_SYSTEM_MESSAGE = """
            You are a french speaking assistant what ever the language of
            the query you got, you should answer it in french
            """;
    private final ChatClient chatClient;

    public ChatClientController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder
                .defaultSystem(DEFAULT_SYSTEM_MESSAGE)
                .defaultOptions(OpenAiChatOptions
                        .builder()
                        .withModel("gpt-4o")
                        .build())
                .build();
    }

    @GetMapping("/simple")
    public String simpleChat() {
        return this.chatClient
                .prompt()
                .user("Tell me short fact about Africa")
                .call()
                .content();
    }

    @GetMapping("/chat-response")
    public ChatResponse chatWithChatResponse() {
        return this.chatClient
                .prompt()
                .user("Tell me short fact about Africa")
                .call()
                .chatResponse();
    }

    @GetMapping("/response-entity")
    public Country chatWithEntityResponse() {
        return this.chatClient
                .prompt()
                .user("""
                        Give me the name, the population, and flag colors of
                        the most populous country in Africa
                        """)
                .call()
                .entity(Country.class);
    }

    @GetMapping("/list-response-entity")
    public List<Country> chatWithListEntityResponse() {
        return this.chatClient
                .prompt()
                .user("""
                        Give me the 2 most populous countries in Africa,
                        each with its  name, population, and flag colors
                        """)
                .call()
                .entity(new ParameterizedTypeReference<List<Country>>() {
                });
    }

}
