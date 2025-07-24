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

    public ConvertDataService(ObjectMapper objectMapper, @Value("${mail.integracao}") String mailIntegracao) {
        try {
            EmailProvider.valueOf(mailIntegracao.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException(
                    String.format("Configuração 'mail.integracao' inválida. O valor '%s' não é um provedor de e-mail válido. Valores aceitos: %s",
                            mailIntegracao, Arrays.toString(EmailProvider.values())), e);
        }
        this.objectMapper = objectMapper;
        this.mailIntegracao = mailIntegracao;
    }

    public String convertData(EmailData emailData) {
        // Exemplo de como usar a propriedade injetada
        logger.info("Usando a integração de e-mail: {}", mailIntegracao);

        
        try {
            return objectMapper.writeValueAsString(emailData);
        } catch (JsonProcessingException e) {
            // É uma boa prática logar o erro e/ou lançar uma exceção de runtime.
            logger.error("Erro ao serializar EmailData para JSON", e);
            // Lançar uma exceção de runtime para que o Spring possa tratá-la (e.g., retornar um erro 500)
            throw new RuntimeException("Erro ao processar os dados do e-mail.", e);
        }
    }
}
