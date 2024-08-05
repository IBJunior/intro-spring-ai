package com.example.introspringai.basics;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/prompts")
public class PromptsController {

    private final ChatClient chatClient;

    public PromptsController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/prompt-template")
    public String chatWithPromptTemplate(@RequestParam(defaultValue = "mathematics") String subject) {

        PromptTemplate template = new PromptTemplate("tell me a joke about {subject}");
        var prompt = template.create(Map.of("subject", subject));

        return this.chatClient
                .prompt(prompt)
                .call()
                .content();
    }

    @GetMapping("/prompt-with-messages")
    public String chatWithPromptAndMessages() {
        UserMessage userMessage = new UserMessage("tell me a joke about software developers");
        SystemMessage systemMessage = new SystemMessage("""
                You are funny assistant who tells jokes, you should add multiple
                emojis at the end of each response
                """);
        Prompt prompt = new Prompt(List.of(userMessage, systemMessage));

        return this.chatClient
                .prompt(prompt)
                .call()
                .content();
    }

    @GetMapping("/prompt-with-message-and-resources")
    public String chatWithPromptAndMessageAndResource(@Value("classpath:user-prompt.st") Resource messageResource) {
        UserMessage userMessage = new UserMessage(messageResource);
        Prompt prompt = new Prompt(userMessage);

        return this.chatClient
                .prompt(prompt)
                .call()
                .content();
    }


}
