package hg.jh.luko6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;


    // 사용자 정보를 인자로 받는 메소드로 확장
    public void sendContactEmail(String name, String fromEmail, String phone, String messageContent) {
        String[] to = { "goorm94@naver.com", "haberde97@gmail.com" }; // 이메일을 받을 주소들
        String subject = "New Contact from " + name; // 메일 제목
        String text = buildEmailContent(name, fromEmail, phone, messageContent); // 이메일 본문 조합

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail); // 발신자 이메일 주소
        message.setTo(to); // 수신자 이메일 주소
        message.setSubject(subject); // 메일 제목
        message.setText(text); // 메일 내용
        mailSender.send(message);
    }

    // 이메일 본문을 조합하는 메소드
    private String buildEmailContent(String name, String email, String phone, String message) {
        return String.format("Name: %s\nEmail: %s\nPhone: %s\nMessage:\n%s",
                name, email, phone, message);
    }
}