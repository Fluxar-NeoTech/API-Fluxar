package org.example.apifluxar.service;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
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

    public MessageResponseDTO sendTemplateMessage(String to) {
        String link = "https://areademulher.r7.com/wp-content/uploads/2022/07/tipos-de-macarrao-saiba-quais-sao-e-como-servir.jpg";

        Employee username = employeeService.findByEmail(to);

        String templateId = "d-71bbe8a900a340a68d3e58157eff16b0";

        Email from = new Email("suporte2025.neo.tech@gmail.com", "NeoTech");
        Email toEmail = new Email(to);

        Mail mail = new Mail();
        mail.setFrom(from);
        mail.setTemplateId(templateId);

        Personalization personalization = new Personalization();
        personalization.addTo(toEmail);
        personalization.addDynamicTemplateData("firstName", username.getFirstName());
        personalization.addDynamicTemplateData("reset_link",
                "https://areademulher.r7.com/wp-content/uploads/2022/07/tipos-de-macarrao-saiba-quais-sao-e-como-servir.jpg");

        mail.addPersonalization(personalization);
        System.out.println(">>> API KEY = " + sendgridApiKey);

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
