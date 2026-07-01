package com.dah.email;

import java.util.Properties;

import com.dah.records.EmailMessage;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class SmtpEmailSenderAdapter implements EmailSender {

  private Session session;
  private String email;

  public SmtpEmailSenderAdapter(String email, String appPassword) {
    Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");

    Session session = Session.getInstance(props, new Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(email, appPassword);
      }
    });

    this.session = session;
    this.email = email;
  }

  @Override
  public void send(EmailMessage message) {
    MimeMessage email = new MimeMessage(session);

    try {
      email.setFrom(new InternetAddress(this.email));
      email.addRecipient(Message.RecipientType.TO, new InternetAddress(message.to()));
      email.setSubject(message.subject());
      email.setText(message.body());

      Transport.send(email);
    } catch (Exception e) {
      System.out.printf("[ERROR] email: %s", e.getMessage());
    }
  }

}
