package view;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class Mail {
    //setup mail server properties
    //draft an email
    //send email

    Session newSession = null;
    MimeMessage mimeMessage = null;
    public static void main(String args[]) throws  AddressException, MessagingException {
        Mail mail = new Mail();
        mail.setupServerProperties();
        mail.draftEmail();
        mail.sendEmail();
    }

    private void sendEmail() throws MessagingException {
        String fromUser = "sangeethkasun12345@gmail.com";
        String fromUserPassword = "rsyokrzfxvohhrtr";
        String emailHost = "smtp.gmail.com";
        Transport transport = newSession.getTransport("smtp");
        transport.connect(emailHost,fromUser,fromUserPassword);
        transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());
        transport.close();
        System.out.println("Email Successfully Sent");
    }

    private MimeMessage draftEmail() throws AddressException, MessagingException {
        String[] emailReceipients = {"vikumdinujaya@gmail.com"};
        String emailSubject = "Test Mail";
        String emailBody = "Test Body of my  email";
        mimeMessage = new MimeMessage(newSession);

        for(int i=0;i< emailReceipients.length;i++){
            mimeMessage.addRecipient(Message.RecipientType.TO,new InternetAddress(emailReceipients[i]));
        }
        mimeMessage.setSubject(emailSubject);


        //Create mimemessage
        //Create Message body parts
        //Add message body parts --> multipart
        //finally add multipart to messagecontent i.e. mimeMessage object

        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent(emailBody,"html/text");
        MimeMultipart multipart = new MimeMultipart();
        multipart.addBodyPart(bodyPart);
        mimeMessage.setContent(multipart);
        return mimeMessage;
    }

    private void setupServerProperties() {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.port","587");
        properties.put("mail.smtp.auth","ture");
        properties.put("mail.smtp.starttls.enable","true");
        newSession = Session.getDefaultInstance(properties,null);
    }


}
