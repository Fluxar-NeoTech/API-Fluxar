package org.example.apifluxar.controller;

import org.example.apifluxar.dto.message.MessageResponseDTO;
import org.example.apifluxar.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("api/email")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("send/{email}")
    public ResponseEntity<MessageResponseDTO> sendEmail(@PathVariable String email) {
        MessageResponseDTO message= emailService.sendTemplateMessage(email);
        return ResponseEntity.ok(message);
    }
}
