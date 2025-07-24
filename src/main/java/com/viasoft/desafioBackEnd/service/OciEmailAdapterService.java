package com.viasoft.desafioBackEnd.service;

import com.viasoft.desafioBackEnd.dto.EmailOciDTO;
import com.viasoft.desafioBackEnd.model.EmailData;
import org.springframework.stereotype.Service;

/**
 * Serviço responsável por adaptar o modelo de domínio de e-mail (EmailData)
 * para o Data Transfer Object (DTO) específico para o provedor OCI
 * (EmailOciDTO).
 */
@Service
public class OciEmailAdapterService {

    /**
     * Adapta uma instância de {@link EmailData} para uma nova instância de {@link EmailOciDTO}.
     *
     * @param emailData O objeto de dados de e-mail a ser adaptado.
     * @return uma nova instância de {@link EmailOciDTO} com os dados mapeados, ou null se a entrada for null.
     */
    public EmailOciDTO adapt(EmailData emailData) {
        if (emailData == null) {
            return null;
        }
        return new EmailOciDTO(
                adaptData(emailData.getEmailDestinatario(), 40, "E-mail destinatário"),
                adaptData(emailData.getNomeDestinatario(), 50, "Nome destinatário"),
                adaptData(emailData.getEmailRemetente(), 40, "E-mail remetente"),
                adaptData(emailData.getAssunto(), 100, "Assunto"),
                adaptData(emailData.getConteudo(), 250, "Conteúdo")
        );
    }

    private String adaptData(String inputData, int limit, String fieldName) {
        if (inputData == null || inputData.trim().isEmpty()) {
            throw new IllegalArgumentException(String.format("O campo '%s' é obrigatório.", fieldName));
        }
        if (inputData.length() > limit) {
            throw new IllegalArgumentException(String.format("O campo '%s' excede o limite de %d caracteres.", fieldName, limit));
        }
        return inputData;
    }
}