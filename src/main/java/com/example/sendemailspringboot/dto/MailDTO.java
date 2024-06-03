package com.example.sendemailspringboot.dto;

import lombok.Data;

@Data
public class MailDTO {
    private String subject;
    private String message;
}
