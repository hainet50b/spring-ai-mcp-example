package com.programacho.notemcpserver.application;

import com.programacho.notemcpserver.domain.Note;
import com.programacho.notemcpserver.domain.NoteRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Tool(description = "Create a new note.")
    public Note createNote(
            @ToolParam(description = "Title of the note") String title,
            @ToolParam(description = "Content of the note") String content,
            @ToolParam(description = "Tags of the note, separated by commas. (e.g. java,spring,mcp)") String tags
    ) {
        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);
        note.setTags(tags);

        return noteRepository.save(note);
    }

    @Tool(description = "List all notes.")
    public List<Note> listNotes() {
        return noteRepository.findAll();
    }

    @Tool(description = "Fetch a note by ID.")
    public Note getNote(
            @ToolParam(description = "ID of the note to fetch") Long id
    ) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Note not found: id=" + id));
    }

    @Tool(description = "Update a note by ID.")
    public Note updateNote(
            @ToolParam(description = "ID of the note to update") Long id,
            @ToolParam(description = "Title of the note") String title,
            @ToolParam(description = "Content of the note") String content,
            @ToolParam(description = "Tags of the note, separated by commas. (e.g. java,spring,mcp)") String tags
    ) {
        return noteRepository.findById(id)
                .map(n -> {
                    n.setTitle(title);
                    n.setContent(content);
                    n.setTags(tags);

                    return noteRepository.save(n);
                })
                .orElseThrow(() -> new NoSuchElementException("Note not found: id=" + id));
    }

    @Tool(description = "Delete a note by ID.")
    public String deleteNote(
            @ToolParam(description = "ID of the note to delete") Long id
    ) {
        if (!noteRepository.existsById(id)) {
            throw new NoSuchElementException("Note not found: id=" + id);
        }

        noteRepository.deleteById(id);

        return "Note deleted: id=" + id;
    }
}
