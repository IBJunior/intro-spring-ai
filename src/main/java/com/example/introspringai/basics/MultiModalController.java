package com.example.introspringai.basics;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.Media;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.introspringai.basics.ChatClientController.DEFAULT_SYSTEM_MESSAGE;

@RestController
@RequestMapping("/api/multimodal")
public class MultiModalController {

    private final ChatClient chatClient;

    public MultiModalController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping
    public String describeImage(@Value("classpath:multimodal/java-code.png") Resource imageResource) {

        Media media = new Media(MimeTypeUtils.IMAGE_PNG, imageResource);
        UserMessage userMessage = new UserMessage("What do you see in this image ?", media);
        SystemMessage systemMessage = new SystemMessage(DEFAULT_SYSTEM_MESSAGE);

        return this.chatClient
                .prompt(new Prompt(List.of(userMessage, systemMessage)))
                .call()
                .content();
    }
}
