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
    @DisplayName("Deve retornar null quando EmailData de entrada for null")
    void adapt_quandoEmailDataForNulo_deveRetornarNull() {
        EmailAwsDTO result = adapterService.adapt(null);

        assertNull(result);
    }

    @Test
    @DisplayName("Deve mapear campos nulos de EmailData para campos nulos em EmailAwsDTO")
    void adapt_quandoCamposForemNulos_deveMapearComoNulos() {
        EmailData emailData = new EmailData(null, null, null, null, null);
        EmailAwsDTO result = adapterService.adapt(emailData);

        assertNotNull(result);
        assertAll("Verifica se campos nulos são mapeados corretamente",
                () -> assertNull(result.getRecipient()),
                () -> assertNull(result.getRecipientName()),
                () -> assertNull(result.getSender()),
                () -> assertNull(result.getSubject()),
                () -> assertNull(result.getContent())
        );
    }

    @Test
    @DisplayName("Deve truncar campos que excedem os limites de caracteres")
    void adapt_quandoCamposExcedemLimite_deveTruncar() {
        // Arrange
        EmailData emailData = new EmailData(
                createString('a', 46), // limit 45
                createString('b', 61),  // limit 60
                createString('c', 46),    // limit 45
                createString('d', 121), // limit 120
                createString('e', 257)  // limit 256
        );
        EmailAwsDTO result = adapterService.adapt(emailData);

        assertNotNull(result);
        assertAll("Verifica se todos os campos foram truncados para o limite correto",
                () -> assertEquals(45, result.getRecipient().length(), "Email do destinatário deve ser truncado"),
                () -> assertEquals(60, result.getRecipientName().length(), "Nome do destinatário deve ser truncado"),
                () -> assertEquals(45, result.getSender().length(), "Email do remetente deve ser truncado"),
                () -> assertEquals(120, result.getSubject().length(), "Assunto deve ser truncado"),
                () -> assertEquals(256, result.getContent().length(), "Conteúdo deve ser truncado")
        );
    }

    @Test
    @DisplayName("Não deve truncar campos que estão exatamente no limite de caracteres")
    void adapt_quandoCamposEstaoNoLimite_naoDeveTruncar() {
        EmailData emailData = new EmailData(createString('a', 45), createString('b', 60), createString('c', 45), createString('d', 120), createString('e', 256));
        EmailAwsDTO result = adapterService.adapt(emailData);

        assertNotNull(result);
        assertAll("Verifica se campos no limite não são truncados",
                () -> assertEquals(45, result.getRecipient().length()),
                () -> assertEquals(60, result.getRecipientName().length()),
                () -> assertEquals(45, result.getSender().length()),
                () -> assertEquals(120, result.getSubject().length()),
                () -> assertEquals(256, result.getContent().length())
        );
    }
}