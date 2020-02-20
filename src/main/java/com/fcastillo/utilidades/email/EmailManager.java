/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.utilidades.email;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.log4j.Logger;

/**
 *
 * @author fcastillo
 */
public class EmailManager {
    
    private static String mensajeEnviar;
    private static final Logger LOG = Logger.getLogger(EmailManager.class.getName());

    /**
     *
     * @param asunto
     * @param remitente
     * @param destinatario
     * @param params
     * @param rutaPlantilla
     * @param props
     * @throws java.lang.Exception
     */
    public void sendHtml(String asunto, String remitente, String destinatario, HashMap<String, String> params, String rutaPlantilla, Properties props) throws Exception {
        try {
            final String usuario = remitente;
            final String pwd = props.getProperty("mail.smtp.password");
            
            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(usuario, pwd);
                }
            });
            MimeMessage mimeMessage = new MimeMessage(session);
            
            mimeMessage.setFrom(new InternetAddress(remitente));
            
            InternetAddress[] internetAddresses = {new InternetAddress(destinatario)};
            
            mimeMessage.setRecipients(Message.RecipientType.TO, internetAddresses);
            
            mimeMessage.setSubject(asunto);
            
            Multipart multipart = new MimeMultipart();
            
            InputStream inputStream = new FileInputStream(rutaPlantilla);
            
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            
            String strLine;
            
            StringBuffer msjHTML = new StringBuffer();
            
            while ((strLine = bufferedReader.readLine()) != null) {
                msjHTML.append(strLine);
            }
            
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            
            mensajeEnviar = msjHTML.toString();
            
            params.entrySet().stream().forEach((f) -> {
                mensajeEnviar = mensajeEnviar.replace(f.getKey(), f.getValue());
            });
            
            mimeBodyPart.setContent(mensajeEnviar, "text/html");
            
            multipart.addBodyPart(mimeBodyPart);
            
            mimeMessage.setContent(multipart);
            
            Transport.send(mimeMessage, mimeMessage.getAllRecipients());
            LOG.info("Mail enviado ");
        } catch (Exception e) {
            LOG.info("Fallo envio de mail : " + e.getMessage());
            throw e;
        }
    }
    
}
