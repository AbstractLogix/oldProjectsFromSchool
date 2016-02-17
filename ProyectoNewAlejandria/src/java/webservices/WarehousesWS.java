/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package webservices;

import beans.DocumentWarehouses;
import beans.Shippers;
import beans.WarehouseDetails;
import beans.Warehouses;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
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
public class WarehousesWS {

    /**
     * Web service operation
     * @param poNo
     * @param username
     * @param authenticator
     * @return 
     */
    @WebMethod(operationName = "getWarehousesIdByPoNo")
    public int[] getWarehousesIdByPoNo(@WebParam(name = "poNo") String poNo,
            @WebParam(name = "username") String username,
            @WebParam(name = "authenticator") String authenticator) {
        // Primero se debe validar el usuario
        UsersWS usersSrv = new UsersWS();
        int validation = usersSrv.validateUser(username, authenticator);
        if (validation == 0) {
            System.out.println("Usuario Invalido");
            return null;
        }
        // abro la sesion
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        // Jalo los warehouses para este poNo
        Query query = session.createQuery("from Warehouses where orderNo = :poNo order by initDate desc");
        query.setString("poNo", poNo);
        ArrayList<Warehouses> trackingAL = new ArrayList<Warehouses>(query.list());
        // Construyo el array de wrhIds
        int[] wrhIdA = new int[trackingAL.size()];
        for (int i = 0; i < trackingAL.size(); i++) {
            wrhIdA[i] = trackingAL.get(i).getId();
        }
        // Cierro la sesion
        session.getTransaction().commit();
        session.close();
        // Regreso
        return wrhIdA;
    }

    /**
     * Web service operation
     * @param documentNo
     * @param username
     * @param authenticator
     * @return 
     */
    @WebMethod(operationName = "getWarehousesIdByDocumentNo")
    public int[] getWarehousesIdByDocumentNo(@WebParam(name = "documentNo") String documentNo,
            @WebParam(name = "username") String username,
            @WebParam(name = "authenticator") String authenticator) {
        // Primero se debe validar el usuario
        UsersWS usersSrv = new UsersWS();
        int validation = usersSrv.validateUser(username, authenticator);
        if (validation == 0) {
            System.out.println("Usuario Invalido");
            return null;
        }
        // Habro la sesion
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        // Jalo los warehouses para este poNo
        Query query = session.createQuery("from DocumentWarehouses where documentNo = :documentNo order by warehosueId asc");
        query.setString("documentNo", documentNo);
        ArrayList<DocumentWarehouses> trackingAL = new ArrayList<DocumentWarehouses>(query.list());
        // Construyo el array de wrhIds
        int[] wrhIdA = new int[trackingAL.size()];
        System.out.println("antes del for ");
        for (int i = 0; i < trackingAL.size(); i++) {
            System.out.println("pero si entra.. pro que no funca "+trackingAL.get(i).getWarehosueId());
            wrhIdA[i] = trackingAL.get(i).getWarehosueId();
        }
        // Cierro la sesion
        session.getTransaction().commit();
        session.close();
        // Regreso
        System.out.println("largo "+wrhIdA.length);
        return wrhIdA;
    }

    /**
     * Web service operation
     * @param warehouseId
     * @param username
     * @param authenticator
     * @return 
     */
    @WebMethod(operationName = "getDocumentsNoByWarehouseId")
    public java.lang.String[] getDocumentsNoByWarehouseId(@WebParam(name = "warehouseId") int warehouseId,
            @WebParam(name = "username") String username,
            @WebParam(name = "authenticator") String authenticator) {
        // Primero se debe validar el usuario
        UsersWS usersSrv = new UsersWS();
        int validation = usersSrv.validateUser(username, authenticator);
        if (validation == 0) {
            System.out.println("Usuario Invalido");
            return null;
        }
        // Habro la sesion
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        // Jalo los warehouses para este poNo
        Query query = session.createQuery("from DocumentWarehouses where warehosueId = :warehouseId order by documentNo asc");
        query.setInteger("warehouseId", warehouseId);
        ArrayList<DocumentWarehouses> trackingAL = new ArrayList<DocumentWarehouses>(query.list());
        // Construyo el array de wrhIds
        String[] docNoA = new String[trackingAL.size()];
        for (int i = 0; i < trackingAL.size(); i++) {
            docNoA[i] = trackingAL.get(i).getDocumentNo();
        }
        // Cierro la sesion
        session.getTransaction().commit();
        session.close();
        // Regreso        
        return docNoA;      
    }

