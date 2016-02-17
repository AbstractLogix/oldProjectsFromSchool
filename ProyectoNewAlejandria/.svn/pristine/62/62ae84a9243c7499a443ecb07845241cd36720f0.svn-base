/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package webservices;

import beans.Documents;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;
import com.google.gson.*;


/**
 *
 * @author Otto
 */
@WebService()
public class DocumentsWS {

    /**
     * Web service operation
     * @param username
     * @param authenticator
     * @return 
     */
    @WebMethod(operationName = "getDocumentsOnTransitByCustomer")
    public String getDocumentsOnTransitByCustomer(@WebParam(name = "username") String username,
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
        // Jalo los Documents
        String str = "SELECT d, oc.code, dc.code FROM Documents d, Locations ol,  Cities oc, Locations dl,  Cities dc ";
        str += "WHERE d.agentOrigenId = ol.id and ol.cityId = oc.id and d.agentDestinationId = dl.id and dl.cityId = dc.id ";
        str += "and d.customerId = :customerId and d.stepId <> 'DV'";
        Query query = session.createQuery(str);
        query.setInteger("customerId", webUser.getCustomerId());
        List docL = query.list();
        // Armo el AL de warheouses
        Documents[] docsA = new Documents[docL.size()];
        for (int i = 0; i < docL.size(); i++) {
            Object[] inputObj = (Object[]) docL.get(i);
            Documents document = (Documents)inputObj[0];
            String origenCityCode = (String) inputObj[1];
            String destinationCityCode = (String) inputObj[2];
            // Agrego el origen y el destino
            document.setOrigenCityCode(origenCityCode);
            document.setDestinationCityCode(destinationCityCode);
            // Agrego el documento
            docsA[i] = document;
        }
        // Cierro la sesion
        session.getTransaction().commit();
        session.close();
        
        // Retorno
        final Gson gson = new Gson();
        final String representacionJSON = gson.toJson(docsA);
        
        String resp = representacionJSON.replace('/', '/');
        resp =resp.replace('\\', ' ');
        
        
        return resp;
    }

    /**
     * Web service operation
     * @param documentNo
     * @param documentType
     * @param username
     * @param authenticator
     * @return 
     */
    @WebMethod(operationName = "getDocumentWarehouses")
    public String getDocumentWarehouses(@WebParam(name = "documentNo") String documentNo,
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
        // Jalo los warehouses para este poNo
        String str = "SELECT w FROM DocumentWarehouses dw, Warehouses w, Documents d WHERE dw.documentNo = d.documentNo and ";
        str += "dw.documentType = d.documentType and dw.warehosueId = w.id and dw.documentNo = :documentNo and ";
        str += "dw.documentType = :documentType and d.customerId = :customerId order by warehosueId asc";
        Query query = session.createQuery(str);
        query.setString("documentNo", documentNo);
        query.setString("documentType", documentType);
        query.setInteger("customerId", webUser.getCustomerId());
        ArrayList<beans.Warehouses> wrhsAL = new ArrayList<beans.Warehouses>(query.list());
        // Armo el array de respeusta
        beans.Warehouses[] wrhsA = new beans.Warehouses[wrhsAL.size()];
        for (int i = 0; i < wrhsA.length; i++) {
            wrhsA[i] = wrhsAL.get(i);
        }
        // Cierro la sesion
        session.getTransaction().commit();
        session.close();
        
        // Retorno
        final Gson gson = new Gson();
        final String wrhsAJSON = gson.toJson(wrhsA);
        
        // Retorno
        return wrhsAJSON;
    }

    /**
     * Web service operation
     * @param documentNo
     * @param documentType
     * @param username
     * @param authenticator
     * @return 
     */
    @WebMethod(operationName = "getDocument")
    public Documents getDocument(@WebParam(name = "documentNo") String documentNo,
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
        // Jalo los Documents
        String str = "SELECT d, c, cu, oc.name, dc.name, oc.code, dc.code FROM Documents d, Locations ol,  Cities oc, Locations dl, ";
        str += "Cities dc, Customers c, Currencies cu WHERE d.agentOrigenId = ol.id and ol.cityId = oc.id and ";
        str += "d.agentDestinationId = dl.id and cu.id = d.currencyId and d.customerId = c.id and dl.cityId = dc.id and ";
        str += "d.documentNo = :documentNo and d.documentType = :documentType and d.customerId = :customerId";
        Query query = session.createQuery(str);
        System.out.println("parametros "+documentNo+" "+documentType+" "+webUser.getCustomerId());
        query.setString("documentNo", documentNo);
        query.setString("documentType", documentType);
        query.setInteger("customerId", webUser.getCustomerId());
        List docL = query.list();
        // Cierro la sesion
        session.getTransaction().commit();
        session.close();
        // Armo el documento
        Documents document = new Documents();
        if (docL.size() > 0) {
            Object[] inputObj = (Object[]) docL.get(0);
            document = (Documents)inputObj[0];
            beans.Customers customer = (beans.Customers)inputObj[1];
            beans.Currencies currency = (beans.Currencies)inputObj[2];
            String origenName = (String)inputObj[3];
            String destinationName = (String)inputObj[4];
            String origenCode = (String)inputObj[5];
            String destinationCode = (String)inputObj[6];
            // cargo el origenName y el destinationName en el documento
            document.setCustomer(customer);
            document.setCurrency(currency);
            document.setOrigenName(origenName);
            document.setDestinationName(destinationName);
            document.setOrigenCityCode(origenCode);
            document.setDestinationCityCode(destinationCode);
            return document;
        } else {
            return null;
        }
    }

