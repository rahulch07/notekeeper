package com.noteskeeper.dto;

import java.time.LocalDateTime;

public record NoteResponse(Long id, String name, String content, Long userId, LocalDateTime createdAt, LocalDateTime updatedAt) {}
