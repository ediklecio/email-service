package com.viasoft.desafio_back_end.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class EmailData {

    @NotBlank(message = "O e-mail do destinatário não pode estar em branco.")
    @Email(message = "O e-mail do destinatário deve ser um endereço de e-mail válido.")
    private String emailDestinatario;

    @NotBlank(message = "O nome do destinatário não pode estar em branco.")
    private String nomeDestinatario;

    @NotBlank(message = "O e-mail do remetente não pode estar em branco.")
    @Email(message = "O e-mail do remetente deve ser um endereço de e-mail válido.")
    private String emailRemetente;

    @NotBlank(message = "O assunto não pode estar em branco.")
    private String assunto;

    @NotBlank(message = "O conteúdo não pode estar em branco.")
    private String conteudo;

    public EmailData() {
    }

    public EmailData(String emailDestinatario, String nomeDestinatario, String emailRemetente, String assunto, String conteudo) {
        this.emailDestinatario = emailDestinatario;
        this.nomeDestinatario = nomeDestinatario;
        this.emailRemetente = emailRemetente;
        this.assunto = assunto;
        this.conteudo = conteudo;
    }

    public String getEmailDestinatario() {
        return emailDestinatario;
    }

    public void setEmailDestinatario(String emailDestinatario) {
        this.emailDestinatario = emailDestinatario;
    }

    public String getNomeDestinatario() {
        return nomeDestinatario;
    }

    public void setNomeDestinatario(String nomeDestinatario) {
        this.nomeDestinatario = nomeDestinatario;
    }

    public String getEmailRemetente() {
        return emailRemetente;
    }

    public void setEmailRemetente(String emailRemetente) {
        this.emailRemetente = emailRemetente;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
}