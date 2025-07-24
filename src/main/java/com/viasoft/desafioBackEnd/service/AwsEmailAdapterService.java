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
     * Adapta uma instância de {@link EmailData} para uma nova instância de {@link EmailAwsDTO}.
     * @param emailData O objeto de dados de e-mail a ser adaptado.
     * @return uma nova instância de {@link EmailAwsDTO} com os dados mapeados, ou null se a entrada for null.
     */
    public EmailAwsDTO adapt(EmailData emailData) {
        if (emailData == null) {
            return null;
        }
        return new EmailAwsDTO(
            adaptData(emailData.getEmailDestinatario(), 45),
            adaptData(emailData.getNomeDestinatario(), 60),
            adaptData(emailData.getEmailRemetente(), 45),
            adaptData(emailData.getAssunto(), 120),
            adaptData(emailData.getConteudo(), 256)
        );
    }

    private String adaptData(String inputData, int limit) {
        if (inputData == null) {
            return null;
        }
        return inputData.length() > limit ? inputData.substring(0, limit) : inputData;
    }
}
