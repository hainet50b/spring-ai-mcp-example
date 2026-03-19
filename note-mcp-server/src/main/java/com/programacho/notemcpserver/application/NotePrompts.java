package com.programacho.notemcpserver.application;

import com.programacho.notemcpserver.domain.Note;
import org.springaicommunity.mcp.annotation.McpArg;
import org.springaicommunity.mcp.annotation.McpPrompt;
import org.springframework.stereotype.Component;

import java.util.List;

import static io.modelcontextprotocol.spec.McpSchema.*;

@Component
public class NotePrompts {

    private final NoteService service;

    public NotePrompts(NoteService service) {
        this.service = service;
    }

    @McpPrompt(name = "summarize-note", description = "Summarize a note by its ID")
    public GetPromptResult summarizeNote(
            @McpArg(name = "id", description = "Note ID", required = true) String id
    ) {
        Note note = service.getNote(Long.parseLong(id));

        String prompt = """
                Please summarize the following note:
                
                Title: %s
                Tags: %s
                Content:
                %s
                """.formatted(note.getTitle(), note.getTags(), note.getContent());

        return new GetPromptResult(
                "Summarize a note",
                List.of(new PromptMessage(Role.USER, new TextContent(prompt)))
        );
    }
}
