package com.viasoft.desafioBackEnd.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.viasoft.desafioBackEnd.enums.EmailProvider;
import java.util.Arrays;
import com.viasoft.desafioBackEnd.model.EmailData;

@Service
public class ConvertDataService {

    private static final Logger logger = LoggerFactory.getLogger(ConvertDataService.class);

    private final ObjectMapper objectMapper;
    private final String mailIntegracao;
    private final AwsEmailAdapterService awsEmailAdapterService;
    private final OciEmailAdapterService ociEmailAdapterService;

    public ConvertDataService(
            ObjectMapper objectMapper,
            @Value("${mail.integracao}") String mailIntegracao,
            AwsEmailAdapterService awsEmailAdapterService,
            OciEmailAdapterService ociEmailAdapterService) {

        try {
            EmailProvider.valueOf(mailIntegracao.toUpperCase());
        } catch (IllegalArgumentException e) {
            String errorMessage = String.format(
                    "Configuração 'mail.integracao' inválida. O valor '%s' não é um provedor de e-mail válido. Valores aceitos: %s",
                    mailIntegracao, Arrays.toString(EmailProvider.values()));
            throw new IllegalStateException(errorMessage, e);
        }

        this.objectMapper = objectMapper;
        this.mailIntegracao = mailIntegracao;
        this.awsEmailAdapterService = awsEmailAdapterService;
        this.ociEmailAdapterService = ociEmailAdapterService;
    }

    public String convertData(EmailData emailData) {
        Object dataToSerialize;
        EmailProvider provider = EmailProvider.valueOf(mailIntegracao.toUpperCase());

        switch (provider) {
            case AWS:
                dataToSerialize = awsEmailAdapterService.adapt(emailData);
                break;
            case OCI:
                dataToSerialize = ociEmailAdapterService.adapt(emailData);
                break;
            default:
                String errorMessage = String.format("Provedor de e-mail '%s' não implementado.", mailIntegracao);
                throw new UnsupportedOperationException(errorMessage);
        }

        String emailResult = serializeData(dataToSerialize);
        logger.info("Dados do e-mail convertidos com sucesso: {}", emailResult);
        return emailResult;
    }

     private <T> String serializeData(T data) {
        try {
            return objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            String errorMessage = "Falha ao serializar os dados do e-mail para o formato JSON.";
            throw new RuntimeException(errorMessage, e);
        }
    }
}
