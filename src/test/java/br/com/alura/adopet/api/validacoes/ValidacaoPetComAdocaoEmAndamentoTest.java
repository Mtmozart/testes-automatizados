package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.BDDMockito.given;

@SpringBootTest
class ValidacaoPetComAdocaoEmAndamentoTest {

    @Mock
    private AdocaoRepository adocaoRepository;
    @Mock
    private Pet pet;
    @Mock
    private Adocao adocao;
    @InjectMocks
    private ValidacaoPetComAdocaoEmAndamento validacao;
    @Mock
    private SolicitacaoAdocaoDto dto;

    @Test
    @DisplayName("Should exception if the status is 'Aguardando Avalidação")
    public void scenario01() {
        //arrange
        given(adocaoRepository.existsByPetIdAndStatus(
                dto.idPet(),
                StatusAdocao.AGUARDANDO_AVALIACAO)).willReturn(true);
        Assertions.assertThrows(ValidacaoException.class, () -> validacao.validar(dto));
    }

    @Test
    @DisplayName("Should denied if sent validation exception")
    public void scenario02() {
        //arrange
        given(adocaoRepository.existsByPetIdAndStatus(
                dto.idPet(),
                StatusAdocao.AGUARDANDO_AVALIACAO)).willReturn(false);
        Assertions.assertDoesNotThrow(() -> validacao.validar(dto));
    }

}