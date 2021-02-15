
package com.fcastillo.utilidades.email;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author fcastillo
 */
public class EmailManager {

    public static boolean sendTemplate(String asunto, String remitente, String destinatario, HashMap<String, String> params, String rutaPlantilla, Session s) {
        boolean enviado = false;
        try {
            String message;

            MimeMessage mimeMessage = new MimeMessage(s);

            mimeMessage.setFrom(new InternetAddress(remitente));

            InternetAddress[] internetAddresses = {new InternetAddress(destinatario)};

            mimeMessage.setRecipients(Message.RecipientType.TO, internetAddresses);

            mimeMessage.setSubject(asunto);

            Multipart multipart = new MimeMultipart();

            InputStream inputStream = new FileInputStream(rutaPlantilla);

            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream));

            String strLine;

            StringBuffer msjHTML = new StringBuffer();

            while ((strLine = bufferedReader.readLine()) != null) {
                msjHTML.append(strLine);
            }

            MimeBodyPart mimeBodyPart = new MimeBodyPart();

            message = msjHTML.toString();

            Iterator<Map.Entry<String, String>> it = params.entrySet().iterator();

            while (it.hasNext()) {
                Map.Entry<String, String> entry = it.next();
                message = message.replace(entry.getKey(), entry.getValue());
            }

            mimeBodyPart.setContent(message, "text/html");

            multipart.addBodyPart(mimeBodyPart);

            mimeMessage.setContent(multipart);

            Transport.send(mimeMessage, mimeMessage.getAllRecipients());
            enviado = true;
        } catch (Exception e) {
            System.out.println("Failed send mail()" + e.getMessage());
        }
        return enviado;
    }
    
    public static boolean sendTemplateHtml(String asunto, String remitente, String destinatario, String htmlTemplate, Session s) {
        boolean enviado = false;
        try {
            String message;

            MimeMessage mimeMessage = new MimeMessage(s);

            mimeMessage.setFrom(new InternetAddress(remitente));

            InternetAddress[] internetAddresses = {new InternetAddress(destinatario)};

            mimeMessage.setRecipients(Message.RecipientType.TO, internetAddresses);

            mimeMessage.setSubject(asunto);

            Multipart multipart = new MimeMultipart();

            MimeBodyPart mimeBodyPart = new MimeBodyPart();

            mimeBodyPart.setContent(htmlTemplate, "text/html");

            multipart.addBodyPart(mimeBodyPart);

            mimeMessage.setContent(multipart);

            Transport.send(mimeMessage, mimeMessage.getAllRecipients());
            
            enviado = true;
        } catch (Exception e) {
            System.out.println("Failed send mail()" + e.getMessage());
        }
        return enviado;
    }

}
