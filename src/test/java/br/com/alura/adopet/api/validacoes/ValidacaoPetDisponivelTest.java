package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ValidacaoPetDisponivelTest {

    @InjectMocks
    private ValidacaoPetDisponivel validacao;

    @Mock
    private PetRepository petRepository;

    @Mock
    private Pet pet;

    @Mock
    private SolicitacaoAdocaoDto dto;

    @Test
    @DisplayName("Should be permitted to solicit pet adoption.")
    void cenario01() {
        // Arrange
        BDDMockito.given(dto.idPet()).willReturn(1L); // Supondo que o idPet é um Long e o valor é 1L
        BDDMockito.given(petRepository.getReferenceById(1L)).willReturn(pet);
        BDDMockito.given(pet.getAdotado()).willReturn(false);

        // Act and Assert
        Assertions.assertDoesNotThrow(() -> validacao.validar(dto));
    }

    @Test
    @DisplayName("Should not be permitted to solicit pet adoption.")
    void cenario02() {
        // Arrange
        BDDMockito.given(dto.idPet()).willReturn(1L); // Supondo que o idPet é um Long e o valor é 1L
        BDDMockito.given(petRepository.getReferenceById(1L)).willReturn(pet);
        BDDMockito.given(pet.getAdotado()).willReturn(true);

        // Act and Assert
        Assertions.assertThrows(ValidacaoException.class, () -> validacao.validar(dto));
    }
}
