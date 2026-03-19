package com.programacho.notemcpserver.application;

import org.springaicommunity.mcp.annotation.McpArg;
import org.springaicommunity.mcp.annotation.McpPrompt;
import org.springframework.stereotype.Component;

import java.util.List;

import static io.modelcontextprotocol.spec.McpSchema.*;

@Component
public class NotePrompts {

    @McpPrompt(name = "summarize-note", description = "Summarize a note by its ID")
    public GetPromptResult summarizeNote(
            @McpArg(name = "id", description = "Note ID", required = true) String id
    ) {
        String prompt = """
                Please summarize note ID %s.
                Use the get-note tool to retrieve the note content first.
                """.formatted(id);

        return new GetPromptResult(
                "Summarize a note",
                List.of(new PromptMessage(Role.USER, new TextContent(prompt)))
        );
    }
}
