package org.profilematch.pmcore.ejbs;

import com.sun.mail.smtp.SMTPAddressFailedException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.ejb.Singleton;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Singleton
public class Mail {
    
    private Properties proprietes = null;
    private Session mailSession = null ;
    private Transport transport = null;
    
    private final String mail = "imatchprofile1@gmail.com";
    private final String path = "password.txt";
    private String mdp = "" ;
    
    @PostConstruct
    private void init(){
        
        // configuration des propriétés
        proprietes = System.getProperties();
        proprietes.put("mail.smtp.port", "587");
        proprietes.put("mail.smtp.auth", "true");
        proprietes.put("mail.smtp.starttls.enable", "true");
        //mailServerProperties.put("mail.smtp.reportsuccess", "true");
        
        //génération de la session
        mailSession = Session.getDefaultInstance(proprietes, null);
    }
    
    
    private Transport connexion(){
        
        try {
            File fichier = new File(path);
            
            if(mdp.isEmpty() && fichier.exists()){
                BufferedReader lecteurAvecBuffer = new BufferedReader(new FileReader(fichier));
                mdp = lecteurAvecBuffer.readLine();
            }
            
            transport = mailSession.getTransport("smtp");
            transport.connect("smtp.gmail.com", mail, mdp);
            return transport;
            
        } catch (Exception ex) {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public  String envoyer(org.profilematch.pmcore.entities.Mail mail) throws Exception {
        
        MimeMessage generateMailMessage;
        
        try {
            generateMailMessage = new MimeMessage(mailSession);
            generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(mail.getEmail()));
            generateMailMessage.setSubject("Invitation iMatchProfile", "utf-8");
            String emailBody = "Bonjour," +
                    "<br/> Vous avez recu une invitation pour rejoindre la communité iMatchProfile"
                    + "<br/>  Vous pouvez y accèder grâce à ce  <a href='#'> lien </a>"
                    + " <br/> A bientôt, "
                    + "<br/><br/>  l'équipe iMatchProfile";
            generateMailMessage.setContent(emailBody, "text/html ; charset=utf-8");

            transport = connexion();
            transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
            
            transport.close();
            return "ok";
            
        } catch (SMTPAddressFailedException ex) {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AddressException ex) {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "erreur interne";
    }
    
}