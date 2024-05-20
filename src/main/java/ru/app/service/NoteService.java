package ru.app.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.task_handler.aspect.AfterUserAction;
import ru.gb.task_handler.aspect.TrackUserAction;
import ru.gb.task_handler.model.Note;
import ru.gb.task_handler.repository.NoteRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
@AllArgsConstructor
public class NoteService {

    private final NoteRepository repository;

    private final AtomicLong count = new AtomicLong();

    public List<Note> getAllNotes() {
        return repository.findAll();
    }

    @TrackUserAction
    public Note addNote(String description) {
        Note note = new Note();
        note.setId(count.incrementAndGet());
        note.setDescription(description);
        note.setDateOfCreation(LocalDateTime.now());
        return repository.save(note);
    }

    public Note getNoteById(Long id) {
        return repository.findById(id).orElseThrow(null);
    }

    @AfterUserAction
    public Note updateNote(Long id, String description) {
        Note note = repository.findById(id).orElse(null);
        if (note != null) {
            note.setDescription(description);
            repository.save(note);
        }
        return note;
    }

    public void removeNote(Long id) {
        Note note = repository.findById(id).orElse(null);
        assert note != null;
        repository.delete(note);
    }
}
