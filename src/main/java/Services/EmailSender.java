package Services;


import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;


public class EmailSender {
    public static void sendEmail(String text, String to ){

        String user = "username"; //change accordingly
        String password= "password"; //change accordingly

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user,password);
                    }
                });

        //Compose the message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject("Task Manager notification");
            message.setText(text);

            //send the message
            Transport.send(message);
            System.out.println("message sent successfully...");

        } catch (MessagingException e) {e.printStackTrace();}
    }
}