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
                adaptData(emailData.getEmailDestinatario(), 40),
                adaptData(emailData.getNomeDestinatario(), 50),
                adaptData(emailData.getEmailRemetente(), 40),
                adaptData(emailData.getAssunto(), 100),
                adaptData(emailData.getConteudo(), 250)
        );
    }

    private String adaptData(String inputData, int limit) {
        if (inputData == null) {
            return null;
        }
        return inputData.length() > limit ? inputData.substring(0, limit) : inputData;
    }
}