package com.select;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class LuckyEmail {
    public static void main(String[] args) throws Exception {
        if (args.length != 2)
        {
            throw new Exception("Need to pass argument of size 2");
        }
        int groupSize = Integer.valueOf(args[0]);
        List<String> emails = Files.newBufferedReader(Path.of(System.getProperty("user.dir")+ File.separator, args[1])).lines()
                .map(l -> l.split(",")[0]).collect(Collectors.toList());
        Collections.shuffle(emails);
        List<String> luckyEmails = emails.subList(0, Math.min(emails.size(), groupSize));
        System.out.println("Lucky emails: " + luckyEmails);

        //sentEmail(luckyEmails);
    }
/*
    private static void sentEmail(List<String> luckyEmails) throws Exception {
        Message message = new MimeMessage(getSession());
        message.setFrom(new InternetAddress("your-email@gmail.com"));
        String to = luckyEmails.toString().replace("[", "").replace("]", "");
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject("Subject");
        message.setText("Body of the email");

        Transport.send(message);
    }

    private static Session getSession() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("src/main/resources/smtp.properties"));

        return Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("gmail-username", "gmail-password");
            }
        });
    }*/
}
