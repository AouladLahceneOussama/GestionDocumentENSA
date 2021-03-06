
import java.io.*;
import java.util.Properties;
import java.util.regex.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JOptionPane;

public class Email {
    // private String Toemail;
    public static JFrame load = new JFrame("Sending");

    public boolean isEmail(String email) {

        String regex = "^[a-z]+[.][a-z]+@etu[.]uae[.]ac[.]ma$";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void SendEmail(String email, String etat, File FileOut)
            throws IOException, InvocationTargetException, InterruptedException {

        final String username = "exemple@gmail.com";
        final String password = "123456";

        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("ayatanssaien@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Testing Subject");
            message.setText("PFA");

            Multipart multipart = new MimeMultipart();

            if (etat.equals("accept")) {
                // Attachment body part.
                MimeBodyPart pdfAttachment = new MimeBodyPart();
                pdfAttachment.attachFile(FileOut);
                // Attach body parts
                multipart.addBodyPart(pdfAttachment);
                message.setContent(multipart);
            }

            if (etat.equals("refuse")) {
                message.setContent(
                        "<h1>Demande Rejet?? </h1><br/> <p>Votre demande ?? ??t?? rejet?? Essayez de contacter l'administration </p>",
                        "text/html");
            }

            // _____________
 
            Transport.send(message);
            load.dispose();
            //________________
            
            JOptionPane.showMessageDialog(Main.frame, "Done");
            if (etat.equals("accept")) {
                FileOut.delete();
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
