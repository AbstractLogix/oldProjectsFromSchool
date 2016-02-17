/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package webservices;

import java.util.ArrayList;
import java.util.Properties;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
/**
 *
 * @author Otto
 */
@WebService()
public class EmailSenderWS {

    // Construyo la funcion que devuelve la sesion

    private Session getEmailSession() {
            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            // Propiedades de envio de correo
            Properties prop = new Properties();
            prop.setProperty("mail.transport.protocol", "smtp");
            prop.setProperty("mail.smtp.starttls.enable","true");
            prop.setProperty("mail.smtp.host", "smtp.gmail.com");
            prop.setProperty("mail.debug", "false");
            prop.setProperty("mail.smtp.auth", "true");
            prop.setProperty("mail.smtp.port", "465");
            prop.setProperty("mail.smtp.socketFactory.port", "465");
            prop.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            prop.setProperty("mail.smtp.socketFactory.fallback", "false");
            //prop.setProperty("mail.smtp.starttls.enable","true");
            javax.mail.Authenticator auth = new SMTPAuthenticator();
            Session session = Session.getDefaultInstance(prop, auth);
            return session;
    }

    private BodyPart sendInstructionBody(int[] wrhsId, String service, String quoted, String observations,
        String customerCode, String customerName, String customerLastname) {
        
        // Construyo el BodyPart
        BodyPart bp = new MimeBodyPart();
        String emailText = "Buenos dias,\n";
        emailText += "\n";
        emailText += "El cliente:\n";
        emailText += customerName+" "+customerLastname+"\n";
        emailText += "Codigo: "+customerCode+"\n";
        emailText += "\n";
        emailText += "Ha girado instrucciones para los Warehouses:\n";
        for (int i = 0; i < wrhsId.length; i++) {
            emailText += wrhsId[i]+"\n";
        }
        emailText += "\n";
        if (!service.equalsIgnoreCase("")) {
            emailText += "Servicio: "+service+"\n";
            emailText += "Cotizado: "+quoted+"\n";
        } else {
            emailText += "No se selecciono servicio";
        }
        emailText += "\n";
        emailText += "Observaciones: "+observations+"\n";
        emailText += "\n";
        emailText += "Saludos.\n";
        // Cargo el texto
        try {
            bp.setText(emailText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Regreso
        return bp;
    }

    private BodyPart sendCallMeBody(String phone, String message, beans.Customers customer) {
        // Construyo el BodyPart
        BodyPart bp = new MimeBodyPart();
        String emailText = "Buenos dias,\n";
        emailText += "\n";
        emailText += "El cliente:\n";
        emailText += customer.getName().trim()+" "+customer.getLastname().trim()+"\n";
        emailText += "Codigo: "+customer.getCode().trim()+"\n";
        emailText += "\n";
        emailText += "Ha solicitado ser llamado\n";
        emailText += "Teléfono: \n";
        emailText += phone + " \n";
        emailText += "Mensaje: \n";
        emailText += message + " \n";
        emailText += "\n";
        emailText += "Informacion adicional del cliente: \n";
        for (int i = 0; i < customer.getCustomerDestinations().size(); i++) {
            if (!customer.getCustomerDestinations().get(0).getTel().equalsIgnoreCase("")) {
                emailText += "Telefono: "+customer.getCustomerDestinations().get(0).getTel()+"\n";
            }
            if (!customer.getCustomerDestinations().get(0).getCel().equalsIgnoreCase("")) {
                emailText += "Celular: "+customer.getCustomerDestinations().get(0).getCel()+"\n";
            }
            if (!customer.getCustomerDestinations().get(0).getEmail().equalsIgnoreCase("")) {
                emailText += "Email: "+customer.getCustomerDestinations().get(0).getEmail()+"\n";
            }
            emailText += "\n";
            // Ahora grabo los customerContacts
            emailText += "Contactos: \n";
            ArrayList<beans.CustomerContacts> ccAL = customer.getCustomerDestinations().get(0).getCustomerContacts();
            for (int ii = 0; ii < ccAL.size(); ii++) {
                beans.CustomerContacts customerContact = customer.getCustomerDestinations().get(0).getCustomerContacts().get(ii);
                if (!customerContact.getTel().equalsIgnoreCase("")) {
                    emailText += customerContact.getTel()+" "+customerContact.getName().trim()
                        +" "+customerContact.getLastname().trim()+"\n";
                }
                if (!customerContact.getCel().equalsIgnoreCase("")) {
                    emailText += customerContact.getCel()+" "+customerContact.getName().trim()
                        +" "+customerContact.getLastname().trim()+"\n";
                }
            }
            i = customer.getCustomerDestinations().size();
        }

        emailText += "Saludos.\n";
        // Cargo el texto
        try {
            bp.setText(emailText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Regreso
        return bp;
    }

    private BodyPart sendEmailToRecoveryBody (String message, beans.Customers customer) {
        // Construyo el BodyPart
        BodyPart bp = new MimeBodyPart();
        String emailText = "Buenos dias,\n";
        emailText += "\n";
        emailText += customer.getName().trim()+" "+customer.getLastname().trim()+"\n";
        emailText += "Codigo: "+customer.getCode().trim()+"\n";
        emailText += "\n";
        emailText += "Ha enviado un correo a cobros\n";
        emailText += "Mensaje: \n";
        emailText += message + " \n";
        emailText += "\n";
        emailText += "Información del cliente: \n";
        for (int i = 0; i < customer.getCustomerDestinations().size(); i++) {
            if (!customer.getCustomerDestinations().get(0).getTel().equalsIgnoreCase("")) {
                emailText += "Telefono: "+customer.getCustomerDestinations().get(0).getTel()+"\n";
            }
            if (!customer.getCustomerDestinations().get(0).getCel().equalsIgnoreCase("")) {
                emailText += "Celular: "+customer.getCustomerDestinations().get(0).getCel()+"\n";
            }
            if (!customer.getCustomerDestinations().get(0).getEmail().equalsIgnoreCase("")) {
                emailText += "Email: "+customer.getCustomerDestinations().get(0).getEmail()+"\n";
            }
            emailText += "\n";
            // Ahora grabo los customerContacts
            emailText += "Contactos: \n";
            ArrayList<beans.CustomerContacts> ccAL = customer.getCustomerDestinations().get(0).getCustomerContacts();
            for (int ii = 0; ii < ccAL.size(); ii++) {
                beans.CustomerContacts customerContact = customer.getCustomerDestinations().get(0).getCustomerContacts().get(ii);
                if (!customerContact.getTel().equalsIgnoreCase("")) {
                    emailText += customerContact.getTel()+" "+customerContact.getName().trim()
                        +" "+customerContact.getLastname().trim()+"\n";
                }
                if (!customerContact.getCel().equalsIgnoreCase("")) {
                    emailText += customerContact.getCel()+" "+customerContact.getName().trim()
                        +" "+customerContact.getLastname().trim()+"\n";
                }
            }
            i = customer.getCustomerDestinations().size();
        }

        emailText += "Saludos.\n";
        // Cargo el texto
        try {
            bp.setText(emailText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Regreso
        return bp;
    }

    private BodyPart sendEmailBody (String from, String subject, String message) {
        // Construyo el BodyPart
        BodyPart bp = new MimeBodyPart();
        String emailText = "Buenos dias,\n";
        emailText += "\n";
        emailText += "Ud tiene un correo desde Alejandria:\n";
        emailText += "\n";
        emailText += "De: "+from+"\n";
        emailText += "Titulo: "+subject+"\n";
        emailText += "\n";
        emailText += "Mensaje:\n";
        emailText += message+"\n";
        emailText += "\n";
        emailText += "Saludos.\n";
        emailText += "\n";
        // Cargo el texto
        try {
            bp.setText(emailText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Regreso
        return bp;
    }

    /**
     * Web service operation
     * @param wrhsId
     * @param service
     * @param quoted
     * @param observations
     * @param username
     * @param authenticator
     * @return 
     */
    @WebMethod(operationName = "sendInstruction")
    public int sendInstruction(@WebParam(name = "wrhsId") int[] wrhsId,
            @WebParam(name = "service") String service,
            @WebParam(name = "quoted") String quoted,
            @WebParam(name = "observations") String observations,
            @WebParam(name = "username") String username,
            @WebParam(name = "authenticator") String authenticator) {
        // Primero se debe validar el usuario
        UsersWS usersSrv = new UsersWS();
        int validation = usersSrv.validateUser(username, authenticator);
        if (validation == 0) {
            System.out.println("Usuario Invalido");
            return 0;
        }
        // Cargo el Customer
        CustomersWS customerWS = new CustomersWS();
        //beans.Customers customer = customerWS.getCustomer(username, authenticator);
        beans.Customers customer = new beans.Customers();
        // Construyo el correo y envio
        try {
            // Abro la sesion
            Session session = this.getEmailSession();
            MimeMessage msg = new MimeMessage(session);
            // Cargo el Remitente
            InternetAddress from = new InternetAddress("support@cpsworldwide.com");
            msg.setFrom(from);
            // Cargo el Destinatario
            //InternetAddress to = new InternetAddress("oramirez@cpslogistics.com");
            beans.Users user = customerWS.getUser(customer.getCustomerDestinations().get(0).getCustomerAgentId(), username, authenticator);
            InternetAddress to = new InternetAddress(user.getEmail());
            msg.addRecipient(Message.RecipientType.TO, to);
            InternetAddress to2 = new InternetAddress(user.getEmail1());
            msg.addRecipient(Message.RecipientType.TO, to2);
            user = customerWS.getUser(customer.getCustomerDestinations().get(0).getSalesPersonId(), username, authenticator);
            InternetAddress to1 = new InternetAddress(user.getEmail());
            msg.addRecipient(Message.RecipientType.TO, to1);
            // Cargo el Subject
            msg.setSubject("Envio de Instrucciones, Codigo "+customer.getCode()+" "+customer.getName()+" "+customer.getLastname());
            // Cargo el Cuerpo del mensaje
            Multipart mp = new MimeMultipart("mixed");
            BodyPart bp = sendInstructionBody(wrhsId, service, quoted, observations, customer.getCode(), customer.getName(), customer.getLastname());
            mp.addBodyPart(bp);
            msg.setContent(mp);
            // Preparo y envío
            Transport tr = session.getTransport("smtp");
            tr.connect("smtp.gmail.com", "support@cpsworldwide.com", "1234");
            msg.saveChanges(); // don't forget this
            if (msg.getAllRecipients() != null) {
                tr.sendMessage(msg, msg.getAllRecipients()); // ACTIVARLA DE NUEVO *********************
            }
            tr.close();
            // Creo el mensaje correspondiente
            String mensaje = "Wrhs: ";
            for (int i = 0; i < wrhsId.length; i++) {
                mensaje += wrhsId[i]+", ";
            }
            mensaje += "Srv: "+service;
            mensaje += "Msg: "+observations;
            webservices.UsersWS userWS = new webservices.UsersWS();
            userWS.addWebUserMessage("INSTRUCTION", mensaje, username, authenticator);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //TODO write your implementation code here:
        return 0;
    }

    /**
	 * SimpleAuthenticator is used to do simple authentication when the SMTP
	 * server requires it.
	 */
	private class SMTPAuthenticator extends javax.mail.Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			String username = "support@cpsworldwide.com";
			String password = "jIntelligent";
			return new PasswordAuthentication(username, password);
		}
	}

    /**
     * Web service operation
     * @param phone
     * @param message
     * @param username
     * @param authenticator
     * @return 
     */
    @WebMethod(operationName = "sendCallMe")
    public int sendCallMe(@WebParam(name = "phone") String phone,
            @WebParam(name = "message") String message,
            @WebParam(name = "username") String username,
            @WebParam(name = "authenticator") String authenticator) {
        // Primero se debe validar el usuario
        UsersWS usersSrv = new UsersWS();
        int validation = usersSrv.validateUser(username, authenticator);
        if (validation == 0) {
            System.out.println("Usuario Invalido");
            return 0;
        }
        // Cargo el Customer
        CustomersWS customerWS = new CustomersWS();
        //beans.Customers customer = customerWS.getCustomer(username, authenticator);
         beans.Customers customer = new beans.Customers();
        // Construyo el correo y envio
        try {
            // Abro la sesion
            Session session = this.getEmailSession();
            MimeMessage msg = new MimeMessage(session);
            // Cargo el Remitente
            InternetAddress from = new InternetAddress("support@cpsworldwide.com");
            msg.setFrom(from);
            // Cargo el Destinatario
            //InternetAddress to = new InternetAddress("oramirez@cpslogistics.com");
            beans.Users user = customerWS.getUser(customer.getCustomerDestinations().get(0).getCustomerAgentId(), username, authenticator);
            InternetAddress to = new InternetAddress(user.getEmail());
            msg.addRecipient(Message.RecipientType.TO, to);
            user = customerWS.getUser(customer.getCustomerDestinations().get(0).getSalesPersonId(), username, authenticator);
            InternetAddress to1 = new InternetAddress(user.getEmail());
            msg.addRecipient(Message.RecipientType.TO, to1);
            // Cargo el Subject
            msg.setSubject("Solicitud de llamada, Codigo "+customer.getCode()+" "+customer.getName()+" "+customer.getLastname());
            // Cargo el Cuerpo del mensaje
            Multipart mp = new MimeMultipart("mixed");
            BodyPart bp = sendCallMeBody(phone, message, customer);
            mp.addBodyPart(bp);
            msg.setContent(mp);
            // Preparo y envío
            Transport tr = session.getTransport("smtp");
            tr.connect("smtp.gmail.com", "support@cpsworldwide.com", "1234");
            msg.saveChanges(); // don't forget this
            if (msg.getAllRecipients() != null) {
                tr.sendMessage(msg, msg.getAllRecipients()); // ACTIVARLA DE NUEVO *********************
            }
            tr.close();
            // Creo el mensaje correspondiente
            webservices.UsersWS userWS = new webservices.UsersWS();
            userWS.addWebUserMessage("CALLME", message, username, authenticator);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //TODO write your implementation code here:
        return 0;
    }

    /**
     * Web service operation
     * @param message
     * @param username
     * @param authenticator
     * @return 
     */
    @WebMethod(operationName = "sendEmailToRecovery")
    public int sendEmailToRecovery(@WebParam(name = "message") String message,
            @WebParam(name = "username") String username,
            @WebParam(name = "authenticator") String authenticator) {
        // Primero se debe validar el usuario
        UsersWS usersSrv = new UsersWS();
        int validation = usersSrv.validateUser(username, authenticator);
        if (validation == 0) {
            System.out.println("Usuario Invalido");
            return 0;
        }
        // Cargo el Customer
        CustomersWS customerWS = new CustomersWS();
        //beans.Customers customer = customerWS.getCustomer(username, authenticator);
        beans.Customers customer = new beans.Customers();
        // Construyo el correo y envio
        try {
            // Abro la sesion
            Session session = this.getEmailSession();
            MimeMessage msg = new MimeMessage(session);
            // Cargo el Remitente
            InternetAddress from = new InternetAddress("support@cpsworldwide.com");
            msg.setFrom(from);
            // Cargo el Destinatario
            //InternetAddress to = new InternetAddress("oramirez@cpslogistics.com");
            InternetAddress to = new InternetAddress("cobros@cpslogistics.com");
            msg.addRecipient(Message.RecipientType.TO, to);
            // Cargo el Subject
            msg.setSubject("Mensaje a cobros de cliente, Codigo "+customer.getCode()+" "+customer.getName()+" "+customer.getLastname());
            // Cargo el Cuerpo del mensaje
            Multipart mp = new MimeMultipart("mixed");
            BodyPart bp = sendEmailToRecoveryBody(message, customer);
            mp.addBodyPart(bp);
            msg.setContent(mp);
            // Preparo y envío
            Transport tr = session.getTransport("smtp");
            tr.connect("smtp.gmail.com", "support@cpsworldwide.com", "1234");
            msg.saveChanges(); // don't forget this
            if (msg.getAllRecipients() != null) {
                tr.sendMessage(msg, msg.getAllRecipients()); // ACTIVARLA DE NUEVO *********************
            }
            tr.close();
            // Creo el mensaje correspondiente
            webservices.UsersWS userWS = new webservices.UsersWS();
            userWS.addWebUserMessage("ETRO", message, username, authenticator);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //TODO write your implementation code here:
        return 0;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "sendEmail")
    public int sendEmail(@WebParam(name = "from") String from, 
            @WebParam(name = "to") String to,
            @WebParam(name = "subject") String subject,
            @WebParam(name = "message") String message,
            @WebParam(name = "username") String username,
            @WebParam(name = "authenticator") String authenticator) {
        // Primero se debe validar el usuario
        UsersWS usersSrv = new UsersWS();
        int validation = usersSrv.validateUser(username, authenticator);
        if (validation == 0) {
            System.out.println("Usuario Invalido");
            return 0;
        }
        try {
            // Abro la sesion
            Session session = this.getEmailSession();
            MimeMessage msg = new MimeMessage(session);
            // Cargo el Remitente
            InternetAddress fromIA = new InternetAddress("support@cpsworldwide.com");
            msg.setFrom(fromIA);
            // Cargo el Destinatario
            //InternetAddress to = new InternetAddress("oramirez@cpslogistics.com");
            //InternetAddress toIA = new InternetAddress(to);
            InternetAddress toIA = new InternetAddress("oramirez@cpslogistics.com");
            msg.addRecipient(Message.RecipientType.TO, toIA);
            // Cargo el Subject
            msg.setSubject("Correo desde Alejandria, del cliente: "+from);
            // Cargo el Cuerpo del mensaje
            Multipart mp = new MimeMultipart("mixed");
            BodyPart bp = sendEmailBody(from, subject, message);
            mp.addBodyPart(bp);
            msg.setContent(mp);
            // Preparo y envío
            Transport tr = session.getTransport("smtp");
            tr.connect("smtp.gmail.com", "support@cpsworldwide.com", "1234");
            msg.saveChanges(); // don't forget this
            if (msg.getAllRecipients() != null) {
                tr.sendMessage(msg, msg.getAllRecipients()); // ACTIVARLA DE NUEVO *********************
            }
            tr.close();
            // Creo el mensaje correspondiente
            webservices.UsersWS userWS = new webservices.UsersWS();
            userWS.addWebUserMessage("ETRO", message, username, authenticator);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //TODO write your implementation code here:
        return 0;
    }
}
