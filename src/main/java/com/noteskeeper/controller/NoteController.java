package com.noteskeeper.controller;

import com.noteskeeper.repository.NoteRepository;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

import com.noteskeeper.dto.CreateNoteRequest;
import com.noteskeeper.dto.NoteResponse;
import com.noteskeeper.service.NoteService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller("/api/v1/notes")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @GetMapping
    public ResponseEntity<List<NoteResponse>> getAll(@RequestParam Long userId){
        return ResponseEntity.ok(noteService.getAll(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteResponse> getNote(@PathVariable Long id){
        return ResponseEntity.ok(noteService.getNote(id));
    }

    @PostMapping
    public ResponseEntity<NoteResponse> createNote(@RequestBody @Valid CreateNoteRequest request) {
        NoteResponse note = noteService.createNote(request);
        return ResponseEntity.created(URI.create("/api/v1/notes/" + note.id())).body(note);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteResponse> updateNote(@PathVariable Long id, @RequestBody @Valid CreateNoteRequest request) {
        NoteResponse note = noteService.updateNote(id, request);
        return ResponseEntity.created(URI.create("/api/v1/notes/" + note.id())).body(note);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<NoteResponse> deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
        return ResponseEntity.noContent().build();
    }
    
}
