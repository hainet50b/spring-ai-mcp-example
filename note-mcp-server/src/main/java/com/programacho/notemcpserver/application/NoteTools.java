package com.programacho.notemcpserver.application;

import com.programacho.notemcpserver.domain.Note;
import com.programacho.notemcpserver.domain.NoteRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class NoteTools {

    private final NoteRepository repository;

    public NoteTools(NoteRepository repository) {
        this.repository = repository;
    }

    @Tool(name = "create-note", description = "Create a new note.")
    public Note createNote(
            @ToolParam(description = "Title of the note") String title,
            @ToolParam(description = "Content of the note") String content,
            @ToolParam(description = "Tags of the note, separated by commas. (e.g. java,spring,mcp)") String tags
    ) {
        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);
        note.setTags(tags);

        return repository.save(note);
    }

    @Tool(name = "list-notes", description = "List all notes.")
    public List<Note> listNotes() {
        return repository.findAll();
    }

    @Tool(name = "get-note", description = "Fetch a note by ID.")
    public Note getNote(
            @ToolParam(description = "ID of the note to fetch") Long id
    ) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Note not found: id=" + id));
    }

    @Tool(name = "update-note", description = "Update a note by ID.")
    public Note updateNote(
            @ToolParam(description = "ID of the note to update") Long id,
            @ToolParam(description = "Title of the note") String title,
            @ToolParam(description = "Content of the note") String content,
            @ToolParam(description = "Tags of the note, separated by commas. (e.g. java,spring,mcp)") String tags
    ) {
        return repository.findById(id)
                .map(m -> {
                    m.setTitle(title);
                    m.setContent(content);
                    m.setTags(tags);

                    return repository.save(m);
                })
                .orElseThrow(() -> new NoSuchElementException("Note not found: id=" + id));
    }

    @Tool(name = "delete-note", description = "Delete a note by ID.")
    public String deleteNote(
            @ToolParam(description = "ID of the note to delete") Long id
    ) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Note not found: id=" + id);
        }

        repository.deleteById(id);

        return "Note deleted: id=" + id;
    }
}
