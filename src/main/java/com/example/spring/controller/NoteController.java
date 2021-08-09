package com.example.spring.controller;

import com.example.spring.aop.OperationLog;
import com.example.spring.entity.Note;
import com.example.spring.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NoteController {

  @Autowired
  private NoteRepository noteRepository;

  @RequestMapping("/notes")
  public String notes() {
    List<Note> notes = noteRepository.findAllNotes();
    if (notes.isEmpty()) {
      return "nothing";
    }
    return notes.toString();
  }

  @OperationLog(description = "query one note")
  @RequestMapping("/note/{noteId}")
  public String note(@PathVariable("noteId") Long noteId) {
    Note note = noteRepository.findNoteById(noteId);
    if (note == null) {
      return "not exist";
    }
    return note.toString();
  }
}
