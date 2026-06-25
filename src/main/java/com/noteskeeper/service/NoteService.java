package com.noteskeeper.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.noteskeeper.repository.NoteRepository;

import jakarta.transaction.Transactional;

import com.noteskeeper.dto.CreateNoteRequest;
import com.noteskeeper.dto.NoteResponse;
import com.noteskeeper.dto.UpdateNoteRequest;
import com.noteskeeper.entity.Note;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    public Page<NoteResponse> getAll(Long userId, Pageable pageable) {
        return noteRepository.findAllByUserId(userId, pageable)
                             .map(this::toNoteResponse);
    }

    public NoteResponse getNote(Long userId) {
        return noteRepository.findById(userId)
                             .map(this::toNoteResponse)
                             .orElseThrow(null);
    }

    @Transactional
    public NoteResponse createNote(CreateNoteRequest request){
        Note note = new Note();
        note.setTitle(request.title());
        note.setContent(request.content());
        note.setUserId(1L);
        System.out.print("#### create note request" + request);
        System.out.print("#### create note" + note);
        note =  noteRepository.save(note);

        return toNoteResponse(note);
    }

    @Transactional
    public NoteResponse updateNote(Long id, UpdateNoteRequest request){
        Note note = noteRepository.getById(id);

        if(request.title() != null) note.setTitle(request.title());
        if(request.content() != null) note.setContent(request.content());

        //no need to save since it is transactional - dirty checking
        System.out.print("#### update note request" + request);
        System.out.print("#### update note" + note);

        return toNoteResponse(note);
    }

    public void deleteNote(Long id){
        Note note = noteRepository.getById(id);

        noteRepository.delete(note);
    }

    private NoteResponse toNoteResponse(Note note) {
        NoteResponse response = new NoteResponse(note.getId(), note.getTitle(), note.getContent(), note.getUserId(),
                note.getCreatedAt(), note.getUpdatedAt());
        return response;
    }
}
