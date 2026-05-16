package com.kosmo.findpassword.controller;

import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final SecureRandom secureRandom = new SecureRandom();

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    // 랜덤한 인증번호 생성 (대소문자, 숫자, 특수문자 포함, 8자리)
    public String generateVerificationCode() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            code.append(characters.charAt(randomIndex));  // 랜덤 문자 선택
        }
        return code.toString();
    }

    // 이메일로 인증번호 보내기
    public void sendVerificationEmail(String toEmail, String verificationCode) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("your-email@gmail.com");  // 발신자 이메일 주소 (구글 이메일)
            helper.setTo(toEmail);  // 수신자 이메일 주소
            helper.setSubject("코스런 서비스 이메일 인증번호 비밀번호찾기");

            // 이메일 본문 내용 (서비스명, 인증번호 포함) - 스타일을 인라인으로 추가
            String text = "<html>"
                    + "<body style='font-family: Arial, sans-serif; background-color: #f4f4f9; margin: 0; padding: 0;'>"
                    + "<div style='max-width: 600px; margin: 30px auto; padding: 20px; background-color: #ffffff; border-radius: 8px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);'>"
                    + "    <h2 style='color: #ffb84d; text-align: center; font-size: 24px;'>코스런 비밀번호찾기 이메일 인증</h2>"  // 밝은 주황색 적용
                    + "    <p style='font-size: 16px; color: #333333; line-height: 1.6;'>안녕하세요, 코스런 서비스입니다.</p>"
                    + "    <p style='font-size: 16px; color: #333333; line-height: 1.6;'>아래의 인증번호를 입력하여 이메일 인증을 완료해주세요.</p>"
                    + "    <div style='text-align: center; padding: 10px 20px; background-color: #ffb84d; border-radius: 8px; margin: 20px 0; width: fit-content; max-width: 250px; margin-left: auto; margin-right: auto;'>"
                    + "        <p style='font-size: 22px; font-weight: bold; color: #ffffff; margin: 0;'>"
                    + verificationCode
                    + "        </p>"
                    + "    </div>"
                    + "    <p style='font-size: 16px; color: #333333; line-height: 1.6;'>인증번호는 5분 이내에 사용해야 하며 비밀번호 변경부탁드립니다!.</p>"
                    + "    <div style='text-align: center; margin-top: 20px; font-size: 14px; color: #888888;'>"
                    + "        <p>감사합니다!</p>"
                    + "        <p><a href='https://kotoki-service.com' style='color: #ffb84d; text-decoration: none;'>코스런 서비스 웹사이트</a></p>"
                    + "    </div>"
                    + "</div>"
                    + "</body>"
                    + "</html>";
            helper.setText(text, true);  // HTML 형식으로 이메일 내용 전송

            javaMailSender.send(message);  // 이메일 전송
        } catch (Exception e) {
            throw new RuntimeException("이메일 전송에 실패했습니다.", e);
        }
    }
}
