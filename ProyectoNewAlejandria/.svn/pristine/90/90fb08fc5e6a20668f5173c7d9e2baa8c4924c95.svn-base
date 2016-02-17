/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package webservices;

import beans.IinvoiceDetails;
import beans.Iinvoices;
import beans.MjFiles;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.net.MalformedURLException; 
import java.net.URL; 
import utils.FTPUtils;
import com.google.gson.*;

/**
 *
 * @author Otto
 */
@WebService()
public class IInvoicesWS {

    /**
     * Web service operation
     * @param documentNo
     * @param documentType
     * @param username
     * @param authenticator
     * @return 
     */
    @WebMethod(operationName = "getDocumentInvoices")
    public String getDocumentInvoices(@WebParam(name = "documentNo") String documentNo,
            @WebParam(name = "documentType") String documentType,
            @WebParam(name = "username") String username,
            @WebParam(name = "authenticator") String authenticator) {
        // Primero se debe validar el usuario
        UsersWS usersSrv = new UsersWS();
        int validation = usersSrv.validateUser(username, authenticator);
        if (validation == 0) {
            System.out.println("Usuario Invalido");
            return null;
        }
        // Obtengo el webUsers
        beans.WebUsers webUser = usersSrv.getWebUser(username, authenticator);
        // abro la sesion
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        // Jalo las Iinvoices
        String str = "SELECT i, id FROM Iinvoices i, IdocumentInvoices di, Documents d, IinvoiceDetails id WHERE di.invoiceId = i.id ";
        str += "and d.documentNo = di.documentNo and d.documentType = di.documentType and id.invoiceId = i.id and ";
        str += "di.documentNo = :documentNo and di.documentType = :documentType and d.customerId = :customerId and i.statusCode = 'PT'";
        Query query = session.createQuery(str);
        query.setString("documentNo", documentNo);
        query.setString("documentType", documentType);
        query.setInteger("customerId", webUser.getCustomerId());
        List invL = query.list();
        // Armo el AL de warheouses
        Iinvoices inv = null;
        ArrayList<Iinvoices> invAL = new ArrayList<Iinvoices>();
        ArrayList<IinvoiceDetails> invDetailsAL = new ArrayList<IinvoiceDetails>();
        for (int i = 0; i < invL.size(); i++) {
            Object[] inputObj = (Object[]) invL.get(i);
            Iinvoices invAux = (Iinvoices)inputObj[0];
            beans.IinvoiceDetails invDetail = (beans.IinvoiceDetails)inputObj[1];
            // Cargo la invoice si no hay uno cargado
            if (inv == null) {
                inv = invAux;
            }
            // Cargo el customerDestination si hay cambio
            if (inv.getId() != invAux.getId()) {
                inv.setDetail(invDetailsAL);
                invAL.add(inv);
                inv = invAux;
                invDetailsAL = new ArrayList<IinvoiceDetails>();
            }
            // Agrego el invoiceDetail a la lista de invoiceDetail
            invDetailsAL.add(invDetail);
        }
        // Cargo la ultima invoice si existe
        if (inv != null) {
            inv.setDetail(invDetailsAL);
            invAL.add(inv);
        }
        //// Cierro la sesion
        session.getTransaction().commit();
        session.close();
        // Regreso
        System.out.println("que pasa... esto es antes de regresar");
        // Convierto el AL en A
        Iinvoices[] invA = new Iinvoices[invAL.size()];
        for (int i = 0; i < invAL.size(); i++) {
            invA[i] = invAL.get(i);
        }
        
        // Retorno
        final Gson gson = new Gson();
        final String invAJSON = gson.toJson(invA);
        return invAJSON;
    }

    /**
     * Web service operation
     * @param serie
     * @param number
     * @param username
     * @param authenticator
     * @return 
     */
    @WebMethod(operationName = "getEncriptedNameBySerieNumber")
    public String getEncriptedNameBySerieNumber(@WebParam(name = "serie") String serie,
            @WebParam(name = "number") String number,
            @WebParam(name = "username") String username,
            @WebParam(name = "authenticator") String authenticator) {
        System.out.println("buscar factura");
        // Primero se debe validar el usuario
        UsersWS usersSrv = new UsersWS();
        int validation = usersSrv.validateUser(username, authenticator);
        if (validation == 0) {
            System.out.println("Usuario Invalido");
            return null;
        }
        // Obtengo el webUsers
        beans.WebUsers webUser = usersSrv.getWebUser(username, authenticator);
        // abro la sesion
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        // Jalo las Iinvoices
        String str = "FROM Iinvoices i WHERE i.serie = :serie AND i.number = :number AND i.customerId = :customerId";
        Query query = session.createQuery(str);
        query.setString("serie", serie);
        query.setString("number", number);
        query.setInteger("customerId", webUser.getCustomerId());
        ArrayList<Iinvoices> invA = new ArrayList<Iinvoices>(query.list());
        String result = "";
        //System.out.println("casi antes de regresasrrrrrrrrrrrr");
        if (invA.size() > 0) {
            Iinvoices inv = invA.get(0);
            String invoiceFormatString =inv.getSerie()+inv.getNumber()+"shit";
            UtilityWS utilityWS = new UtilityWS();
            result = utilityWS.getEncryptedWord(invoiceFormatString);
            return result;
        } else {
            return null;
        }
    }

