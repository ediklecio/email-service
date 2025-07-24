package com.viasoft.desafioBackEnd.controller;

import com.viasoft.desafioBackEnd.model.EmailData;
import com.viasoft.desafioBackEnd.service.ConvertDataService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emails")
@Tag(name = "Emails", description = "Operações relacionadas ao tratamento para envio de email por provedor.")
public class EmailController {

    private static final Logger logger = LoggerFactory.getLogger(EmailController.class);

    private final ConvertDataService convertDataService;

    public EmailController(
        ConvertDataService convertDataService) {
        this.convertDataService = convertDataService;
    }

    @PostMapping
    @Operation(summary = "Recebe os dados do e-mail", description = "Endpoint para receber os dados do e-mail a ser enviado.")
    public ResponseEntity<String> receiveEmailData(@Valid @RequestBody EmailData emailData) {
        
        try {
            logger.info("Recebido novo pedido de e-mail para: {}", emailData.getEmailDestinatario());

            String serializedData = convertDataService.convertData(emailData);
            logger.info("Dados serializados: {}", serializedData);

            return ResponseEntity.ok(serializedData);
        } catch (RuntimeException e) {
            logger.error("Falha ao processar a solicitação de e-mail.", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar sua solicitação: " + e.getMessage());
        }
    }
}