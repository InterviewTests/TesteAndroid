package br.banco.services.contact.message;

import android.util.Log;

import java.security.Security;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.banco.services.contact.email.ByteArrayDataSource;
import br.banco.services.contact.email.JSSEProvider;


public class GMailSenderV2 extends javax.mail.Authenticator{


    public String Mailhost = "smtp.gmail.com";
    public String User ="busqe.irece@gmail.com";
    public String Password ="Zeus2030";

    public javax.mail.Session Session;

    //public

    public String FullName;
    public String Email;
    public String Phone; // Long
    public String EmailSave; // integer

    public String EmailCopy;
    public String Subject;
    public String Body;



    public String getMailhost() {
        return Mailhost;
    }

    public void setMailhost(String mailhost) {
        Mailhost = mailhost;
    }

    public javax.mail.Session getSession() {
        return Session;
    }

    public void setSession(javax.mail.Session session) {
        Session = session;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmailSave() {
        return EmailSave;
    }

    public void setEmailSave(String emailSave) {
        EmailSave = emailSave;
    }

    public String getEmailCopy() {
        return EmailCopy;
    }

    public void setEmailCopy(String emailCopy) {
        EmailCopy = emailCopy;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getBody() {
        return Body;
    }

    public void setBody(String body) {
        Body = body;
    }


    //config

    static {
        Security.addProvider(new JSSEProvider());
    }

    public GMailSenderV2() {

        this.User = User;
        this.Password = Password;

        this.FullName = FullName;
        this.Email  = Email;
        this.Phone  = Phone;
        this.EmailSave  = EmailSave;

        this.EmailCopy  = EmailCopy;
        this.Subject  = Subject;
        this.Body  = Body;


        Properties props = new Properties();

        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", Mailhost);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        props.put("mail.smtp.starttls.enable","true");

        props.setProperty("mail.smtp.quitwait", "false");

        Session = Session.getDefaultInstance(props, this);
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(User, Password);
    }

    public synchronized void sendMail(
            String subject,
            String body,
            String sender,
            String recipients)

     throws Exception {

        MimeMessage message = new MimeMessage(Session);
        DataHandler handler = new DataHandler(new ByteArrayDataSource(body.getBytes(),
                "text/plain"));

        message.setSender(new InternetAddress(sender));
        message.setSubject(subject);
        message.setDataHandler(handler);

        if (recipients.indexOf(',') > 0)
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
        else
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipients));

        Transport.send(message);

        Log.e("CONTACT", "VIEW / GMailSenderV2 -> SUCCESS");
    }





}
