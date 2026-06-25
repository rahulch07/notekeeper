package com.noteskeeper.repository;

import com.noteskeeper.entity.Note;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NoteRepository extends JpaRepository<Note, Long> {
    public Page<Note> findAllByUserId(Long userId, Pageable pageable);
}
