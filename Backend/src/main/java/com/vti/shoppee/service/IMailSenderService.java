package com.vti.shoppee.service;

public interface IMailSenderService {
    void sendSimpleMessage(String to, String subject, String text);
    void sendMessageWithHtml(String to, String subject, String text);
}
