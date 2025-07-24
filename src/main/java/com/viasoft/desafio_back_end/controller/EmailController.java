package com.viasoft.desafio_back_end.controller;

import com.viasoft.desafio_back_end.model.EmailData;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emails")
public class EmailController {

    private static final Logger logger = LoggerFactory.getLogger(EmailController.class);

    @PostMapping
    public ResponseEntity<String> receiveEmailData(@Valid @RequestBody EmailData emailData) {
        logger.info("Recebido novo pedido de e-mail para: {}", emailData.getEmailDestinatario());
        logger.info("Assunto: {}", emailData.getAssunto());

        // Simula o processamento e retorna uma resposta de sucesso.
        return ResponseEntity.ok("Dados do e-mail recebidos com sucesso.");
    }
}