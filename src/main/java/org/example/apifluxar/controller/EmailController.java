package org.example.apifluxar.controller;

import jakarta.validation.Valid;
import org.example.apifluxar.dto.email.SendEmailRequestDTO;
import org.example.apifluxar.dto.message.MessageResponseDTO;
import org.example.apifluxar.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("api/email")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public ResponseEntity<MessageResponseDTO> sendEmail(@RequestBody @Validated SendEmailRequestDTO emailRequestDTO) {
        MessageResponseDTO message= emailService.sendTemplateMessage(emailRequestDTO);
        return ResponseEntity.ok(message);
    }
}