    /**
     * Web service operation
     * @param username
     * @param authenticator
     * @return 
     */
    @WebMethod(operationName = "getWarehousesOnOHRTLCFCCSByCustomer")
    public String getWarehousesOnOHRTLCFCCSByCustomer(@WebParam(name = "username") String username,
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
        String str = "SELECT w, s.name, c.code, (select sum(wd.pieces) from WarehouseDetails wd where warehouseId = w.id), ";
        str += "(select sum(wd.weight) from WarehouseDetails wd where warehouseId = w.id), ";
        str += "(select sum(round((wd.pieces*wd.height*wd.length*wd.width)/166,0)) from WarehouseDetails wd where warehouseId = w.id), ";
        str += "(select sum(round((wd.pieces*wd.height*wd.length*wd.width)/1728,0)) from WarehouseDetails wd where warehouseId = w.id) ";
        str += "FROM Warehouses w, Shippers s, Locations ol,  Cities c where ol.cityId = c.id and ";
        str += "w.locationId = ol.id and w.shipperId = s.id and (w.stepId = 'OH' OR w.stepId = 'RT' OR w.stepId = 'LC' ";
        str += "OR w.stepId = 'FC' OR w.stepId = 'CS' OR (w.stepId = 'TR' and w.routingId = 'LCL' and w.id not in ";
        str += "(select dw.warehosueId from DocumentWarehouses dw)) ) AND w.customerId = :customerId order by w.initDate asc";
        Query query = session.createQuery(str);
        query.setInteger("customerId", webUser.getCustomerId());
        List wrhL = query.list();
        // Armo el AL de warheouses
        ArrayList<Warehouses> wrhsA = new ArrayList<Warehouses>();
        for (int i = 0; i < wrhL.size(); i++) {
            Object[] inputObj = (Object[]) wrhL.get(i);
            Warehouses wrh = (Warehouses)inputObj[0];
            String shipperName = (String) inputObj[1];
            String origenCity = (String) inputObj[2];
            Integer pieces = ((Long)inputObj[3]).intValue();
            Double weight = ((BigDecimal)inputObj[4]).doubleValue();
            Double volWeight = ((BigDecimal)inputObj[5]).doubleValue();
            Double cft = ((BigDecimal)inputObj[6]).doubleValue();
            // Le agrego shipper al warehouse
            Shippers shipper = new Shippers();
            shipper.setName(shipperName);
            wrh.setShipper(shipper);
            // Le agrego el origenCity, pieces, weight, vol weight, cft
            wrh.setOrigenCity(origenCity);
            wrh.setPieces(pieces);
            wrh.setWeight(weight);
            wrh.setVolWeight(volWeight);
            wrh.setCft(cft);
            // Agrego el warehouse
            wrhsA.add(wrh);
        }
        // Cierro la sesion
        session.getTransaction().commit();
        session.close();
        
        // Regreso
        final Gson gson = new Gson();
        final String representacionJSON = gson.toJson(wrhsA);
        
        return representacionJSON;
    }

