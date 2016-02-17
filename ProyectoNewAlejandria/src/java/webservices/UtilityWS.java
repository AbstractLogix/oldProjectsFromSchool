/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package webservices;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.util.List;
import javax.jws.WebService;
import org.hibernate.EmptyInterceptor;
import org.hibernate.Interceptor;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;
import com.google.gson.*;
import java.io.File;
import java.io.IOException;
import static sun.net.www.protocol.http.AuthCacheValue.Type.Server;

/**
 *
 * @author Otto
 */
@WebService()
public class UtilityWS {
    
    public String absolutePath;
    public String excelPath;

    public String dateToString(Calendar c) {
        String date = "";
        date = "" + c.get(Calendar.YEAR) + (c.get(Calendar.MONTH) + 1) + c.get(Calendar.DAY_OF_MONTH) + c.get(Calendar.HOUR_OF_DAY) + c.get(Calendar.MINUTE) + c.get(Calendar.SECOND);
        return date;
    }

    public String completeDateToUpper(java.util.Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        String s = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DAY_OF_MONTH) + " 23:59:59";
        return s;
    }

    public String completeDateToLower(java.util.Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        String s = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DAY_OF_MONTH) + " 00:00:00";
        return s;
    }

    public String getEncryptedWord(String wordToEncrypt) {
        String word = "Icaruus_CPSL";


        char[] wordByte = word.toCharArray();
        char[] wordToEncryptByte = wordToEncrypt.toCharArray();

        int length = wordToEncryptByte.length;
        char[] wordToEncryptByteChanged = new char[length];

        for (int i = 0; i < wordToEncryptByte.length; i++) {
            wordToEncryptByteChanged[i] = wordToEncryptByte[length - i - 1];
        }

        if (wordToEncryptByteChanged.length <= wordByte.length) {
            length = wordToEncryptByteChanged.length;
        } else {
            length = wordByte.length;
        }

        for (int i = 0; i < length; i++) {
            int a = (int) wordByte[i] + (int) wordToEncryptByteChanged[i];
            wordByte[i] = (char) a;
        }
        String newString = "";

        for (int i = 0; i < wordByte.length; i++) {
            int ch = 0;
            if (i == 0 || i == 2) {
                ch = -1 * wordByte[i];
            } else {
                ch = wordByte[i];
            }
            newString += Integer.toHexString(ch);
        }
        return newString;
    }

    // Calula la fecha estimada de arrivo de acuerdo a la fecha de routing, y el routing en si
    public String calculateETA(Date date, String routingId) {
        String result = "";        
        Calendar etaC = Calendar.getInstance();
        etaC.setTime(date);
        // Si es AIR
        if (routingId.equalsIgnoreCase("AIR")) {
            if (etaC.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                etaC.add(Calendar.DAY_OF_MONTH, 3);
            } else if (etaC.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY
                        || etaC.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY
                        || etaC.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
                etaC.add(Calendar.DAY_OF_MONTH, 2);
            } else if (etaC.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY
                        || etaC.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY
                        || etaC.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                etaC.add(Calendar.DAY_OF_MONTH, 4);
            }
        } else if (routingId.equalsIgnoreCase("COC")) {
            if (etaC.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                etaC.add(Calendar.DAY_OF_MONTH, 2);
            } else if (etaC.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY
                        || etaC.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY
                        || etaC.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY
                        || etaC.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
                etaC.add(Calendar.DAY_OF_MONTH, 1);
            } else if (etaC.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY
                        || etaC.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                etaC.add(Calendar.DAY_OF_MONTH, 3);
            }
        } else if (routingId.equalsIgnoreCase("LCL")) {
            etaC.add(Calendar.DAY_OF_MONTH, 12);
        }
        // Formateo la fecha
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yy");
        if (routingId.equalsIgnoreCase("FCL") || routingId.equalsIgnoreCase("NOR")) {
            result = "";
        } else {
            result = dateFormatter.format(etaC.getTime());
        }
        // Regreso
        return result;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getMessages")
    public java.lang.String[] getMessages(@WebParam(name = "langAbbr") String langAbbr) {
        // Abro la sesion
        Interceptor interceptor = EmptyInterceptor.INSTANCE;
        interceptor.onPrepareStatement("use Alejandria");
        Session session = HibernateUtil.getSessionFactory().openSession(interceptor);
        //Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List resultL = session.createQuery("select messages."+langAbbr+" from Messages messages").list();
        String[] arrayL = new String[resultL.size()];

        for(int i = 0 ; i < resultL.size(); i++){
           arrayL[i] = (String) resultL.get(i);
        }
        // Cierro la sesion
        session.getTransaction().commit();
        session.close();

        return arrayL;
    }

    /**
     * Web service operation
     * @param shippersId
     * @return 
     */
    @WebMethod(operationName = "getShippersById")
    public String getShippersById(@WebParam(name = "shippersId") String shippersId) {
        // Abro la sesion
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        //Convertimos de Json a int[]
        String[] shippers = shippersId.split(",");
        
        // Cargo los Shippers
        String str = "FROM Shippers s where (";
        for (int i = 0; i < shippers.length; i++) {
            str += "s.id = "+shippers[i]+" OR ";
        }
        str += "1=0)";
        System.out.println("query "+str);
        Query query = session.createQuery(str);
        ArrayList<beans.Shippers> resultAL = new ArrayList<beans.Shippers>(query.list());
        beans.Shippers[] result = new beans.Shippers[resultAL.size()];
        for (int i = 0; i < resultAL.size(); i++) {
            result[i] = resultAL.get(i);
        }
        System.out.println("este es el bloody largo "+result.length);
        
        // Retorno
        final Gson gson = new Gson();
        final String resultJSON = gson.toJson(result);
        
        return resultJSON;
    }
  
}
