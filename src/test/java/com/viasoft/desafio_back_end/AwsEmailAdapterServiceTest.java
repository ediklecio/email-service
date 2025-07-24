package com.viasoft.desafio_back_end;

import com.viasoft.desafioBackEnd.dto.EmailAwsDTO;
import com.viasoft.desafioBackEnd.model.EmailData;
import com.viasoft.desafioBackEnd.service.AwsEmailAdapterService;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("AwsEmailAdapterService Test")
class AwsEmailAdapterServiceTest {

    private AwsEmailAdapterService adapterService;

    @BeforeEach
    void setUp() {
        adapterService = new AwsEmailAdapterService();
    }

    private String createString(char character, int length) {
        return String.valueOf(character).repeat(length);
    }

    @Test
    @DisplayName("Deve adaptar EmailData para EmailAwsDTO com sucesso quando os dados estão dentro dos limites")
    void adapt_quandoDadosValidos_deveRetornarDtoMapeado() {
        EmailData emailData = new EmailData(
                "destinatario@example.com",
                "Nome Destinatario",
                "remetente@example.com",
                "Assunto do E-mail",
                "Corpo do e-mail."
        );
        EmailAwsDTO result = adapterService.adapt(emailData);

        assertNotNull(result);
        assertAll("Verifica todos os campos do DTO adaptado",
                () -> assertEquals("destinatario@example.com", result.getRecipient()),
                () -> assertEquals("Nome Destinatario", result.getRecipientName()),
                () -> assertEquals("remetente@example.com", result.getSender()),
                () -> assertEquals("Assunto do E-mail", result.getSubject()),
                () -> assertEquals("Corpo do e-mail.", result.getContent())
        );
    }

    @Test
    @DisplayName("Deve retornar excessão quando EmailData de entrada for null")
    void adapt_quandoEmailDataForNulo_deveRetornarNull() {

        assertThrows(NullPointerException.class, () -> {
            adapterService.adapt(null);
        });
    }

    @Test
    @DisplayName("Deve lançar exceção quando campo excede o limite de caracteres")
    void adapt_quandoCampoExcedeLimite_deveLancarIllegalArgumentException() {
        // Arrange
        EmailData emailData = new EmailData(
                createString('b', 34) + "@example.com", // > 45
                createString('b', 71),  // Nome Destinatário > 60
                createString('b', 34) + "@example.com", // > 45
                createString('b', 121),  // Nome Destinatário > 120
                createString('b', 257)  // Content > 256
        );

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            adapterService.adapt(emailData);
        });

        String expectedMessage = "O campo 'E-mail do Destinatário' excede o limite de 45 caracteres.";
        assertEquals(expectedMessage, exception.getMessage());
    }
}