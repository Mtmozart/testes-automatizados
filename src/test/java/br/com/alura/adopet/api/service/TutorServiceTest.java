package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AtualizacaoTutorDto;
import br.com.alura.adopet.api.dto.CadastroTutorDto;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TutorServiceTest {

    @InjectMocks
    private TutorService tutorService;

    @Mock
    private TutorRepository tutorRepository;
    @Mock
    private Tutor tutor;

    private CadastroTutorDto dto;

    private AtualizacaoTutorDto atualizacaoTutorDto;

    @Test
    void cadastrar() {
        this.dto = new CadastroTutorDto("nome", "(69) 99999-9999", "email@email.com");
        tutorService.cadastrar(dto);

        ArgumentCaptor<Tutor> tutorCaputarado = ArgumentCaptor.forClass(Tutor.class);
        verify(tutorRepository).save(tutorCaputarado.capture());
        Tutor tutorSalvo = tutorCaputarado.getValue();
        assertEquals("nome", tutorSalvo.getNome());
        assertEquals("(69) 99999-9999", tutorSalvo.getTelefone());
        assertEquals("email@email.com", tutorSalvo.getEmail());


    }

    @Test
    void atualizar() {
        // Arrange
        Tutor tutor = new Tutor();
        AtualizacaoTutorDto dto = new AtualizacaoTutorDto(1L, "novo nome", "(69) 99999-9999", "email@email.com");
        given(tutorRepository.getReferenceById(dto.id())).willReturn(tutor);

        // Act
        tutorService.atualizar(dto);

        // Assert
        ArgumentCaptor<Tutor> tutorCaptor = ArgumentCaptor.forClass(Tutor.class);
        verify(tutorRepository, times(1)).save(tutorCaptor.capture());

        Tutor tutorSalvo = tutorCaptor.getValue();
        assertEquals("(69) 99999-9999", tutorSalvo.getTelefone());
        assertEquals("email@email.com", tutorSalvo.getEmail());
        assertEquals("novo nome", tutorSalvo.getNome());
    }
}