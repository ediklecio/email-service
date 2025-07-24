package com.viasoft.desafioBackEnd.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.viasoft.desafioBackEnd.model.EmailData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ConvertDataService {

    private static final Logger logger = LoggerFactory.getLogger(ConvertDataService.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String convertData(EmailData emailData) {
    }

   
}