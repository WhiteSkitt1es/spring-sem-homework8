package ru.app;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.gb.task_handler.model.Note;
import ru.gb.task_handler.repository.NoteRepository;
import ru.gb.task_handler.service.NoteService;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NoteServiceModelTest {

    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private NoteService service;

    @Test
    public void addNoteHappyFlow() {
        // Блок предусловия

        String description = "Test Description";

        // when
        Note savedNote = service.addNote(description);

        // then
        ArgumentCaptor<Note> noteCaptor = ArgumentCaptor.forClass(Note.class);
        verify(noteRepository).save(noteCaptor.capture());

        Note capturedNote = noteCaptor.getValue();
        assertThat(capturedNote.getDescription()).isEqualTo(description);
    }

    @Test
    public void updateNoteExceptionThrowsFlow() {
        // Блок предусловия
        Long id = 1L;
        String description = "Test Description";
        given(noteRepository.findById(id)).willReturn(Optional.empty());

        // when & then
        assertThrows(NullPointerException.class, () -> service.updateNote(id,description));
    }
}