    /**
     * Web service operation
     * @param serie
     * @param number
     * @param username
     * @param authenticator
     * @return 
     */
    @WebMethod(operationName = "getInvoiceBySerieNumber")
    public String getInvoiceBySerieNumber(@WebParam(name = "serie") String serie,
            @WebParam(name = "number") String number,
            @WebParam(name = "username") String username,
            @WebParam(name = "authenticator") String authenticator) {
        // Primero se debe validar el usuario
        UsersWS usersSrv = new UsersWS();
        int validation = usersSrv.validateUser(username, authenticator);
        if (validation == 0) {
            System.out.println("Usuario Invalido");
            return null;
        }
        // Obtengo el webUsers
        beans.WebUsers webUser = usersSrv.getWebUser(username, authenticator);
        // abro la sesion
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        // Jalo las Iinvoices
        String str = "SELECT i, id FROM Iinvoices i, IinvoiceDetails id WHERE id.invoiceId = i.id AND ";
        str += "i.serie = :serie AND i.number = :number AND i.customerId = :customerId";
        Query query = session.createQuery(str);
        query.setString("serie", serie);
        query.setString("number", number);
        query.setInteger("customerId", webUser.getCustomerId());
        List invL = query.list();
        // Armo la invoice si existe
        Iinvoices inv = null;
        ArrayList<IinvoiceDetails> invDetailsAL = new ArrayList<IinvoiceDetails>();
        for (int i = 0; i < invL.size(); i++) {
            Object[] inputObj = (Object[]) invL.get(i);
            Iinvoices invAux = (Iinvoices)inputObj[0];
            beans.IinvoiceDetails invDetail = (beans.IinvoiceDetails)inputObj[1];
            // Cargo la invoice si no hay uno cargado
            if (inv == null) {
                inv = invAux;
            }
            // Agrego el invoiceDetail a la lista de invoiceDetail
            invDetailsAL.add(invDetail);
        }
        // Cargo la ultima invoice si existe
        if (inv != null) {
            inv.setDetail(invDetailsAL);
        }
        //// Cierro la sesion
        session.getTransaction().commit();
        session.close();
        
        // Retorno
        final Gson gson = new Gson();
        final String invJSON = gson.toJson(inv);
        // Regreso
        return invJSON;
    }
    
    
    //descargar factura ftp
    @WebMethod(operationName = "getPrintFtpInvoice")
    public String getPrintFtpInvoice(@WebParam(name = "serie") String serie,
            @WebParam(name = "number") String number,
            @WebParam(name = "username") String username,
            @WebParam(name = "authenticator") String authenticator) {
        String result = "1";
        Integer customerId = 0;
        UsersWS usersSrv = new UsersWS();
        int validation = usersSrv.validateUser(username, authenticator);
        if (validation == 0) {
            System.out.println("Usuario Invalido");
            return null;
        }
        // Obtengo el webUsers
        beans.WebUsers webUser = usersSrv.getWebUser(username, authenticator);
        customerId = webUser.getCustomerId();
        //llamar al servlet
        
        StringBuffer tmp = new StringBuffer(); 
        String resultUrl = new String(); 
        try { 
            // Crea la URL con del sitio introducido, ej: http://google.com 
            System.out.println("url");
            //url produccion
            URL url = new URL("http://app.cpslogistics.com:8080/Icarus/AddFtpAttachment?customerId="+customerId+"&serie="+serie+"&number=" + number);
            
            //URL prueba
            //URL url = new URL("http://localhost:8084/Icarus/AddFtpAttachment?customerId="+customerId+"&serie="+serie+"&number=" + number); 
     
            // Lector para la respuesta del servidor 
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream())); 
            String str; 
            while ((str = in.readLine()) != null) { 
                tmp.append(str); 
            } 
            in.close(); 
            resultUrl = tmp.toString(); 
        }catch (MalformedURLException e) { 
            System.out.println("1 "+e.toString());
        } catch (IOException e) { 
            System.out.println("2 "+e.toString());
        } 
      
        
        //ahora a descargar la factura
        String pdfPath = "";
        if (Integer.valueOf(resultUrl) > 0 ){
            MjFiles mjFilesBean = new MjFiles();
            //buscar
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            // Obtengo el customer
            mjFilesBean = (MjFiles)session.load(MjFiles.class,Integer.valueOf(resultUrl));
            System.out.println("Descargar");
            if (mjFilesBean != null){
                
                pdfPath = downloadFile(mjFilesBean,"pdf");
            }
            session.getTransaction().commit();
            session.close();
            result = pdfPath;
            
            
        }
        
        return result;
    }
    
    public String downloadFile(MjFiles mjFile, String extension){
        String result = "0";
        try {

       

            //Se obtiene el nombre original
            String originalFilename = mjFile.getName();
            //Se obtiene el nombre con el que se guardo en el servidor ftp
            
            String filename = mjFile.getUrl().substring(48);//44
            //Se obtiene la extensión
            originalFilename += "." + extension; //Se agrega la extensión
           
            //Se descarga el archivo al proyecto
            FTPUtils ftpUtil = new FTPUtils();
            result = ftpUtil.download(originalFilename, originalFilename);
            
           
        } catch (Exception ex) {
             //Utils.exception(logger, ex,"PIOINVOICE-Error al intentar descargar el archivo","downloadFile",this.getClass().getName());
        }
        return result; //Se devuelve el nombre del archivo
    }
    
    
}
