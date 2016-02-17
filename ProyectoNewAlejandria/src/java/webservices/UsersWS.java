/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package webservices;

import beans.WebUserMessages;
import beans.WebUserProfile;
import beans.WebUsers;
import beans.WebUserPermissions;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
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
public class UsersWS {

    /**
     * Web service operation
     * @param username
     * @param authenticator
     * @return 
     */
    @WebMethod(operationName = "validateUser")
    // Devuelve 1 si se valida, 0 si la validacion es incorrecta
    // realiza el md5(username + password) y compara que sea igual al authenticator
    // password lo toma de la bd de acuerdo al username
    public int validateUser(@WebParam(name = "username") String username,
            @WebParam(name = "authenticator") String authenticator) {
        int result = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        // Cargo los usuario para este username
        
        Query query = session.createQuery("from WebUsers where username = :username");
        query.setString("username", username);
        ArrayList<WebUsers> usersAL = new ArrayList<WebUsers>(query.list());
        //System.out.println("username "+username+" "+usersAL.size());
        if (usersAL.size() > 0) {
            String password = usersAL.get(0).getPassword();
            String word = username+password;
            // Obtengo el md5 del word
            String wordMd5 = "";
            byte[] defaultBytes = word.getBytes();
            try {
                MessageDigest algorithm = MessageDigest.getInstance("MD5");
                algorithm.reset();
                algorithm.update(defaultBytes);
                byte messageDigest[] = algorithm.digest();

                StringBuilder hexString = new StringBuilder();
                for (int i = 0; i < messageDigest.length; i++) {
                    String hexS = Integer.toHexString(0xFF & messageDigest[i]);
                    if (hexS.length() < 2) {
                        hexS = "0"+hexS;
                    }
                    hexString.append(hexS);
                    //System.out.print(hexS+" ");
                }
                //System.out.println("bien");
                String foo = messageDigest.toString();
                //System.out.println("word " + word + " md5 version is " + hexString.toString());
                wordMd5 = hexString.toString();
            } catch (NoSuchAlgorithmException nsae) {
                System.out.println("Error al obtenere el md5 del word");
            }
            if (authenticator.equalsIgnoreCase(wordMd5)) {
                result = 1;
            }
        }
        // Cierro la sesion
        session.getTransaction().commit();
        session.close();
        System.out.println("esto es lo que regreso "+result);
        return result;
    }

    /**
     * Web service operation
     * @param username
     * @param authenticator
     * @return 
     */
    @WebMethod(operationName = "getWebUser")
    public WebUsers getWebUser(@WebParam(name = "username") String username,
            @WebParam(name = "authenticator") String authenticator) {
        WebUsers result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        // Cargo los usuario para este username
        Query query = session.createQuery("from WebUsers where username = :username");
        query.setString("username", username);
        ArrayList<WebUsers> usersAL = new ArrayList<WebUsers>(query.list());
        //System.out.println("username "+username+" "+usersAL.size());
        if (usersAL.size() > 0) {
            String password = usersAL.get(0).getPassword();
            String word = username+password;
            // Obtengo el md5 del word
            String wordMd5 = "";
            byte[] defaultBytes = word.getBytes();
            try {
                MessageDigest algorithm = MessageDigest.getInstance("MD5");
                algorithm.reset();
                algorithm.update(defaultBytes);
                byte messageDigest[] = algorithm.digest();

                StringBuffer hexString = new StringBuffer();
                for (int i = 0; i < messageDigest.length; i++) {
                    String hexS = Integer.toHexString(0xFF & messageDigest[i]);
                    if (hexS.length() < 2) {
                        hexS = "0"+hexS;
                    }
                    hexString.append(hexS);
                    //System.out.print(hexS+" ");
                }
                //System.out.println("bien");
                String foo = messageDigest.toString();
                //System.out.println("word " + word + " md5 version is " + hexString.toString());
                wordMd5 = hexString.toString();
            } catch (NoSuchAlgorithmException nsae) {
                System.out.println("Error al obtenere el md5 del word");
            }
            if (authenticator.equalsIgnoreCase(wordMd5)) {
                result = usersAL.get(0);
            }
        }
        // Cierro la sesion
        session.getTransaction().commit();
        session.close();
        // Regreso
        return result;
    }

    /**
     * Web service operation
     * @param action
     * @param message
     * @param username
     * @param authenticator
     * @return 
     */
    @WebMethod(operationName = "addWebUserMessage")
    public int addWebUserMessage(@WebParam(name = "action") String action,
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
        // Obtengo el webUsers
        beans.WebUsers webUser = usersSrv.getWebUser(username, authenticator);
        // abro la sesion
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        // Creo el message y Guardo
        WebUserMessages webUserMsg = new WebUserMessages();
        webUserMsg.setAction(action);
        webUserMsg.setMessage(message);
        webUserMsg.setCustomerId(webUser.getCustomerId());
        webUserMsg.setInitDate(new Date());
        //System.out.println("pero por que dice que el id es algo!!! "+webUserMsg.getId());
        session.saveOrUpdate(webUserMsg);
        // Cierro la sesion
        session.getTransaction().commit();
        session.close();
        // Regreso
        return 1;
    }