    public ArrayList<Warehouses> getWarehousesById(int[] wrhsId, String username, String authenticator) {
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
        // Jalo los warehouses
        String str = "SELECT w, s.name, c.code, (select sum(wd.pieces) from WarehouseDetails wd where warehouseId = w.id), ";
        str += "(select sum(wd.weight) from WarehouseDetails wd where warehouseId = w.id), ";
        str += "(select sum(round((wd.pieces*wd.height*wd.length*wd.width)/166,0)) from WarehouseDetails wd where warehouseId = w.id), ";
        str += "(select sum(round((wd.pieces*wd.height*wd.length*wd.width)/1728,0)) from WarehouseDetails wd where warehouseId = w.id) ";
        str += "FROM Warehouses w, Shippers s, Locations ol,  Cities c WHERE ol.cityId = c.id and ";
        str += "w.locationId = ol.id and w.shipperId = s.id and w.customerId = :customerId  and (";
        for (int i = 0; i < wrhsId.length; i++) {
            str += "w.id = "+wrhsId[i]+" or ";
        }
        str += "1 = 0) order by w.initDate asc";
        Query query = session.createQuery(str);
        query.setInteger("customerId", webUser.getCustomerId());
        List wrhL = query.list();
        // Armo el AL de warheouses
        ArrayList<Warehouses> wrhsAL = new ArrayList<Warehouses>();
        for (int i = 0; i < wrhL.size(); i++) {
            Object[] inputObj = (Object[]) wrhL.get(i);
            Warehouses wrh = (Warehouses) inputObj[0];
            String shipperName = (String) inputObj[1];
            String origenCity = (String) inputObj[2];
            Integer pieces = ((Long)inputObj[3]).intValue();
            Double weight = ((BigDecimal)inputObj[4]).doubleValue();
            Double volWeight = ((BigDecimal)inputObj[5]).doubleValue();
            Double cft = ((BigDecimal)inputObj[6]).doubleValue();
            // Le agrego shipper al warehouse
            Shippers shipper = new Shippers();
            shipper.setName(shipperName);
            wrh.setShipper(shipper);
            // Le agrego el origenCity, pieces, weight, vol weight, cft
            wrh.setOrigenCity(origenCity);
            wrh.setPieces(pieces);
            wrh.setWeight(weight);
            wrh.setVolWeight(volWeight);
            wrh.setCft(cft);
            // Agrego el warehouse
            wrhsAL.add(wrh);
        }
        // Cierro la sesion
        session.getTransaction().commit();
        session.close();
        // Regreso
        return wrhsAL;
    }


    /**
     * Web service operation
     * @param warehouseId
     * @param username
     * @param authenticator
     * @return 
     */
    @WebMethod(operationName = "getWarehouse")
    public String getWarehouse(@WebParam(name = "warehouseId") int warehouseId,
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
        // Jalo el warehouse
        String str = "SELECT w, s, c, ic, st, ";
        str += "(select sum(wd.pieces) from WarehouseDetails wd where warehouseId = w.id), ";
        str += "(select sum(wd.weight) from WarehouseDetails wd where warehouseId = w.id), ";
        str += "(select sum(round((wd.pieces*wd.height*wd.length*wd.width)/166,0)) from WarehouseDetails wd where warehouseId = w.id), ";
        str += "(select sum(round((wd.pieces*wd.height*wd.length*wd.width)/1728,0)) from WarehouseDetails wd where warehouseId = w.id) ";
        str += "FROM Warehouses w, Shippers s, Customers c, Carriers ic, Status st WHERE w.id = :warehouseId and w.customerId = :customerId ";
        str += "and w.shipperId = s.id and w.customerId = c.id and w.inlandCarrierId = ic.id and w.statusId = st.id";
        Query query = session.createQuery(str);
        query.setInteger("warehouseId", warehouseId);
        query.setInteger("customerId", webUser.getCustomerId());
        List wrhL = query.list();
        // Cargo el resultado
        Warehouses wrh = new Warehouses();
        if (wrhL.isEmpty()) {
            return null;
        } else {
            Object[] inputObj = (Object[]) wrhL.get(0);
            wrh = (Warehouses)inputObj[0];
            Shippers shipper = (Shippers)inputObj[1];
            beans.Customers customer = (beans.Customers)inputObj[2];
            beans.Carriers inlandCarrier = (beans.Carriers)inputObj[3];
            beans.Status status = (beans.Status)inputObj[4];
            Integer pieces = ((Long)inputObj[5]).intValue();
            Double weight = ((BigDecimal)inputObj[6]).doubleValue();
            Double volWeight = ((BigDecimal)inputObj[7]).doubleValue();
            Double cft = ((BigDecimal)inputObj[8]).doubleValue();
            
            // Cargo el shipper, customer, inland carrier en el wrh
            wrh.setShipper(shipper);
            wrh.setCustomer(customer);
            wrh.setInlandCarrier(inlandCarrier);
            wrh.setPieces(pieces);
            wrh.setWeight(weight);
            wrh.setVolWeight(volWeight);
            wrh.setCft(cft);
            wrh.setStatus(status);
            // Cargo los warehouseDetails
            WarehousesWS wrhWS = new WarehousesWS();
            wrh.setWarehouseDetail(wrhWS.getWarehouseDetails(warehouseId));
        }
                // Regreso
        final Gson gson = new Gson();
        final String representacionJSON = gson.toJson(wrh);
        
        return representacionJSON;
    }

