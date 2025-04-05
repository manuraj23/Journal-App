package com.JournelApp.JournelApp;

import com.JournelApp.JournelApp.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
@SpringBootTest
public class MailSenderServiceTest {
    @Autowired
    private EmailService emailService;

    @Test
    void testSendMail(){
        emailService.sendEmail("manuraj0642#gmail.com","Test Subject", "Test Body");
    }
}
