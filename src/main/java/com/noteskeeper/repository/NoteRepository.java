package com.noteskeeper.repository;

import com.noteskeeper.entity.Note;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface NoteRepository extends JpaRepository<Note, Long> {
    public List<Note> findAllByUserId(Long userId);
}
