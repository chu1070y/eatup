package ga.eatup.partner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import lombok.Setter;

@Component
public class EmailServiceImpl{

    @Setter(onMethod_ = @Autowired)
    public JavaMailSender emailSender;

    public void sendSimpleMessage(String name, String email, String phone, String msg) {
    	
        SimpleMailMessage message = new SimpleMailMessage();
        
        message.setTo("chu1070y@naver.com");
        message.setFrom(email);
          
        message.setSubject("1대1 문의사항 - " + name);
        message.setText("\n\n[name] : " + name + "\n[phone] : " + phone + "\n[Email] : " + email + msg);
        message.setReplyTo("answldls9401@hanmail.net");
        emailSender.send(message);

    }
}