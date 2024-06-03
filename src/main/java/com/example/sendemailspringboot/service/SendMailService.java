package com.example.sendemailspringboot.service;

import com.example.sendemailspringboot.dto.MailDTO;
import com.example.sendemailspringboot.dto.UserDTO;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@Slf4j
@RequiredArgsConstructor
public class SendMailService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public void sendMail(String toEmail, MailDTO mailDTO){
        try {
            UserDTO userDTO = UserDTO.builder()
                    .username("htngoc14741")
                    .fullName("Hoang Tuan Ngoc")
                    .build();

            MimeMessage message = mailSender.createMimeMessage();
            Context context = new Context();
            context.setVariable("user", userDTO);
            String html = templateEngine.process("email-template", context);

            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(toEmail);
            helper.setText(html, true);
            helper.setSubject(mailDTO.getSubject());
            helper.setFrom(fromEmail);

            mailSender.send(message);
        }catch (Exception ex){
            log.error("send email" + ex.getMessage());
        }
    }
}
