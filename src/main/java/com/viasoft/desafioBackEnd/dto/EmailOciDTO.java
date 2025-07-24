package com.viasoft.desafioBackEnd.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) para representar os dados de um e-mail a ser enviado
 * através do provedor Oracle Cloud Infrastructure (OCI).
 */
public class EmailOciDTO {
    @NotBlank(message = "O e-mail do destinatário não pode estar em branco.")
    @Email(message = "O e-mail do destinatário deve ser um endereço de e-mail válido.")
    @Size(max = 40, message = "O e-mail do destinatário deve ter no máximo 40 caracteres.")
    private String recipientEmail;

    @NotBlank(message = "O nome do destinatário não pode estar em branco.")
    @Size(max = 50, message = "O nome do destinatário deve ter no máximo 50 caracteres.")
    private String recipientName;

    @NotBlank(message = "O e-mail do remetente não pode estar em branco.")
    @Email(message = "O e-mail do remetente deve ser um endereço de e-mail válido.")
    @Size(max = 40, message = "O e-mail do remetente deve ter no máximo 40 caracteres.")
    private String senderEmail;

    @NotBlank(message = "O assunto do e-mail não pode estar em branco.")
    @Size(max = 100, message = "O assunto do e-mail deve ter no máximo 100 caracteres.")
    private String subject;

    @NotBlank(message = "O conteúdo do e-mail não pode estar em branco.")
    @Size(max = 250, message = "O conteúdo do e-mail deve ter no máximo 250 caracteres.")
    private String body;

    public EmailOciDTO(String recipientEmail, String recipientName, String senderEmail, String subject, String body) {
        this.recipientEmail = recipientEmail;
        this.recipientName = recipientName;
        this.senderEmail = senderEmail;
        this.subject = subject;
        this.body = body;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}