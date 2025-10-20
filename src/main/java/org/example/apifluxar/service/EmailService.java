package org.example.apifluxar.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.example.apifluxar.dto.email.SendEmailRequestDTO;
import org.example.apifluxar.dto.message.MessageResponseDTO;
import org.example.apifluxar.model.Employee;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    final JavaMailSender emailSender;
    final EmployeeService employeeService;

    public EmailService(JavaMailSender emailSender, EmployeeService employeeService) {
        this.employeeService = employeeService;
        this.emailSender = emailSender;
    }

    public MessageResponseDTO sendTemplateMessage(SendEmailRequestDTO emailRequestDTO) {
        // variables
        String to = emailRequestDTO.getEmail();
        String link = "https://areademulher.r7.com/wp-content/uploads/2022/07/tipos-de-macarrao-saiba-quais-sao-e-como-servir.jpg";
        Employee username = employeeService.findByEmail(to);

        try {
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setTo(to);
            helper.setSubject("Redefinição de Senha");

            String htmlBody = """
            <html>
                <body style="font-family: Arial, sans-serif; background-color: #f9f9f9; padding: 20px;">
                  <div style="max-width: 500px; margin: auto; background: white; border-radius: 10px; padding: 20px; box-shadow: 0 0 10px rgba(0,0,0,0.1);">
                      <h2 style="color: #46005A;">Olá, %s!</h2>
                      <p>Recebemos um pedido para redefinir sua senha.</p>
                      <p>Clique no botão abaixo para continuar o processo:</p>
                      <p style="text-align: center; margin: 30px 0;">
                          <a href="%s"
                             style="background-color: #46005A; color: white; padding: 12px 25px;
                                    text-decoration: none; border-radius: 5px; display: inline-block;">
                              Redefinir Senha
                          </a>
                      </p>
                      <p style="color: #555;">Se você não solicitou essa alteração, apenas ignore este e-mail.</p>
                      <hr style="border: none; border-top: 1px solid #ddd;">
                      <p style="font-size: 12px; color: #888;">Este é um e-mail automático, por favor não responda.</p>
                  </div>
                </body>
            </html>
        """.formatted(username.getFirstName(), link);

            helper.setText(htmlBody, true);
            emailSender.send(mimeMessage);

            return new MessageResponseDTO("Email enviado com sucesso para " + to);

        } catch (MessagingException e) {
            return new MessageResponseDTO("Falha ao enviar email para " + to);
        }
    }
}