    public ArrayList<Documents> getDocumentsByNoAndType(String[] docsNo, String[] docsType, String username, String authenticator) {
            // Primero se debe validar el usuario
        UsersWS usersSrv = new UsersWS();
        int validation = usersSrv.validateUser(username, authenticator);
        if (validation == 0) {
            System.out.println("Usuario Invalido");
            return null;
        }        
        // Construyo el ArrayList de documentos
        ArrayList<Documents> documentsAL = new ArrayList<Documents>();
        for (int i = 0; i < docsNo.length; i++) {
            Documents document = getDocument(docsNo[i], docsType[i], username, authenticator);
            documentsAL.add(document);
        }
        return documentsAL;
    }

    @WebMethod(operationName = "getDocumentByNo")
    public Documents getDocumentByNo(@WebParam(name = "documentNo") String documentNo,
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
        // Jalo los Documents
        String str = "SELECT d, c, cu, oc.name, dc.name, oc.code, dc.code FROM Documents d, Locations ol,  Cities oc, Locations dl, ";
        str += "Cities dc, Customers c, Currencies cu WHERE d.agentOrigenId = ol.id and ol.cityId = oc.id and ";
        str += "d.agentDestinationId = dl.id and cu.id = d.currencyId and d.customerId = c.id and dl.cityId = dc.id and ";
        str += "d.documentNo = :documentNo  and d.customerId = :customerId";
        Query query = session.createQuery(str);
        System.out.println("parametros "+documentNo+" "+webUser.getCustomerId());
        query.setString("documentNo", documentNo);
        query.setInteger("customerId", webUser.getCustomerId());
        List docL = query.list();
        // Cierro la sesion
        session.getTransaction().commit();
        session.close();
        // Armo el documento
        Documents document = new Documents();
        if (docL.size() > 0) {
            Object[] inputObj = (Object[]) docL.get(0);
            document = (Documents)inputObj[0];
            beans.Customers customer = (beans.Customers)inputObj[1];
            beans.Currencies currency = (beans.Currencies)inputObj[2];
            String origenName = (String)inputObj[3];
            String destinationName = (String)inputObj[4];
            String origenCode = (String)inputObj[5];
            String destinationCode = (String)inputObj[6];
            // cargo el origenName y el destinationName en el documento
            document.setCustomer(customer);
            document.setDocumentType(document.getDocumentType());
            document.setCurrency(currency);
            document.setOrigenName(origenName);
            document.setDestinationName(destinationName);
            document.setOrigenCityCode(origenCode);
            document.setDestinationCityCode(destinationCode);
        } else {
            return null;
        }
        return document;
    }
    
    
    
    /**
     * Web service operation
     * @param serie
     * @param number
     * @param username
     * @param authenticator
     * @return 
     */
    @WebMethod(operationName = "getInvoiceDocuments")
    public String getInvoiceDocuments(@WebParam(name = "serie") String serie,
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
        // Jalo los Documents
        String str = "SELECT d FROM Documents d, IdocumentInvoices di, Iinvoices i WHERE d.documentNo = di.documentNo AND ";
        str += "d.documentType = di.documentType AND di.invoiceId = i.id AND i.serie = :serie AND i.number = :number AND ";
        str += "i.customerId = :customerId ORDER BY i.printDate DESC";
        System.out.println("para "+serie+" "+number+" "+webUser.getCustomerId());
        Query query = session.createQuery(str);
        query.setString("serie", serie);
        query.setString("number", number);
        query.setInteger("customerId", webUser.getCustomerId());
        List docL = query.list();
        // Armo el AL de warheouses
        Documents[] docsA = new Documents[docL.size()];
        for (int i = 0; i < docL.size(); i++) {
           Documents document = (Documents)docL.get(i);
           docsA[i] = document;
        }
        // Cierro la sesion
        session.getTransaction().commit();
        session.close();
        
        // Retorno
        final Gson gson = new Gson();
        final String docsAJSON = gson.toJson(docsA);
        
        // Retorno
        return docsAJSON;
    }

    /**
     * Web service operation
     * @param username
     * @param authenticator
     * @return 
     */
    @WebMethod(operationName = "getDeliveredDocumentsByCustomer")
    public String getDeliveredDocumentsByCustomer(@WebParam(name = "username") String username,
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
        // Jalo los Documents
        String str = "SELECT d, oc.code, dc.code FROM Documents d, Locations ol,  Cities oc, Locations dl,  Cities dc ";
        str += "WHERE d.agentOrigenId = ol.id and ol.cityId = oc.id and d.agentDestinationId = dl.id and dl.cityId = dc.id ";
        str += "and d.customerId = :customerId and d.stepId = 'DV'";
        Query query = session.createQuery(str);
        query.setInteger("customerId", webUser.getCustomerId());
        List docL = query.list();
        // Armo el AL de warheouses
        Documents[] docsA = new Documents[docL.size()];
        for (int i = 0; i < docL.size(); i++) {
            Object[] inputObj = (Object[]) docL.get(i);
            Documents document = (Documents)inputObj[0];
            String origenCityCode = (String) inputObj[1];
            String destinationCityCode = (String) inputObj[2];
            // Agrego el origen y el destino
            document.setOrigenCityCode(origenCityCode);
            document.setDestinationCityCode(destinationCityCode);
            // Agrego el documento
            docsA[i] = document;
        }
        // Cierro la sesion
        session.getTransaction().commit();
        session.close();
        // Retorno
        
        final Gson gson = new Gson();
        final String docsAJSON = gson.toJson(docsA);
        
        String resp = docsAJSON.replace('/', '-');
        resp =resp.replace('\\', ' ');
        resp =resp.replace('\n', ' ');
        
        
        return resp;
    }

}
