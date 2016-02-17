/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import java.util.ArrayList;
import java.util.Calendar;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import utils.ExcelCreator;
import utils.Utility;

/**
 *
 * @author IT2
 */
@WebService(serviceName = "ExcelRpts")
public class ExcelRpts {
    
    
    public String absolutePath;
    public String excelPath;
    //public String user;

    public ExcelRpts() {
        excelPath = "web//reports//excel//";
        /*if (flashgateway.Gateway.getHttpRequest() != null) { //No nos preocupa pues solo nos sirve para crear attachment de correo, y aqui no se usa
            System.out.println("gethttpreques est null");
            user = (String) flashgateway.Gateway.getHttpRequest().getSession().getAttribute("user");
        } else {
            user = "";
        }*/
    }

    private ArrayList<String> getRegularHeader() {
        ArrayList<String> top = new ArrayList<String>();
        top.add("CPS Logistics & Transportation");
        top.add("10914 NW 33 ST Suite 115");
        top.add("Doral, FL 33172  PH: 305-534-7675");
        return top;
    }

    private ArrayList<String> getRegularFooter() {
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        //services.ReportPathService reportPathService = new services.ReportPathService();
        //String username = reportPathService.getUser();
        java.util.Date now = new java.util.Date();
        ArrayList<String> footer = new ArrayList<String>();
        //footer.add("User: "+username);
        footer.add("Date: "+dateFormat.format(now));
        return footer;
    }
    
    
    /**
     * Web service operation
     * @param id_empresa
     * @param username
     * @param authenticator
     * @return 
     */
    @WebMethod(operationName = "createMovimientosAbiertosByEmpresaReport")
    public String createMovimientosAbiertosByEmpresaReport(@WebParam(name = "id_empresa") int id_empresa,
            @WebParam(name = "username") String username,
            @WebParam(name = "authenticator") String authenticator) {
        String result = "~/reportes/excel";
        try {
            Utility utility = new Utility();
            result = utility.dateToString(Calendar.getInstance());
            // SET DE LOS TOP HEADERS
            ArrayList<String> top = this.getRegularHeader();
            // SET DE LOS FOOTER
            ArrayList<String> footer = this.getRegularFooter();
            // SET LOS TYPES Y HEADERS
            ArrayList<Integer> types = new ArrayList<Integer>();
            ArrayList<String> headers = new ArrayList<String>();
            types.add(ExcelCreator.TYPE_STRING);   headers.add("Serie");
            types.add(ExcelCreator.TYPE_STRING);    headers.add("Number");
            types.add(ExcelCreator.TYPE_INTEGER);    headers.add("Due");
            if (id_empresa == 2) {
                types.add(ExcelCreator.TYPE_CURRENCY_QUE);    headers.add("Amount");
            } else if (id_empresa == 7) {
                types.add(ExcelCreator.TYPE_CURRENCY_USD);    headers.add("Amount");
            }

            // CREO EL EXCEL
            String fileName = excelPath + result + "_movimientosReport";
            ExcelCreator excel = new ExcelCreator(headers, null , types, fileName, "Balance Statement", top, footer);
            String nombre = excel.createExcel();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