    /**
     * Web service operation
     * @param beginningDate
     * @param endDate
     * @param username
     * @param authenticator
     * @return 
     */
    @WebMethod(operationName = "getWebUserMessages")
    public String getWebUserMessages(@WebParam(name = "beginnigDate") Date beginningDate,
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
        // abro la sesion
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        // Cargo los usuario para este username
        String str = "from WebUserMessages where initDate >= :beginningDate and initDate <= :endDate and customerId = :customerId and action <> 'LOGIN'";
        Query query = session.createQuery(str);
        UtilityWS utility = new UtilityWS();
        query.setString("beginningDate", utility.completeDateToLower(beginningDate));
        query.setString("endDate", utility.completeDateToUpper(endDate));
        query.setInteger("customerId", webUser.getCustomerId());
        ArrayList<WebUserMessages> webUserMsgAL = new ArrayList<WebUserMessages>(query.list());
        // Construyo el WebUserMessages A
        WebUserMessages[] webUserMsgA = new WebUserMessages[webUserMsgAL.size()];
        for (int i = 0; i < webUserMsgAL.size(); i++) {
            webUserMsgA[i] = webUserMsgAL.get(i);
        }
        // Cierro la sesion
        session.getTransaction().commit();
        session.close();
        // Retorno
        final Gson gson = new Gson();
        final String webUserMsgAJSON = gson.toJson(webUserMsgA);
        
        return webUserMsgAJSON;
    }

    private WebUserProfile getWebUserProfileByCustomerId(int customerId) {
        // abro la sesion
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        // Cargo los usuario para este username
        String str = "from WebUserProfile where customerId = :customerId";
        Query query = session.createQuery(str);
        query.setInteger("customerId", customerId);
        ArrayList<WebUserProfile> wupAL = new ArrayList<WebUserProfile>(query.list());
        // Cierro la sesion
        session.getTransaction().commit();
        session.close();
        if (wupAL.size() > 0) {
            return wupAL.get(0);
        } else {
            return null;
        }
    }

    private ArrayList<WebUserPermissions> getWebUserPermissionsByWebUserId(int webUserId) {
        // abro la sesion
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        // Cargo los usuario para este username
        String str = "from WebUserPermissions where webUserId = :webUserId";
        Query query = session.createQuery(str);
        query.setInteger("webUserId", webUserId);
        ArrayList<WebUserPermissions> wupAL = new ArrayList<WebUserPermissions>(query.list());
        // Cierro la sesion
        session.getTransaction().commit();
        session.close();
        return wupAL;
    }

    /**
     * Web service operation
     * @param username
     * @param authenticator
     * @return 
     */
    @WebMethod(operationName = "getWebUserProfile")
    public WebUserProfile getWebUserProfile(@WebParam(name = "username") String username,
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
        // Obtengo el webUserProfile y este lo regreso si existe
        WebUserProfile wup = getWebUserProfileByCustomerId(webUser.getCustomerId());
        if (wup != null) {
            return wup;
        }
        // si no existe entonces creo el webUserProfile
        // abro la sesion
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        // construyo el webUserProfile
        wup = new WebUserProfile();
        wup.setLanguage("es");
        wup.setDefaultView("cargo");
        wup.setCustomerId(webUser.getCustomerId());
        session.saveOrUpdate(wup);
        // cierro la sesion
        session.getTransaction().commit();
        session.close();
        // regreso el webUserProfile
        return wup;
    }

    /**
     * Web service operation
     * @param username
     * @param authenticator
     * @return 
     */
    @WebMethod(operationName = "getWebUserPermissions")
    public ArrayList<WebUserPermissions> getWebUserPermissions(@WebParam(name = "username") String username,
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
        // Obtengo los webUserPermissions, si no existe asumo que tiene todos y los creo
        //   si existen entonces solo los devuelvo
        ArrayList<WebUserPermissions> wupAL = getWebUserPermissionsByWebUserId(webUser.getId());
        //System.out.println("cual es el largo pues "+wupAL.size());
        if (wupAL.size() > 0) {
            return wupAL;
        } else {
            // Aqui debo de llenar el AL con todos los permisos
            wupAL = new ArrayList<WebUserPermissions>();
            for (int i = 1; i <= 6; i++) {
                WebUserPermissions wup = new WebUserPermissions();
                wup.setWebUserId(webUser.getId());
                wup.setPermissionId(i);
                wupAL.add(wup);
            }
            return wupAL;
        }
    }

