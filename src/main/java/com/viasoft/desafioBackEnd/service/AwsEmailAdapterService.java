package com.viasoft.desafioBackEnd.service;

import com.viasoft.desafioBackEnd.dto.EmailAwsDTO;
import com.viasoft.desafioBackEnd.model.EmailData;
import org.springframework.stereotype.Service;

/**
 * Serviço responsável por adaptar o modelo de domínio de e-mail (EmailData)
 * para o Data Transfer Object (DTO) específico para o provedor AWS
 * (EmailAwsDTO).
 */
@Service
public class AwsEmailAdapterService {

    /**
     * Adapta uma instância de {@link EmailData} para uma nova instância de
     * {@link EmailAwsDTO}.
     * 
     * @param emailData O objeto de dados de e-mail a ser adaptado.
     * @return uma nova instância de {@link EmailAwsDTO} com os dados mapeados, ou
     *         null se a entrada for null.
     */
     public EmailAwsDTO adapt(EmailData emailData) {
        // Lança NullPointerException se emailData for nulo, conforme esperado pelo teste existente.
        return new EmailAwsDTO(
                validateAndAdapt(emailData.getEmailDestinatario(), 45, "E-mail do Destinatário"),
                validateAndAdapt(emailData.getNomeDestinatario(), 60, "Nome do Destinatário"),
                validateAndAdapt(emailData.getEmailRemetente(), 45, "E-mail do Remetente"),
                validateAndAdapt(emailData.getAssunto(), 120, "Assunto"),
                validateAndAdapt(emailData.getConteudo(), 256, "Conteúdo")
        );
    }

    /**
     * Valida os dados de entrada contra nulidade, vazio e limite de caracteres.
     *
     * @param inputData O dado a ser validado.
     * @param limit O limite de caracteres.
     * @param fieldName O nome do campo para usar em mensagens de erro.
     * @return O dado de entrada se for válido.
     * @throws IllegalArgumentException se o dado for inválido.
     */
    private String validateAndAdapt(String inputData, int limit, String fieldName) {
        if (inputData == null || inputData.trim().isEmpty()) {
            throw new IllegalArgumentException(String.format("O campo '%s' é obrigatório.", fieldName));
        }
        if (inputData.length() > limit) {
            throw new IllegalArgumentException(String.format("O campo '%s' excede o limite de %d caracteres.", fieldName, limit));
        }
        return inputData;
    }
}
