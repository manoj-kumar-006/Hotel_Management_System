package com.mk.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendBookingEmail(String to, String hotel, String room,
                                 String checkIn, String checkOut) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to); // ✅ dynamic user email
        message.setSubject("Booking Confirmation ✅");

        message.setText(
                "Your booking is confirmed!\n\n" +
                        "Hotel: " + hotel + "\n" +
                        "Room: " + room + "\n" +
                        "Check-In: " + checkIn + "\n" +
                        "Check-Out: " + checkOut + "\n\n" +
                        "Thank you for booking with us!"
        );

        mailSender.send(message);
    }
}