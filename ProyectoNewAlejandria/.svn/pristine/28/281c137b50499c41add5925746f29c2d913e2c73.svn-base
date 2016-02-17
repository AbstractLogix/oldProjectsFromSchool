/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package webservices;

import beans.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import utils.ExcelCreator;
import utils.Utility;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.ProcessBuilder.Redirect.Type;
import java.net.URL;
import java.util.List;

/**
 *
 * @author Otto
 */
@WebService()
public class ExcelReportsWS {

    public String absolutePath;
    public String excelPath;
    //public String user;

    public ExcelReportsWS() {
        excelPath = "C:\\Users\\IT2\\Documents\\ProyectoNewAlejandria\\web\\reports\\excel\\";
       //
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
     * @param LwrhsId
     * @param username
     * @param authenticator
     * @return 
     */
    @WebMethod(operationName = "createWarehousesReport")
    public String createWarehousesReport(@WebParam(name = "LwrhsId") String LwrhsId,
            @WebParam(name = "username") String username,
            @WebParam(name = "authenticator") String authenticator) {
        String result = "";
        try {
            //Conversion Extra
            String[] LW = LwrhsId.substring(0, LwrhsId.length()-1).split(",");
            int[] wrhsId = new int[LW.length];
            
            for (int i = 0; i<LW.length ; i++) {
              wrhsId[i] =  Integer.parseInt(LW[i]);
            }
            
            //Conversion Extra
            
            UtilityWS utility = new UtilityWS();
            result = utility.dateToString(Calendar.getInstance());
            // SET DE LOS TOP HEADERS
            ArrayList<String> top = this.getRegularHeader();
            // SET DE LOS FOOTER
            ArrayList<String> footer = this.getRegularFooter();
            // SET LOS TYPES Y HEADERS
            ArrayList<Integer> types = new ArrayList<Integer>();
            ArrayList<String> headers = new ArrayList<String>();
            types.add(ExcelCreator.TYPE_INTEGER);   headers.add("Wrh Id");
            types.add(ExcelCreator.TYPE_STRING);    headers.add("Step");
            types.add(ExcelCreator.TYPE_STRING);    headers.add("Origen");
            types.add(ExcelCreator.TYPE_STRING);    headers.add("Destination");
            types.add(ExcelCreator.TYPE_STRING);    headers.add("Routing");
            types.add(ExcelCreator.TYPE_INTEGER);   headers.add("Pieces");
            types.add(ExcelCreator.TYPE_DOUBLE);    headers.add("Weight");
            types.add(ExcelCreator.TYPE_DOUBLE);    headers.add("Vol Weight");
            types.add(ExcelCreator.TYPE_DOUBLE);    headers.add("CFT");
            types.add(ExcelCreator.TYPE_STRING);    headers.add("Shipper");
            types.add(ExcelCreator.TYPE_STRING);    headers.add("Invoice");
            types.add(ExcelCreator.TYPE_DOUBLE);    headers.add("Merc. Value");
            types.add(ExcelCreator.TYPE_STRING);    headers.add("Tracking No");
            types.add(ExcelCreator.TYPE_STRING);    headers.add("Order No");
            // CARGO LA DATA
            WarehousesWS wrhWS = new WarehousesWS();
            ArrayList<beans.Warehouses> wrhsAL = wrhWS.getWarehousesById(wrhsId, username, authenticator);
            ArrayList<ArrayList> data = new ArrayList<ArrayList>();
            for (int i = 0; i < wrhsAL.size(); i++) {
                // Jalo la informacion del wrh
                beans.Warehouses wrh = (beans.Warehouses)wrhsAL.get(i);
                // Busco el numero de factura
                String[] marksA = wrh.getMarks().split(" ");
                String invoiceS = "";
                for (int ii = 0; ii < marksA.length - 1; ii++) {
                    if (marksA[ii].equalsIgnoreCase("INV:")) {
                        invoiceS = marksA[ii+1];
                        ii = marksA.length;
                    }
                }
                // Lleno la informacion para el reporte
                ArrayList row = new ArrayList();
                row.add(wrh.getId());
                row.add(wrh.getStepId());
                row.add(wrh.getOrigenCity());
                row.add(wrh.getDestinationId());
                row.add(wrh.getRoutingId());
                row.add(wrh.getPieces());
                row.add(wrh.getWeight());
                row.add(wrh.getVolWeight());
                row.add(wrh.getCft());
                row.add(wrh.getShipper().getName());
                row.add(invoiceS);
                row.add(wrh.getMercValue().doubleValue());
                row.add(wrh.getProNumber());
                row.add(wrh.getOrderNo());
                data.add(row);
            }
            // CREO EL EXCEL
            String fileName = excelPath + result + "_warehousesReport";
            ExcelCreator excel = new ExcelCreator(headers, data, types, fileName, "Cargo Report", top, footer);
            String nombre = excel.createExcel();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Web service operation
     * @param LdocsNo
     * @param LdocsType
     * @param username
     * @param authenticator
     * @return 
     */
    @WebMethod(operationName = "createShippmentsReport")
    public String createShippmentsReport(@WebParam(name = "LdocsNo") String LdocsNo,
            @WebParam(name = "LdocsType") String LdocsType,
            @WebParam(name = "username") String username,
            @WebParam(name = "authenticator") String authenticator) {
        
        
        String result = "";
        try {
            
            
             //Conversion Extra
            String[] docsNo = LdocsNo.substring(0, LdocsNo.length()-1).split(",");
            String[] docsType = LdocsType.substring(0, LdocsType.length()-1).split(",");
            
            UtilityWS utility = new UtilityWS();
            result = utility.dateToString(Calendar.getInstance());
            // SET DE LOS TOP HEADERS
            ArrayList<String> top = this.getRegularHeader();
            // SET DE LOS FOOTER
            ArrayList<String> footer = this.getRegularFooter();
            // SET LOS TYPES Y HEADERS
            ArrayList<Integer> types = new ArrayList<Integer>();
            ArrayList<String> headers = new ArrayList<String>();
            types.add(ExcelCreator.TYPE_STRING);    headers.add("Document No");
            types.add(ExcelCreator.TYPE_STRING);    headers.add("Routing");
            types.add(ExcelCreator.TYPE_STRING);    headers.add("Origen");
            types.add(ExcelCreator.TYPE_STRING);    headers.add("Destination");
            types.add(ExcelCreator.TYPE_INTEGER);   headers.add("Pieces");
            types.add(ExcelCreator.TYPE_DOUBLE);    headers.add("Weight");
            types.add(ExcelCreator.TYPE_DOUBLE);    headers.add("Vol Weight");
            types.add(ExcelCreator.TYPE_DOUBLE);    headers.add("CFT");
            types.add(ExcelCreator.TYPE_STRING);    headers.add("ETA");
            // CARGO LA DATA
            DocumentsWS docWS = new DocumentsWS();
            ArrayList<beans.Documents> docsAL = docWS.getDocumentsByNoAndType(docsNo, docsType, username, authenticator);
            ArrayList<ArrayList> data = new ArrayList<>();
            for (int i = 0; i < docsAL.size(); i++) {
                beans.Documents document = docsAL.get(i);
                ArrayList row = new ArrayList();
                if (document != null) {
                    System.out.println("document no "+document.getDocumentNo());
                    row.add(document.getDocumentNo());
                    row.add(document.getRoutingId());
                    row.add(document.getOrigenCityCode());
                    row.add(document.getDestinationCityCode());
                    row.add(document.getTotalPieces());
                    row.add(document.getTotalWeight().doubleValue());
                    row.add(document.getTotalVolWeight().doubleValue());
                    row.add(document.getTotalCft().doubleValue());
                    row.add(utility.calculateETA(document.getDocumentDate(), document.getRoutingId()));
                    data.add(row);
                } else {
                    System.out.println("pero por que doc es null!!!");
                }
            }
            // CREO EL EXCEL
            String fileName = excelPath + result + "_shippmentsReport";
            ExcelCreator excel = new ExcelCreator(headers, data, types, fileName, "Shippments Report", top, footer);
            String nombre = excel.createExcel();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Web service operation
     * @param LdocsNo
     * @param LdocsType
     * @param username
     * @param authenticator
     * @return 
     */
    @WebMethod(operationName = "createHistoryReport")
    public String createHistoryReport(@WebParam(name = "LdocsNo") String LdocsNo,
            @WebParam(name = "LdocsType") String LdocsType,
            @WebParam(name = "username") String username,
            @WebParam(name = "authenticator") String authenticator) {
        String result = "";
        try {
            
            String[] docsNo =  LdocsNo.substring(0, LdocsNo.length()-1).split(",");
            String[] docsType =   LdocsType.substring(0,  LdocsType.length()-1).split(",");
            
            
            UtilityWS utility = new UtilityWS();
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yy");
            result = utility.dateToString(Calendar.getInstance());
            // SET DE LOS TOP HEADERS
            ArrayList<String> top = this.getRegularHeader();
            // SET DE LOS FOOTER
            ArrayList<String> footer = this.getRegularFooter();
            // SET LOS TYPES Y HEADERS
            ArrayList<Integer> types = new ArrayList<Integer>();
            ArrayList<String> headers = new ArrayList<String>();
            types.add(ExcelCreator.TYPE_STRING);    headers.add("Document No");
            types.add(ExcelCreator.TYPE_STRING);    headers.add("Routing");
            types.add(ExcelCreator.TYPE_STRING);    headers.add("Origen");
            types.add(ExcelCreator.TYPE_STRING);    headers.add("Destination");
            types.add(ExcelCreator.TYPE_INTEGER);   headers.add("Pieces");
            types.add(ExcelCreator.TYPE_DOUBLE);    headers.add("Weight");
            types.add(ExcelCreator.TYPE_DOUBLE);    headers.add("Vol Weight");
            types.add(ExcelCreator.TYPE_DOUBLE);    headers.add("CFT");
            types.add(ExcelCreator.TYPE_STRING);    headers.add("POD");
            // CARGO LA DATA
            DocumentsWS docWS = new DocumentsWS();
            ArrayList<beans.Documents> docsAL = docWS.getDocumentsByNoAndType(docsNo, docsType, username, authenticator);
            ArrayList<ArrayList> data = new ArrayList<ArrayList>();
            for (int i = 0; i < docsAL.size(); i++) {
                beans.Documents document = docsAL.get(i);
                ArrayList row = new ArrayList();
                if (document != null) {
                    System.out.println("document no "+document.getDocumentNo());
                    String podDate = "";
                    if (document.getPodDate() != null) {
                        podDate = dateFormatter.format(document.getPodDate());
                    }
                    row.add(document.getDocumentNo());
                    row.add(document.getRoutingId());
                    row.add(document.getOrigenCityCode());
                    row.add(document.getDestinationCityCode());
                    row.add(document.getTotalPieces());
                    row.add(document.getTotalWeight().doubleValue());
                    row.add(document.getTotalVolWeight().doubleValue());
                    row.add(document.getTotalCft().doubleValue());
                    row.add(podDate);
                    data.add(row);
                } else {
                    System.out.println("pero por que doc es null!!!");
                }
            }
            // CREO EL EXCEL
            String fileName = excelPath + result + "_historyReport";
            ExcelCreator excel = new ExcelCreator(headers, data, types, fileName, "History Report", top, footer);
            String nombre = excel.createExcel();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
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
                  
        String result = "";
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
            } else if (id_empresa == 12) {
                types.add(ExcelCreator.TYPE_CURRENCY_USD);    headers.add("Amount");
            }
            // CARGO LA DATA
            BMWS bmWS = new BMWS();
            
            //beans.TblCxcMovimientos[] tcmA;
            String temp = bmWS.getMovimientosAbiertosByEmpresa(id_empresa, username, authenticator);
            
            final Gson gson = new Gson();
            
            java.lang.reflect.Type listOfTestObject = new TypeToken<List<beans.TblCxcMovimientos>>(){}.getType();
            List<beans.TblCxcMovimientos> tcmA;
            tcmA = gson.fromJson(temp, listOfTestObject);
            
            
            ArrayList<ArrayList> data = new ArrayList<>();
            tcmA.stream().map((tcm) -> {
                ArrayList row = new ArrayList();
                row.add(tcm.getId().getSerie());
                row.add(tcm.getId().getNoDocumento());
                row.add(tcm.getDiasVencida());
                row.add(tcm.getSaldo());
                return row;
            }).forEach((row) -> {
                data.add(row);
            });
            // CREO EL EXCEL
            String fileName = excelPath + result + "_movimientosReport";
            ExcelCreator excel = new ExcelCreator(headers, data, types, fileName, "Balance Statement", top, footer);
            String nombre = excel.createExcel();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
