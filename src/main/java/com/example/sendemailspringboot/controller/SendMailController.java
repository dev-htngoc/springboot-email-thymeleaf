package com.example.sendemailspringboot.controller;

import com.example.sendemailspringboot.dto.MailDTO;
import com.example.sendemailspringboot.service.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mail")
public class SendMailController {
    @Autowired
    SendMailService sendMailService;

    @PostMapping("/send/{email}")
    public String sendMail(@PathVariable String email, @RequestBody MailDTO mailDTO){
        sendMailService.sendMail(email, mailDTO);
        return "Send successfully!";
    }
}
