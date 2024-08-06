package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.CadastroPetDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.TipoPet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {
    @InjectMocks
    private PetService petService;

    @Mock
    private PetRepository petRepository;

    @Mock
    private Abrigo abrigo;

    private CadastroPetDto dto;
    @Mock
    private Pet pet;

    @Test
    void buscarPetsDisponiveis() {
        //act
        petService.buscarPetsDisponiveis();
        //Assert
        then(petRepository).should().findAllByAdotadoFalse();
    }

    @Test
    void cadastrarPet() {
        //Arrange
        this.dto = new CadastroPetDto(TipoPet.GATO, "NOME", "SRD", 10, "CARAMELO", 10.0F);
        //act
        petService.cadastrarPet(abrigo, dto);
        // Assert
        ArgumentCaptor<Pet> petCaptor = ArgumentCaptor.forClass(Pet.class);
        verify(petRepository).save(petCaptor.capture());
        Pet petSalvo = petCaptor.getValue();

        assertEquals(TipoPet.GATO, petSalvo.getTipo());
        assertEquals("NOME", petSalvo.getNome());
        assertEquals("SRD", petSalvo.getRaca());
        assertEquals(10, petSalvo.getIdade());
        assertEquals("CARAMELO", petSalvo.getCor());
        assertEquals(10.0F, petSalvo.getPeso());
        assertEquals(abrigo, petSalvo.getAbrigo());

    }
}