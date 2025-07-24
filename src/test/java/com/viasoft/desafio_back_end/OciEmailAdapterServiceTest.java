package com.viasoft.desafio_back_end;

import com.viasoft.desafioBackEnd.dto.EmailOciDTO;
import com.viasoft.desafioBackEnd.model.EmailData;
import com.viasoft.desafioBackEnd.service.OciEmailAdapterService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.logging.Logger;

import org.hibernate.validator.internal.util.logging.Log_.logger;

@DisplayName("OciEmailAdapterService Test")
class OciEmailAdapterServiceTest {

    private OciEmailAdapterService adapterService;
        Logger logger = Logger.getLogger(OciEmailAdapterServiceTest.class.getName());

    @BeforeEach
    void setUp() {
        // Assumindo que OciEmailAdapterService será criado
        adapterService = new OciEmailAdapterService();
    }

    /**
     * Helper para criar strings longas para os testes de limite.
     */
    private String createString(char character, int length) {
        return String.valueOf(character).repeat(length);
    }

    @Test
    @DisplayName("Deve adaptar EmailData para EmailOciDTO com sucesso quando os dados estão dentro dos limites")
    void adapt_quandoDadosValidos_deveRetornarDtoMapeado() {
        // Arrange
        EmailData emailData = new EmailData(
                "destinatario@example.com",
                "Nome Destinatario",
                "remetente@example.com",
                "Assunto do E-mail",
                "Corpo do e-mail.");

        // Act
        // Assumindo que o método adapt() retorna um EmailOciDTO
        EmailOciDTO result = adapterService.adapt(emailData);

        // Assert
        assertNotNull(result);
        // Assumindo que EmailOciDTO terá estes getters
        assertAll("Verifica todos os campos do DTO adaptado",
                () -> assertEquals("destinatario@example.com", result.getRecipientEmail()),
                () -> assertEquals("Nome Destinatario", result.getRecipientName()),
                () -> assertEquals("remetente@example.com", result.getSenderEmail()),
                () -> assertEquals("Assunto do E-mail", result.getSubject()),
                () -> assertEquals("Corpo do e-mail.", result.getBody()));
    }

    @Test
    @DisplayName("Deve retornar null quando EmailData de entrada for null")
    void adapt_quandoEmailDataForNulo_deveRetornarNull() {
        // Act
        EmailOciDTO result = adapterService.adapt(null);

        // Assert
        assertNull(result);
    }

    @Test
    @DisplayName("Deve lançar exceção quando campo excede o limite de caracteres")
    void adapt_quandoCampoExcedeLimite_deveLancarExcecao() {
        // Arrange
        EmailData emailData = new EmailData(
                createString('a', 41), // Excede o limite de 40 caracteres
                "Nome Destinatario",
                "remetente@example.com",
                createString('a', 101), // Excede o limite de 100 caracteres
                "Corpo do e-mail.");

        assertThrows(IllegalArgumentException.class, () -> {
            adapterService.adapt(emailData);
        });
    }

}