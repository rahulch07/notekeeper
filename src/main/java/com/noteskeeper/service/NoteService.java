package com.noteskeeper.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.noteskeeper.repository.NoteRepository;

import jakarta.transaction.Transactional;

import com.noteskeeper.dto.CreateNoteRequest;
import com.noteskeeper.dto.NoteResponse;
import com.noteskeeper.entity.Note;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    public List<NoteResponse> getAll(Long userId) {
        return noteRepository.findAllByUserId(userId)
                             .stream()
                             .map(this::toNoteResponse)
                             .toList();
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
        note.setContent(request.Content());
        note.setUserId(1L);

        note =  noteRepository.save(note);

        return toNoteResponse(note);
    }

    @Transactional
    public NoteResponse updateNote(Long id, CreateNoteRequest request){
        Note note = noteRepository.getById(id);

        if(request.title() != null) note.setTitle(request.title());
        if(request.Content() != null) note.setContent(request.Content());

        //no need to save since it is transactional - dirty checking

        return toNoteResponse(note);
    }

    public void deleteNote(Long id){
        Note note = noteRepository.getById(id);

        noteRepository.delete(note);
    }

    private NoteResponse toNoteResponse(Note note) {
        NoteResponse response = new NoteResponse(note.getId(), note.getTitle(), note.getTitle(), note.getUserId(),
                note.getCreatedAt(), note.getUpdatedAt());
        return response;
    }
}
