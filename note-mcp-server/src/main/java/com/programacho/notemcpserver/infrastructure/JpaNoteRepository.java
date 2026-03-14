package com.programacho.notemcpserver.infrastructure;

import com.programacho.notemcpserver.domain.Note;
import com.programacho.notemcpserver.domain.NoteRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaNoteRepository extends JpaRepository<Note, Long>, NoteRepository {
}
