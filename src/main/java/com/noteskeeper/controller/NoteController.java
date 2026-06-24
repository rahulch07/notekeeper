package com.noteskeeper.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import com.noteskeeper.dto.NoteResponse;
import com.noteskeeper.service.NoteService;

import lombok.RequiredArgsConstructor;

@Controller("/api/v1/notes")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    public List<NoteResponse> getAll(@RequestParam Long userId){
        return noteService.getAll(userId);
    }
}
