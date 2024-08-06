package br.com.alura.adopet.api.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class EmailServiceProducaoTest {
    @InjectMocks
    private EmailServiceProducao emailServiceProducao;

    @Mock
    private JavaMailSender emailSender;

    @Test
    void enviarEmail_deveEnviarEmailCorretamente() {
        // Arrange
        String to = "recipient@example.com";
        String subject = "Test Subject";
        String message = "Test Message";

        // Act
        emailServiceProducao.enviarEmail(to, subject, message);

        // Assert
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("adopet@email.com.br");
        email.setTo(to);
        email.setSubject(subject);
        email.setText(message);

        verify(emailSender).send(email);
    }
}