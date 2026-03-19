package com.programacho.notemcphost;

import io.modelcontextprotocol.client.McpSyncClient;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.modelcontextprotocol.spec.McpSchema.*;

@RestController
public class NoteSummaryController {

    private final ChatClient chatClient;

    private final List<McpSyncClient> mcpSyncClients;

    public NoteSummaryController(
            ChatClient.Builder builder,
            List<McpSyncClient> mcpSyncClients,
            ToolCallbackProvider provider) {
        this.chatClient = builder
                .defaultToolCallbacks(provider)
                .build();
        this.mcpSyncClients = mcpSyncClients;
    }

    @GetMapping("/notes/{id}/summary")
    public String notesSummary(@PathVariable Long id) {
        McpSyncClient mcpSyncClient = mcpSyncClients.stream()
                .filter(c -> c.getServerInfo().name().contains("note-mcp-server"))
                .findFirst()
                .orElseThrow();

        GetPromptResult result = mcpSyncClient.getPrompt(
                new GetPromptRequest("summarize-note", Map.of("id", id.toString()))
        );

        List<Message> messages = result.messages().stream()
                .map(m -> {
                    String text = ((TextContent) m.content()).text();

                    return (Message) switch (m.role()) {
                        case USER -> new UserMessage(text);
                        case ASSISTANT -> new AssistantMessage(text);
                    };
                })
                .toList();

        return chatClient
                .prompt(new Prompt(messages))
                .call()
                .content();
    }
}
