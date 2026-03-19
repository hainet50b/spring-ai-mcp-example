package com.programacho.notemcpserver.application;

import com.programacho.notemcpserver.domain.Note;
import com.programacho.notemcpserver.domain.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class NoteService {

    private final NoteRepository repository;

    public NoteService(NoteRepository repository) {
        this.repository = repository;
    }

    public Note createNote(Note note) {
        return repository.save(note);
    }

    public List<Note> listNotes() {
        return repository.findAll();
    }

    public Note getNote(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Note not found: id=" + id));
    }

    public Boolean noteExists(Long id) {
        return repository.existsById(id);
    }

    public Note updateNote(Note note) {
        return repository.save(note);
    }

    public void deleteNote(Long id) {
        repository.deleteById(id);
    }
}
