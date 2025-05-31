package com.example.AIRecognize.authentication.verify.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@RequiredArgsConstructor
@Component
public class MailListenerEvent implements ApplicationListener<MailListener> {
    private final JavaMailSender mailSender;
    @Override
    public void onApplicationEvent(MailListener event) {
        try{
            String subject = "Email verification";
            String senderName = "Luna Universe";

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
            messageHelper.setFrom("liendhhha140217@gmail.com", senderName);
            messageHelper.setTo(event.getEmail());
            messageHelper.setSubject(subject);

            String mailContent = """
                      <div style="margin:0; padding:0; font-family: 'Apple SD Gothic Neo', sans-serif; background-color: #f5f5f5;">
                        <table align="center" width="100%" cellpadding="0" cellspacing="0" style="padding: 40px 0;">
                          <tr>
                            <td align="center">
                              <table width="400" cellpadding="0" cellspacing="0"
                                style="background-color: #fff7f0; border-radius: 16px; overflow: hidden; box-shadow: 0 4px 12px rgba(0,0,0,0.05); border: 1px #D5AA8B solid;">
                                <tr>
                                  <td style="background-color: #101282; text-align: center; padding: 14px;">
                                    <span style="font-size: 40px; font-weight: bold; color: #fff;">AI Recognization</span>
                                  </td>
                                </tr>
                                <tr>
                                  <td style="padding: 32px 24px; background-color: #edf4fa;">
                                    <h2 style="margin: 0 0 12px; font-size: 18px; color: #222; font-weight: bold;">Please check email verification!</h2>
                                    <p style="margin: 0 0 10px; font-size: 14px; color: #555;">This is your verified code.</p>
                                    <div
                                      style="background-color: #FFFBF6; padding: 14px 0; text-align: center; font-size: 28px; font-weight: bold; color: #000; border-radius: 20px; width: 140px; margin: auto;">
                                      """ + String.valueOf(event.getCode()) + """
                                    </div>
                                    <p style="font-size: 13px; color: #666; line-height: 1.6;">
                                      The verification code is only valid for 15 minutes.<br>
                                      Please do not reply to this email.
                                    </p>
                                  </td>
                                </tr>
                                <tr>
                                  <td
                                    style="padding: 16px 24px; background-color: #deeaff; font-size: 11px; color: #2b1dc0; text-align: left;">
                                    <strong>LUNA UNIVERSE</strong><br>
                                    Vietnam, Hanoi<br>
                                    Business ID number 123-234-566 / TEL 010-1234-3456<br>
                                    Copyright © lunauniverse.vn. All rights reserved.
                                  </td>
                                </tr>
                              </table>
                            </td>
                          </tr>
                        </table>
                      </div>                                       
                    """;
            messageHelper.setText(mailContent, true);
            mailSender.send(message);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
