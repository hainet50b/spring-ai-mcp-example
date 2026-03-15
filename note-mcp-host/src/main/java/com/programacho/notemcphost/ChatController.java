package com.programacho.notemcphost;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder builder, ToolCallbackProvider provider) {
        this.chatClient = builder
                .defaultToolCallbacks(provider)
                .build();
    }

    @PostMapping
    public String chat(@RequestBody String message) {
        return chatClient
                .prompt(message)
                .call()
                .content();
    }
}
