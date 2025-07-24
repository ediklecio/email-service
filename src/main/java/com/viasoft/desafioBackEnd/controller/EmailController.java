package com.viasoft.desafioBackEnd.controller;

import com.viasoft.desafioBackEnd.model.EmailData;
import com.viasoft.desafioBackEnd.service.ConvertDataService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emails")
@Tag(name = "Emails", description = "Operações relacionadas ao tratamento para envio de email por provedor.")
public class EmailController {

    private final ConvertDataService convertDataService;

    public EmailController(
        ConvertDataService convertDataService) {
        this.convertDataService = convertDataService;
    }

    @PostMapping
    @Operation(summary = "Recebe os dados do e-mail", description = "Endpoint para receber os dados do e-mail a ser enviado.")
    @ResponseStatus(HttpStatus.NO_CONTENT) 
    public ResponseEntity<String> receiveEmailData(@Valid @RequestBody EmailData emailData) {
        
        try {
            String serializedData = convertDataService.convertData(emailData);
            return ResponseEntity.noContent()
                                 .header("Serialized-Data", serializedData)
                                 .build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar sua solicitação: " + e.getMessage());
        } 
    }
}