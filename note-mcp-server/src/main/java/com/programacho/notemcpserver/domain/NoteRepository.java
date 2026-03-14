package com.programacho.notemcpserver.domain;

import java.util.List;
import java.util.Optional;

public interface NoteRepository {

    Note save(Note note);

    List<Note> findAll();

    Optional<Note> findById(Long id);

    boolean existsById(Long id);

    void deleteById(Long id);
}
