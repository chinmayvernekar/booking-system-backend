package com.parkingbookingsystem;

import com.parkingbookingsystem.locationdetails.ParkingLocationsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    private final static Logger logger = LoggerFactory.getLogger(EmailService.class);

    public void sendSimpleEmail(String toEmail,String body,String subject) {

        SimpleMailMessage mailmessage = new SimpleMailMessage();

        mailmessage.setFrom("chinmay.vernekar49@gmail.com");
        mailmessage.setTo(toEmail);
        mailmessage.setText(body);
        mailmessage.setSubject(subject);
        mailSender.send(mailmessage);
        logger.info("MAIL SENT" + "TO: " + toEmail + " Subject: " + subject);
    }
}
