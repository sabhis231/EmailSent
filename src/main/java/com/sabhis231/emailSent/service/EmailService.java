/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sabhis231.emailSent.service;

import com.sabhis231.emailSent.utils.PropertyUtils;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.json.simple.JSONObject;

/**
 *
 * @author Sabhis231
 */
public class EmailService {

    public static JSONObject sentEmail(String emailTo, String ccTo, String bccTo, String subject, String message) {
        JSONObject object = new JSONObject();

        try {
            String host = new PropertyUtils().GetPropertySec("host");
            String user = new PropertyUtils().GetPropertySec("user");
            String pass = new PropertyUtils().GetPropertySec("pass");
            String auth = new PropertyUtils().GetPropertySec("auth");
            String port = new PropertyUtils().GetPropertySec("port");
            String starttls_enable = new PropertyUtils().GetPropertySec("starttls_enable");
            String starttls_required = new PropertyUtils().GetPropertySec("starttls_required");
            String[] emailToArray = emailTo.split(" ");
            String[] ccToArray = ccTo.split(" ");
            String[] bccToArray = bccTo.split(" ");
            String from = user;
            boolean sessionDebug = false;
            Properties props = System.getProperties();
            props.put("mail.smtp.starttls.enable", starttls_enable);
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", port);
            props.put("mail.smtp.auth", auth);
            props.put("mail.smtp.starttls.required", starttls_required);

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            //To recipient  
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
            //CC recipient  
            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccTo));
            //BCC recipient  
            msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(bccTo));
            //Subjet
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            //Content   
            msg.setContent(message, "text/html; charset=utf-8");
            Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, user, pass);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            object.put("responseCode", 1);
            object.put("msg", "message send successfully");
        } catch (Exception ex) {
            object.put("responseCode", 0);
            object.put("msg", "message not send successfully");
            ex.printStackTrace();
        }
        return object;
    }
}
