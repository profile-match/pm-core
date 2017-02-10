/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.javamail;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ILYASS
 */
public class SendMail {
    
    static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;
 
	public static void generateAndSendEmail(String email) throws AddressException, MessagingException {
 
		// Etape 1 : Configuration du serveur
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
 
		// Etape 2 : Configuration Du Mail à Envoyer
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
                //Email auquel l'invitation va etre envoye
		generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
		generateMailMessage.setSubject("Invitation à IProfileMatch..");
		String emailBody = "Email test par Ilyass en utilisant JavaMail API. " + "<br><br> Cordialement, <br>Equipe De IProfileMatch";
		generateMailMessage.setContent(emailBody, "text/html");
 
		// Etape 3 : Envoie Du Mail à l'aide de gmail
		//System.out.println("Envoie Du Mail à l'aide de gmail");
		Transport transport = getMailSession.getTransport("smtp");
		// Spécification du gmail UserID et le mots de passe
		transport.connect("smtp.gmail.com", "imp.jmail", "imp123456");
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		transport.close();
    
}
    
}
