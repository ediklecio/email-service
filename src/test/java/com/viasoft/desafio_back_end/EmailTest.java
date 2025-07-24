package com.viasoft.desafio_back_end;

import com.viasoft.desafioBackEnd.model.EmailData;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class EmailTest {

	private static Validator validator;

	@BeforeAll
	static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	void shouldSetAndGetEmailFields() {
		String recipientEmail = "destinatario@example.com";
		String recipientName = "Nome Destinatario";
		String senderEmail = "remetente@example.com";
		String subject = "Assunto do Email";
		String content = "Conteúdo do email.";

		EmailData email = new EmailData();
		email.setEmailDestinatario(recipientEmail);
		email.setNomeDestinatario(recipientName);
		email.setEmailRemetente(senderEmail);
		email.setAssunto(subject);
		email.setConteudo(content);

		assertEquals(recipientEmail, email.getEmailDestinatario());
		assertEquals(recipientName, email.getNomeDestinatario());
		assertEquals(senderEmail, email.getEmailRemetente());
		assertEquals(subject, email.getAssunto());
		assertEquals(content, email.getConteudo());
	}

	@Test
	void shouldCreateEmailWithConstructor() {
		EmailData email = new EmailData("destinatario@example.com", "Nome Destinatario", "remetente@example.com", "Assunto do Email", "Conteúdo do email.");

		assertEquals("destinatario@example.com", email.getEmailDestinatario());
		assertEquals("Nome Destinatario", email.getNomeDestinatario());
		assertEquals("remetente@example.com", email.getEmailRemetente());
		assertEquals("Assunto do Email", email.getAssunto());
		assertEquals("Conteúdo do email.", email.getConteudo());
	}

	@Test
	void shouldFailWhenAnyFieldIsBlank() {

		EmailData email = new EmailData("", "", "", "", "");
		Set<ConstraintViolation<EmailData>> violations = validator.validate(email);

		assertFalse(violations.isEmpty());
		assertEquals(5, violations.size());
	}

	@Test
	void shouldFailWhenEmailFormatIsInvalid() {
		EmailData email = new EmailData(
				"email-invalido",
				"Nome Destinatario",
				"remetente-invalido",
				"Assunto",
				"Conteúdo"
		);

		Set<ConstraintViolation<EmailData>> violations = validator.validate(email);

		assertFalse(violations.isEmpty());
		assertEquals(2, violations.size());

		assertTrue(violations.stream()
				.anyMatch(v -> v.getMessage().equals("O e-mail do destinatário deve ser um endereço de e-mail válido.")));

		assertTrue(violations.stream()
				.anyMatch(v -> v.getMessage().equals("O e-mail do remetente deve ser um endereço de e-mail válido.")));
	}
}