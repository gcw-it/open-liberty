/*******************************************************************************
 * Copyright (c) 2017,2020 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package TestingApp.SMTP;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.mail.smtp.SMTPMessage;

@WebServlet("/SMTPInlineServlet")
public class SMTPInlineServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Address[] in;
    Session session;
    Object jndiConstant;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String testSubjectString = "Sent from Liberty JavaMail";
        String testBodyString = "Test mail sent by GreenMail";
        Object jndiConstant;

        try {
            jndiConstant = new InitialContext().lookup("TestingApp/smtp_port");
        } catch (NamingException e) {
            System.out.println("Failed to look-up 'TestingApp/smtp_port': "+e.getMessage());
            e.printStackTrace(System.out);
            throw new RuntimeException(e);
        }

        System.out.println("jndiConstant="+jndiConstant);
        String smtpPort = Integer.toString((Integer) jndiConstant);
        System.out.println("TestingApp/smtp_port="+smtpPort);

        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "localhost");
        props.setProperty("user", "smtp@testserver.com");
        props.setProperty("password", "smtpPa$$word4U2C");
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("from", "smtp@testserver.com");

        session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                PasswordAuthentication passwordAuthentication = new PasswordAuthentication("smtp@testserver.com"
                                                                                          ,"smtpPa$$word4U2C"
                                                                                          );
                return passwordAuthentication;
            }
        });

        session.setDebug(true);
        SMTPMessage smtpMessage = new SMTPMessage(session);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(session.getProperty("user")));
            message.setRecipients(Message.RecipientType.TO,
                                  InternetAddress.parse(session.getProperty("from")));
            message.setSubject(testSubjectString);
            message.setText(testBodyString);

            Transport transport = session.getTransport("smtp");
            transport.connect();
            Transport.send(message);

        } catch (MessagingException e) {
            System.out.println("Mail session properties: "+session.getProperties());
            e.printStackTrace(System.out);
            throw new RuntimeException(e);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}
