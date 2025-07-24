package com.viasoft.desafioBackEnd.dto;

/**
 * Data Transfer Object (DTO) para representar os dados de um e-mail a ser enviado
 * através do provedor Oracle Cloud Infrastructure (OCI).
 */
public class EmailOciDTO {
    private String recipientEmail;
    private String recipientName;
    private String senderEmail;
    private String subject;
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