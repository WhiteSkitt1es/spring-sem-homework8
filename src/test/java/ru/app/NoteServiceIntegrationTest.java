package ru.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.gb.task_handler.model.Note;
import ru.gb.task_handler.repository.NoteRepository;
import ru.gb.task_handler.service.NoteService;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest
public class NoteServiceIntegrationTest {

    @MockBean
    private NoteRepository noteRepository;

    @Autowired
    private NoteService noteService;

    @Test
    void removeNoteCorrectFlow() {
        // given
        Long id = 1L;
        Note note = new Note();
        note.setId(id);
        given(noteRepository.findById(id)).willReturn(Optional.of(note));
        doNothing().when(noteRepository).delete(note);

        // when
        noteService.removeNote(id);

        // then
        verify(noteRepository).delete(note);
    }
}
