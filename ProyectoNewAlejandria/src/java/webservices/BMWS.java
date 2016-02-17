/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package webservices;

import beans.TblCxcMovimientos;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;
import utils.Utility;
import com.google.gson.*;

/**
 *
 * @author Otto
 */
@WebService()
public class BMWS {

    /**
     * Web service operation
     * @param idempresa
     * @param username
     * @param authenticator
     * @return 
     */
    @WebMethod(operationName = "getMovimientosAbiertosByEmpresa")
    public String getMovimientosAbiertosByEmpresa(@WebParam(name = "idempresa") int idempresa,
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
        Session session = HibernateUtil.getSessionFactory(idempresa).openSession();
        session.beginTransaction();
        // Jalo los Movimientos Abiertos
        String str = "SELECT tcm,  (SELECT SUM(tcdm.monto) FROM TblCxcDetalleMovimientos tcdm WHERE tcdm.id.idVoucher = tcm.id.idVoucher ";
        str += "AND tcdm.id.idEmpresa = tcm.id.idEmpresa) FROM TblCxcMovimientos tcm WHERE tcm.id.idCliente = :idCliente ";
        str += "AND tcm.id.idEmpresa = :idEmpresa AND ((SELECT SUM(tcdm1.monto) FROM TblCxcDetalleMovimientos tcdm1 ";
        str += "WHERE tcdm1.id.idEmpresa = tcm.id.idEmpresa AND tcdm1.id.idVoucher = tcm.id.idVoucher) <> 0) ORDER BY tcm.fecha DESC";
        Query query = session.createQuery(str);
        query.setInteger("idCliente", webUser.getCustomerId());
        query.setInteger("idEmpresa", idempresa);
        //query.setInteger("idEmpresa1", id_empresa);
        List tcmL = query.list();
        // Armo el A de tcm
        TblCxcMovimientos[] result = new TblCxcMovimientos[tcmL.size()];
        for (int i = 0; i < tcmL.size(); i++) {
            Object[] inputObj = (Object[]) tcmL.get(i);
            TblCxcMovimientos tcm = (TblCxcMovimientos)inputObj[0];
            Double saldo = ((BigDecimal)inputObj[1]).doubleValue();
            // Calculo los dis que lleva el documento vencido
            int dias_vencida = 0;
            java.util.Date now = new java.util.Date();
            Calendar fromC = Calendar.getInstance();
            fromC.setTime(tcm.getFechaVence());
            fromC.set(Calendar.HOUR_OF_DAY, 0);
            fromC.set(Calendar.MINUTE, 0);
            fromC.set(Calendar.SECOND, 0);
            fromC.set(Calendar.MILLISECOND, 0);
            Calendar toC = Calendar.getInstance();
            toC.setTime(now);
            toC.set(Calendar.HOUR_OF_DAY, 0);
            toC.set(Calendar.MINUTE, 0);
            toC.set(Calendar.SECOND, 0);
            toC.set(Calendar.MILLISECOND, 0);
            long difDaysL = ((toC.getTime().getTime() - fromC.getTime().getTime()) / (1000 * 60 * 60 * 24));
            int difDays = (int) difDaysL;
            if (difDays > 0) {
                dias_vencida = difDays;
            }
            // Agrego el saldo y el diasVencida al tcm
            tcm.setSaldo(saldo);
            tcm.setDiasVencida(dias_vencida);
            // Agrego el tcm al resultado
            result[i] = tcm;
        }
        
        // Retorno
        final Gson gson = new Gson();
        final String resultJSON = gson.toJson(result);
        
        return resultJSON;
    }

    /**
     * Web service operation
     * @param id_empresa
     * @param beginingDate
     * @param endDate
     * @param username
     * @param authenticator
     * @return 
     */
    @WebMethod(operationName = "getMovimientosByDates")
    public beans.TblCxcMovimientos[] getMovimientosByDates(@WebParam(name = "id_empresa") int id_empresa,
            @WebParam(name = "beginingDate") Date beginingDate,
            @WebParam(name = "endDate") Date endDate,
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
        // Abro la sesion
        Session session = HibernateUtil.getSessionFactory(id_empresa).openSession();
        session.beginTransaction();
        // Jalo los Movimientos dentro del rango de fechas
        String str = "SELECT tcm,  (SELECT SUM(tcdm.monto) FROM TblCxcDetalleMovimientos tcdm WHERE tcdm.id.idVoucher = tcm.id.idVoucher ";
        str += "AND tcdm.id.idEmpresa = tcm.id.idEmpresa) FROM TblCxcMovimientos tcm WHERE tcm.id.idCliente = :idCliente ";
        str += "AND tcm.id.idEmpresa = :idEmpresa AND tcm.fecha >= :beginingDate AND tcm.fecha <= :endDate ORDER BY tcm.fecha DESC";
        Query query = session.createQuery(str);
        // Preparo los campos
        Utility utility = new Utility();
        //System.out.println("paramt "+utility.completeDateToLower(beginingDate)+" "+utility.completeDateToUpper(endDate));
        //System.out.println("paramt "+webUser.getCustomerId()+" "+id_empresa);
        query.setInteger("idCliente", webUser.getCustomerId());
        query.setInteger("idEmpresa", id_empresa);
        query.setString("beginingDate", utility.completeDateToLower(beginingDate));
        query.setString("endDate", utility.completeDateToUpper(endDate));
        //query.setInteger("idEmpresa1", id_empresa);
        List tcmL = query.list();
        // Armo el A de tcm
        TblCxcMovimientos[] result = new TblCxcMovimientos[tcmL.size()];
        for (int i = 0; i < tcmL.size(); i++) {
            Object[] inputObj = (Object[]) tcmL.get(i);
            TblCxcMovimientos tcm = (TblCxcMovimientos)inputObj[0];
            Double saldo = ((BigDecimal)inputObj[1]).doubleValue();
            // Calculo los dis que lleva el documento vencido
            int dias_vencida = 0;
            java.util.Date now = new java.util.Date();
            Calendar fromC = Calendar.getInstance();
            fromC.setTime(tcm.getFechaVence());
            fromC.set(Calendar.HOUR_OF_DAY, 0);
            fromC.set(Calendar.MINUTE, 0);
            fromC.set(Calendar.SECOND, 0);
            fromC.set(Calendar.MILLISECOND, 0);
            Calendar toC = Calendar.getInstance();
            toC.setTime(now);
            toC.set(Calendar.HOUR_OF_DAY, 0);
            toC.set(Calendar.MINUTE, 0);
            toC.set(Calendar.SECOND, 0);
            toC.set(Calendar.MILLISECOND, 0);
            long difDaysL = ((toC.getTime().getTime() - fromC.getTime().getTime()) / (1000 * 60 * 60 * 24));
            int difDays = (int) difDaysL;
            if (difDays > 0 && saldo != 0) {
                dias_vencida = difDays;
            }
            // Agrego el saldo y el diasVencida al tcm
            tcm.setSaldo(saldo);
            tcm.setDiasVencida(dias_vencida);
            // Agrego el tcm al resultado
            result[i] = tcm;
        }
        return result;
    }
}
