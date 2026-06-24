package com.noteskeeper.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.noteskeeper.repository.NoteRepository;
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

    private NoteResponse toNoteResponse(Note note) {
        NoteResponse response = new NoteResponse(note.getId(), note.getTitle(), note.getTitle(), note.getUserId(),
                note.getCreatedAt(), note.getUpdatedAt());
        return response;
    }
}
