package org.example.apifluxar.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.example.apifluxar.dto.email.SendEmailRequestDTO;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import org.example.apifluxar.dto.employee.EmployeeResponseDTO;
import org.example.apifluxar.dto.message.MessageResponseDTO;
import org.example.apifluxar.model.Employee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailService {

    @Value("${sendgrid.api-key}")
    private String sendgridApiKey;

    private final EmployeeService employeeService;

    public EmailService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public MessageResponseDTO sendTemplateMessage(SendEmailRequestDTO emailRequestDTO) {
        String to = emailRequestDTO.getEmail();
        String link = "https://web-site-fluxar.vercel.app/redefinePassword";

        EmployeeResponseDTO username = employeeService.findByEmail(to);

        String templateId = "d-ec7e20f549d8468691a49db823d0b2ab";

        Email from = new Email("suporte2025.neo.tech@gmail.com", "NeoTech");
        Email toEmail = new Email(to);

        Mail mail = new Mail();
        mail.setFrom(from);
        mail.setTemplateId(templateId);
        Personalization personalization = new Personalization();
        personalization.addTo(toEmail);
        personalization.setSubject("Redefinição de senha");
        personalization.addDynamicTemplateData("firstName", username.getFirstName());
        personalization.addDynamicTemplateData("reset_link",
                "https://web-site-fluxar.vercel.app/redefinePassword");

        mail.addPersonalization(personalization);

        SendGrid sg = new SendGrid(sendgridApiKey);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sg.api(request);

            if (response.getStatusCode() >= 200 && response.getStatusCode() < 300) {
                return new MessageResponseDTO("Email enviado com sucesso para " + to);
            } else {
                return new MessageResponseDTO("Erro ao enviar email: " + response.getBody());
            }

        } catch (IOException e) {
            return new MessageResponseDTO("Falha ao enviar email: " + e.getMessage());
        }
    }
}
