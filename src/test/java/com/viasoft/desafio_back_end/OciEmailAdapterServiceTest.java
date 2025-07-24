package com.viasoft.desafio_back_end;

import com.viasoft.desafioBackEnd.dto.EmailOciDTO;
import com.viasoft.desafioBackEnd.model.EmailData;
import com.viasoft.desafioBackEnd.service.OciEmailAdapterService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("OciEmailAdapterService Test")
class OciEmailAdapterServiceTest {

    private OciEmailAdapterService adapterService;

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
                "Corpo do e-mail."
        );

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
                () -> assertEquals("Corpo do e-mail.", result.getBody())
        );
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
    @DisplayName("Deve mapear campos nulos de EmailData para campos nulos em EmailOciDTO")
    void adapt_quandoCamposForemNulos_deveMapearComoNulos() {
        // Arrange
        EmailData emailData = new EmailData(null, null, null, null, null);

        // Act
        EmailOciDTO result = adapterService.adapt(emailData);

        // Assert
        assertNotNull(result);
        assertAll("Verifica se campos nulos são mapeados corretamente",
                () -> assertNull(result.getRecipientEmail()),
                () -> assertNull(result.getRecipientName()),
                () -> assertNull(result.getSenderEmail()),
                () -> assertNull(result.getSubject()),
                () -> assertNull(result.getBody())
        );
    }

    @Test
    @DisplayName("Deve truncar campos que excedem os limites de caracteres do OCI")
    void adapt_quandoCamposExcedemLimite_deveTruncar() {
        // Arrange
        EmailData emailData = new EmailData(
                createString('a', 41), // limit 40
                createString('b', 51),  // limit 50
                createString('c', 41),    // limit 40
                createString('d', 101), // limit 100
                createString('e', 251)  // limit 250
        );

        // Act
        EmailOciDTO result = adapterService.adapt(emailData);

        // Assert
        assertNotNull(result);
        assertAll("Verifica se todos os campos foram truncados para o limite correto do OCI",
                () -> assertEquals(40, result.getRecipientEmail().length(), "Email do destinatário deve ser truncado"),
                () -> assertEquals(50, result.getRecipientName().length(), "Nome do destinatário deve ser truncado"),
                () -> assertEquals(40, result.getSenderEmail().length(), "Email do remetente deve ser truncado"),
                () -> assertEquals(100, result.getSubject().length(), "Assunto deve ser truncado"),
                () -> assertEquals(250, result.getBody().length(), "Conteúdo deve ser truncado")
        );
    }

    @Test
    @DisplayName("Não deve truncar campos que estão exatamente no limite de caracteres do OCI")
    void adapt_quandoCamposEstaoNoLimite_naoDeveTruncar() {
        // Arrange
        EmailData emailData = new EmailData(createString('a', 40), createString('b', 50), createString('c', 40), createString('d', 100), createString('e', 250));

        // Act
        EmailOciDTO result = adapterService.adapt(emailData);

        // Assert
        assertNotNull(result);
        assertAll("Verifica se campos no limite não são truncados",
                () -> assertEquals(40, result.getRecipientEmail().length()),
                () -> assertEquals(50, result.getRecipientName().length()),
                () -> assertEquals(40, result.getSenderEmail().length()),
                () -> assertEquals(100, result.getSubject().length()),
                () -> assertEquals(250, result.getBody().length())
        );
    }
}