    /**
     * Web service operation
     * @param language
     * @param defaultView
     * @param username
     * @param authenticator
     * @return 
     */
    @WebMethod(operationName = "setWebUserProfile")
    public int setWebUserProfile(@WebParam(name = "language") String language,
            @WebParam(name = "defaultView") String defaultView,
            @WebParam(name = "username") String username,
            @WebParam(name = "authenticator") String authenticator) {
        System.out.println("si entro al la func ");
        System.out.println("si entro al la func1 "+language);
        // Primero se debe validar el usuario
        UsersWS usersSrv = new UsersWS();
        int validation = usersSrv.validateUser(username, authenticator);
        if (validation == 0) {
            System.out.println("Usuario Invalido");
            return 0;
        }
        System.out.println("si entro al la func antes de jalar el web user");
        // Obtengo el webUsers
        beans.WebUsers webUser = usersSrv.getWebUser(username, authenticator);
        // Obtengo el webUserProfile por customerId
        WebUserProfile wup = getWebUserProfileByCustomerId(webUser.getCustomerId());
        System.out.println("si entro al la func abntes de crear la session");
        // abro la sesion
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        // Le hago los cambios necesarios
        System.out.println("este es el profile a setear "+language+" "+defaultView);
        wup.setLanguage(language);
        wup.setDefaultView(defaultView);
        session.saveOrUpdate(wup);
        // cierro la sesion
        session.getTransaction().commit();
        session.close();
        // Regreso
        return 1;
    }

    /**
     * Web service operation
     * @param username
     * @param authenticator
     * @return 
     */
    // Obtengo el authenticator del cliente
    // verifico que el authenticator recibido (que es en tendria que ser el md5(password)) sea el md5(password) del username
    //   Si es cierto devuelvo md5(authanticator+password) si no devuelvo el authenticator orignal
    @WebMethod(operationName = "preValidateUser")
    public String preValidateUser(@WebParam(name = "username") String username,
            @WebParam(name = "authenticator") String authenticator) {
        String result = authenticator;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        // Cargo los usuario para este username

        Query query = session.createQuery("from WebUsers where username = :username");
        query.setString("username", username);
        ArrayList<WebUsers> usersAL = new ArrayList<WebUsers>(query.list());
        //System.out.println("username "+username+" "+usersAL.size());
        if (usersAL.size() > 0) {
            String password = usersAL.get(0).getPassword();
            String word = password;
            // Obtengo el md5 del word
            String wordMd5 = "";
            byte[] defaultBytes = word.getBytes();
            try {
                MessageDigest algorithm = MessageDigest.getInstance("MD5");
                algorithm.reset();
                algorithm.update(defaultBytes);
                byte messageDigest[] = algorithm.digest();

                StringBuffer hexString = new StringBuffer();
                for (int i = 0; i < messageDigest.length; i++) {
                    String hexS = Integer.toHexString(0xFF & messageDigest[i]);
                    if (hexS.length() < 2) {
                        hexS = "0"+hexS;
                    }
                    hexString.append(hexS);
                    //System.out.print(hexS+" ");
                }
                wordMd5 = hexString.toString();
                //System.out.println("bien");
                //String foo = messageDigest.toString();
                //System.out.println("word " + word + " md5 version is " + hexString.toString());
            } catch (NoSuchAlgorithmException nsae) {
                System.out.println("Error al obtenere el md5 del word");
            }
            if (authenticator.equalsIgnoreCase(wordMd5)) {
                word = username+password;
                // Obtengo el md5 del word
                wordMd5 = "";
                defaultBytes = word.getBytes();
                try {
                    MessageDigest algorithm = MessageDigest.getInstance("MD5");
                    algorithm.reset();
                    algorithm.update(defaultBytes);
                    byte messageDigest[] = algorithm.digest();

                    StringBuffer hexString = new StringBuffer();
                    for (int i = 0; i < messageDigest.length; i++) {
                        String hexS = Integer.toHexString(0xFF & messageDigest[i]);
                        if (hexS.length() < 2) {
                            hexS = "0" + hexS;
                        }
                        hexString.append(hexS);
                    }
                    wordMd5 = hexString.toString();
                } catch (NoSuchAlgorithmException nsae) {
                    System.out.println("Error al obtenere el md5 del word");
                }
                result = wordMd5;
            }
        }
        // Cierro la sesion
        session.getTransaction().commit();
        session.close();
        // Retorno
        return result;
    }

    /**
     * Web service operation
     * @param newPassword
     * @param username
     * @param authenticator
     * @return 
     */
    @WebMethod(operationName = "changeWebUserPassword")
    public int changeWebUserPassword(@WebParam(name = "newPassword") String newPassword,
            @WebParam(name = "username") String username,
            @WebParam(name = "authenticator") String authenticator) {

        // Primero se debe validar el usuario
        UsersWS usersSrv = new UsersWS();
        int validation = usersSrv.validateUser(username, authenticator);
        if (validation == 0) {
            System.out.println("Usuario Invalido");
            return 0;
        }
        System.out.println("si entro al la func antes de jalar el web user");
        // Obtengo el webUsers
        beans.WebUsers webUser = usersSrv.getWebUser(username, authenticator);
        webUser.setPassword(newPassword);
        // Abro la sesion
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        // Hago el cambio
        session.saveOrUpdate(webUser);
        // Cierro la sesion
        session.getTransaction().commit();
        session.close();
        // Retorno
        return 1;
    }

    
}
