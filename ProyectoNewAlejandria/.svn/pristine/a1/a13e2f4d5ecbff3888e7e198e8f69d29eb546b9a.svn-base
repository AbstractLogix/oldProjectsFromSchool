/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package webservices;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author Otto
 */
@WebService()
public class ReportsWS {

    public String absolutePath;
    public String jasperPath;
    public String pdfPath;
    //public String user;

    /** Creates a new instance of reportes */
    public ReportsWS() {
        absolutePath = this.getClass().getResource("/").toString().substring(6); //quito la parte del url que empieza con file:/
        absolutePath = absolutePath.substring(0, absolutePath.indexOf("WEB-INF/classes/")) + "reports/"; //quito la parte del path a las clases y lo dejo hasta /web
        absolutePath = absolutePath.replace("%20", " "); //Si el path tiene espacios java le pone %20 entonces lo reemplazo por espacio
        jasperPath = absolutePath + "jasper/";
        pdfPath = absolutePath + "pdf/";
        /*if (flashgateway.Gateway.getHttpRequest() != null) { //No nos preocupa pues solo nos sirve para crear attachment de correo, y aqui no se usa
            System.out.println("gethttpreques est null");
            user = (String) flashgateway.Gateway.getHttpRequest().getSession().getAttribute("user");
        } else {
            user = "";
        }*/
    }

    /**
     * Web service operation
     * @param warehouseId
     * @param username
     * @param authenticator
     * @return 
     */
    @WebMethod(operationName = "createReceipt")
    public String createReceipt(@WebParam(name = "warehouseId") int warehouseId,
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
        // Creo el reporte
        String ret = "";
        try {
            UtilityWS utility = new UtilityWS();
            ret = utility.dateToString(Calendar.getInstance());
            File urlMasterReport = new File(jasperPath + "wrh_beans.jasper");
            File urlSubReport = new File(jasperPath + "wrh_detail_bean.jasper");
            HashMap masterParams = new HashMap();

            JRDataSource datasource = new JRBeanCollectionDataSource(webservices.WarehousesWS.createBeanCollection(warehouseId, webUser.getCustomerId()));
            JasperReport masterReport = (JasperReport) JRLoader.loadObject(urlMasterReport);
            JasperReport subReport = (JasperReport) JRLoader.loadObject(urlSubReport);
            masterParams.put("SUBREPORT", subReport);

            JasperPrint print = JasperFillManager.fillReport(masterReport, masterParams, datasource);
            JasperExportManager.exportReportToPdfFile(print, pdfPath + ret + "_warehouseReceipt.pdf");
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

}
