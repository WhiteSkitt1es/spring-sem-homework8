package ru.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.task_handler.model.Note;

public interface NoteRepository extends JpaRepository<Note, Long> {


}
