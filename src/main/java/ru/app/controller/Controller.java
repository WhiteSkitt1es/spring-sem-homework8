package ru.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.task_handler.model.Note;
import ru.gb.task_handler.service.NoteService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/notes")
public class Controller {

    private final NoteService service;

    private final FileGateway fileGateway;

    @GetMapping
    public ResponseEntity<List<Note>> getNotes() {
        return new ResponseEntity<>(service.getAllNotes(), HttpStatus.OK);
    }

    @PostMapping("/add-note")
    public ResponseEntity<Note> addTask(@RequestParam String description) {
        fileGateway.writeFile("notes", description);
        return new ResponseEntity<>(service.addNote(description), HttpStatus.CREATED);
    }


    @PutMapping("update-note/{id}/{description}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id, @PathVariable String description) {
        return new ResponseEntity<>(service.updateNote(id, description), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        Note note;
        try {
            note = service.getNoteById(id);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Note());
        }
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    @DeleteMapping("delete-note/{id}")
    public ResponseEntity<Note> deleteNoteById(@PathVariable Long id) {
        service.removeNote(id);
        return ResponseEntity.ok().build();
    }

}
