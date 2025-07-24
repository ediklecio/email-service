package com.viasoft.desafioBackEnd.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EmailAwsDTO {

    @NotBlank(message = "O e-mail do destinatário não pode estar em branco.")
    @Email(message = "O e-mail do destinatário deve ser um endereço de e-mail válido.")
    @Size(max = 45, message = "O e-mail do destinatário deve ter no máximo 45 caracteres.")
    private String recipient;

    @NotBlank(message = "O nome do destinatário não pode estar em branco.")
    @Size(max = 60, message = "O nome do destinatário deve ter no máximo 60 caracteres.")
    private String recipientName;

    @NotBlank(message = "O e-mail do remetente não pode estar em branco.")
    @Email(message = "O e-mail do remetente deve ser um endereço de e-mail válido.")
    @Size(max = 45, message = "O e-mail do remetente deve ter no máximo 45 caracteres.")
    private String sender;

    @NotBlank(message = "O assunto do e-mail não pode estar em branco.")
    @Size(max = 120, message = "O assunto do e-mail deve ter no máximo 120 caracteres.")
    private String subject;

    @NotBlank(message = "O conteúdo do e-mail não pode estar em branco.")
    @Size(max = 256, message = "O conteúdo do e-mail deve ter no máximo 256 caracteres.")
    private String content;

    // Construtor padrão (vazio)
    public EmailAwsDTO() {
    }

    // Construtor com todos os argumentos
    public EmailAwsDTO(String recipient, String recipientName, String sender, String subject, String content) {
        this.recipient = recipient;
        this.recipientName = recipientName;
        this.sender = sender;
        this.subject = subject;
        this.content = content;
    }

    // Getters e Setters

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}