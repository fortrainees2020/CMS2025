package com.email;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class ShareMail {

    private Session newSession = null;
    private MimeMessage draftMessage = null;

    public static void main(String[] args) {
        // Explicitly ensure modern TLS protocols are enabled
        System.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        System.setProperty("jdk.tls.client.protocols", "TLSv1.2");

        ShareMail mail = new ShareMail();
        mail.setupServerProperties();
        mail.draftMail();
        mail.sendMail();
    }

    private void setupServerProperties() {
        Properties properties = new Properties();

        // Gmail SMTP configuration using STARTTLS
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

        newSession = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // Replace with your Gmail email and App Password
            	//Enable the 2 step authentication  from manageaccount -> secuirty
            	//  https://myaccount.google.com/apppasswords - Generate app password 
                return new PasswordAuthentication("fortrainees2020@gmail.com", "apppassword");
            }
        });
    }

    private void draftMail() {
        String[] emailRecipients = { "fortrainees2020@gmail.com" };
        String emailSubject = "Test Email from Java Program";
        String emailBody = "Hello! This is a test email sent using Gmail SMTP with STARTTLS and Java 11.";

        try {
            draftMessage = new MimeMessage(newSession);
            draftMessage.setFrom(new InternetAddress("fortrainees2020@gmail.com"));
            draftMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(String.join(",", emailRecipients)));
            draftMessage.setSubject(emailSubject);
            draftMessage.setText(emailBody);
        } catch (MessagingException e) {
            System.out.println("Error drafting email: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void sendMail() {
        try {
            Transport.send(draftMessage);
            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            System.out.println("Error sending email: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

//Scroll down

