package com.vti.shoppee.service.iml;

import com.vti.shoppee.service.IMailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailSenderService implements IMailSenderService {
    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        // Logic other
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@baeldung.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
        // Logic other
    }

    @Override
    public void sendMessageWithHtml(String to, String subject, String text) {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setFrom("noreply@baeldung.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
         // text có thể để dạng html = true
//        FileSystemResource file
//                = new FileSystemResource(new File(pathToAttachment));
//        helper.addAttachment("Invoice", file);// File có thể là hình ảnh hoặc pdf,...
        emailSender.send(message);

    }
}