    private ArrayList<WarehouseDetails> getWarehouseDetails(int warehouseId) {
        ArrayList<WarehouseDetails> wrhDetails = new ArrayList<WarehouseDetails>();
        // Abro la sesion
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        // Jalo los warehouses para este wrhId
        String str = "SELECT wd, round((wd.pieces*wd.height*wd.length*wd.width)/166,0), ";
        str += "round((wd.pieces*wd.height*wd.length*wd.width)/1728,0) FROM WarehouseDetails wd ";
        str += "WHERE wd.warehouseId = :warehouseId";
        Query query = session.createQuery(str);
        query.setInteger("warehouseId", warehouseId);
        List wdL = query.list();
        // Armo el V de warheouses
        for (int i = 0; i < wdL.size(); i++) {
            Object[] inputObj = (Object[]) wdL.get(i);
            WarehouseDetails wrhDetail = (WarehouseDetails) inputObj[0];
            Double volWeight = ((BigDecimal)inputObj[1]).doubleValue();
            Double cft = ((BigDecimal)inputObj[2]).doubleValue();
            // Cargo el volWeight y cft
            wrhDetail.setVolWeight(volWeight);
            wrhDetail.setCft(cft);
            // Agrego el warehouseDetail al resultado
            wrhDetails.add(wrhDetail);
        }
        // Cierro la sesion
        session.getTransaction().commit();
        session.close();
        // Regresar
        return wrhDetails;
    }

    public static Vector createBeanCollection(int warehouseId, int customerId) {
        Vector vector = new Vector();
        // abro la sesion
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        // Jalo los warehouses para este wrhId
        String str = "SELECT w, c.name, c.lastname, ic, lr, s FROM Warehouses w, Customers c, Carriers ic, LocationRacks lr, ";
        str += "Shippers s WHERE w.customerId = c.id AND w.inlandCarrierId = ic.id AND w.locationRackId = lr.id AND ";
        str += "w.shipperId = s.id AND w.id = :warehouseId AND c.id = :customerId";
        Query query = session.createQuery(str);
        query.setInteger("warehouseId", warehouseId);
        query.setInteger("customerId", customerId);
        List wrhL = query.list();
        // Cierro la sesion
        session.getTransaction().commit();
        session.close();
        // Armo el V de warheouses
        for (int i = 0; i < wrhL.size(); i++) {
            Object[] inputObj = (Object[]) wrhL.get(i);
            Warehouses wrh = (Warehouses) inputObj[0];
            String customerName = (String) inputObj[1];
            String customerLastname = (String) inputObj[2];
            beans.Carriers inlandCarrier = (beans.Carriers) inputObj[3];
            beans.LocationRacks locationRack = (beans.LocationRacks) inputObj[4];
            beans.Shippers shipper = (beans.Shippers)inputObj[5];
            // Construyo el Consignee y agrego
            beans.Customers customer = new beans.Customers();
            customer.setName(customerName);
            customer.setLastname(customerLastname);
            wrh.setCustomer(customer);
            // Cargo el inlandCarrier
            wrh.setInlandCarrier(inlandCarrier);
            // Cargo el locationRack
            wrh.setLocationRack(locationRack);
            // Cargo el shipper
            wrh.setShipper(shipper);
            // Cargo los warehouseDetails
            WarehousesWS wrhWS = new WarehousesWS();
            wrh.setWarehouseDetail(wrhWS.getWarehouseDetails(warehouseId));
            // Agrego el warehouse al resultado
            vector.add(wrh);
        }
        // regreso
        return vector;
    }
      
}


