/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package webservices;

import beans.DocumentTracking;
import beans.Track;
import beans.Tracking;
import java.util.ArrayList;
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
public class TrackingWS {

    /**
     * Web service operation
     * @param warehouseId
     * @param username
     * @param authenticator
     * @return 
     */
    @WebMethod(operationName = "getTrackingByWarehouse")
    public Track[] getTrackingByWarehouse(@WebParam(name = "warehouseId") int warehouseId,
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
        // Jalo los tracking de este warehouse
        //System.out.println("warehouseId "+warehouseId);
        Query query = session.createQuery("select t from Tracking t where t.warehouseId = :warehouseId order by t.trackingDate desc");
        query.setInteger("warehouseId", warehouseId);
        ArrayList<Tracking> trackingAL = new ArrayList<Tracking>(query.list());
        // Construyo el ArrayList de Tracks
        Track[] trackA = new Track[trackingAL.size()];
        //System.out.println("largooooo "+trackingAL.size());
        for (int i = 0; i < trackingAL.size(); i++) {
            Track track = new Track();
            track.setTrackDate(trackingAL.get(i).getTrackingDate());
            track.setMessage(trackingAL.get(i).getMessage());
            trackA[i] = track;
        }
        // Cierro la sesion
        session.getTransaction().commit();
        session.close();
        // Regreso
        return trackA;
    }

    /**
     * Web service operation
     * @param documentNo
     * @param username
     * @param authenticator
     * @return 
     */
    @WebMethod(operationName = "getTrackingByDocument")
    public String getTrackingByDocument(@WebParam(name = "documentNo") String documentNo,
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
        // Jalo los tracking de este document
        Query query = session.createQuery("from DocumentTracking where documentNo = :documentNo order by trackingDate desc");
        query.setString("documentNo", documentNo);
        ArrayList<DocumentTracking> trackingAL = new ArrayList<DocumentTracking>(query.list());
        // Construyo el ArrayList de Tracks
        Track[] trackA = new Track[trackingAL.size()];
        for (int i = 0; i < trackingAL.size(); i++) {
            Track track = new Track();
            track.setTrackDate(trackingAL.get(i).getTrackingDate());
            track.setMessage(trackingAL.get(i).getMessage());
            trackA[i] = track;
        }
        // Cierro la sesion
        session.getTransaction().commit();
        session.close();
        System.out.println("el largo de lo quer retorno es "+trackA.length);
        // Regreso
        
        // Regreso
        final Gson gson = new Gson();
        final String trackAJSON = gson.toJson(trackA);
        
        return trackAJSON;
    }

    /**
     * Web service operation
     * @param trackingNo
     * @param username
     * @param authenticator
     * @return 
     */
    @WebMethod(operationName = "getTrackingByCarrierNo")
    public Track[] getTrackingByCarrierNo(@WebParam(name = "trackingNo") String trackingNo,
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
        // Jalo los tracking de este trackingNo
        Query query = session.createQuery("select t from Tracking t, WarehouseTracking wt where wt.warehouseId = t.warehouseId and wt.tracking = :trackingNo order by t.trackingDate desc");
        query.setString("trackingNo", trackingNo);
        ArrayList<Tracking> trackingAL = new ArrayList<Tracking>(query.list());
        // Construyo el ArrayList de Tracks
        Track[] trackA = new Track[trackingAL.size()];
        for (int i = 0; i < trackingAL.size(); i++) {
            Track track = new Track();
            track.setTrackDate(trackingAL.get(i).getTrackingDate());
            track.setMessage(trackingAL.get(i).getMessage());
            trackA[i] = track;
        }
        // Cierro la sesion
        session.getTransaction().commit();
        session.close();
        // Regreso
        return trackA;
    }

}
