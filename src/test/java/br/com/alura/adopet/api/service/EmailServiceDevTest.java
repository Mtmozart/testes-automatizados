package br.com.alura.adopet.api.service;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class EmailServiceDevTest {

    @Test
    void enviarEmail() {
        // Arrange
        EmailServiceDev emailService = new EmailServiceDev();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Act
        emailService.enviarEmail("to@example.com", "Assunto", "Mensagem");

        // Restora a saída padrão
        System.setOut(originalOut);

        // Assert
        String output = outputStream.toString();
        assertTrue(output.contains("Enviando email fake"));
        assertTrue(output.contains("Destinatario: to@example.com"));
        assertTrue(output.contains("Assunto: Assunto"));
        assertTrue(output.contains("Mensagem: Mensagem"));
    }
}