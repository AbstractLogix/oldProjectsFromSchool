/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package webservices;

import beans.CustomerChargesByCft;
import beans.CustomerChargesByLbs;
import beans.CustomerContacts;
import beans.CustomerDestinations;
import beans.CustomerServices;
import beans.Customers;
import beans.LocationServices;
import beans.Users;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;
import utils.HibernateProxyTypeAdapter;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import com.google.gson.*;


/**
 *
 * @author Otto
 */
@WebService()
public class CustomersWS {

    /**
     * Web service operation
     * @param username
     * @param authenticator
     * @return 
     */
    @WebMethod(operationName = "getCustomer")
    public String getCustomer(@WebParam(name = "username") String username,
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
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        // Obtengo el customer
        Customers customer = (Customers)session.load(Customers.class, webUser.getCustomerId());
        // Cargo los customerDestination y los customerContacts
        String strSql = "select cd, cs, ls, ccl, ccc from CustomerDestinations cd, CustomerServices cs, ";
        strSql += "LocationServices ls, CustomerChargesByLbs ccl, CustomerChargesByCft ccc where ccl.id = cs.id and ";
        strSql += "ccc.id = cs.id and cs.locationServiceId = ls.id and cs.customerDestinationId = cd.id and ";
        strSql += "cd.customerId = :customerId order by cd.id, cs.id";
        Query query = session.createQuery(strSql);
        query.setInteger("customerId", customer.getId());
        List cdL = query.list();
        // Cierro la sesion
        //session.getTransaction().commit();
        //session.close();
        // Recorro los customerDestinations;
        CustomerDestinations cd = null;
        ArrayList<CustomerDestinations> cdAL = new ArrayList<CustomerDestinations>();
        ArrayList<CustomerServices> csAL = new ArrayList<CustomerServices>();
        for (int i = 0; i < cdL.size(); i++) {
            Object[] inputObj = (Object[]) cdL.get(i);
            CustomerDestinations cdAux = (CustomerDestinations)inputObj[0];
            CustomerServices csAux = (CustomerServices)inputObj[1];
            LocationServices lsAux = (LocationServices)inputObj[2];
            CustomerChargesByLbs ccl = (CustomerChargesByLbs)inputObj[3];
            CustomerChargesByCft ccc = (CustomerChargesByCft)inputObj[4];
            // Cargo el cd si no hay uno cargado
            if (cd == null) {
                cd = cdAux;
            }
            // Cargo el customerDestination si hay cambio
            if (cd.getId() != cdAux.getId()) {
                cd.setCustomerServices(csAL);
                // Cargo los customerContacts en el customerDestination
                cd.setCustomerContacts(this.getCustomerContacts(cd.getId()));
                // Cargo el customerDestination
                cdAL.add(cd);
                cd = cdAux;
                csAL = new ArrayList<CustomerServices>();
            }
            // Cargo el cs, ccl, ccc
            csAux.setLocationService(lsAux);
            csAux.setCustomerChargesByLbs(ccl);
            csAux.setCustomerChargesByCft(ccc);
            csAL.add(csAux);
        }
        // Cargo la ultima informacion
        cd.setCustomerServices(csAL);
        // Cargo los customerContacts en el customerDestination
        cd.setCustomerContacts(this.getCustomerContacts(cd.getId()));
        cdAL.add(cd);
        // Abro la sesion
        //session = HibernateUtil.getSessionFactory().openSession();
        //session.beginTransaction();
        // Cargo los cds en el customer
        customer.setCustomerDestinations(cdAL);
        // Cierro la sesion
        session.getTransaction().commit();
        session.close();
        // Regreso
        System.out.println("que pasa... esto es antes de regresar");
        
        // Regreso
        GsonBuilder b = new GsonBuilder();
        b.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
        Gson gson = b.create();
        final String customerJSON = gson.toJson(customer);
        
        return customerJSON;
    }

    private ArrayList<CustomerContacts> getCustomerContacts(int customerDestinationId) {
        // Abro la sesion
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        // Cargo los customerContacts
        String str = "FROM CustomerContacts where customerDestinationId = "+customerDestinationId;
        Query query = session.createQuery(str);
        ArrayList<CustomerContacts> result = new ArrayList<CustomerContacts>(query.list());
        System.out.println("cc length "+result.size());
        // Cierro la sesion
        session.getTransaction().commit();
        session.close();
        // Regreso
        return result;
    }

    /**
     * Web service operation
     * @param userId
     * @param username
     * @param authenticator
     * @return 
     */
    @WebMethod(operationName = "getUser")
    public Users getUser(@WebParam(name = "userId") int userId,
            @WebParam(name = "username") String username,
            @WebParam(name = "authenticator") String authenticator) {
        System.out.println("al menos si llego");
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
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        // Cargo los customerContacts
        String str = "SELECT u FROM Users u, Customers c, CustomerDestinations cd where cd.customerId = c.id and ";
        str += "(cd.salesPersonId = u.id or cd.customerAgentId = u.id) and u.id = :userId and c.id = :customerId";
        Query query = session.createQuery(str);
        query.setInteger("userId", userId);
        query.setInteger("customerId", webUser.getCustomerId());
        ArrayList<Users> usersAL = new ArrayList<Users>(query.list());
        // Cargo el resultado
        Users user = null;
        if (usersAL.size() > 0) {
            user = usersAL.get(0);
        }
        // Cierro la sesion
        session.getTransaction().commit();
        session.close();
        // Retorno
        System.out.println("antes de regresas");
        return user;
    }

    /**
     * Web service operation
     * @param id
     * @param address
     * @param zip
     * @param telephone
     * @param cellphone
     * @param fax
     * @param email
     * @param billingName
     * @param billingAddress
     * @param billingNo
     * @param notifyByMail
     * @param username
     * @param authenticator
     * @return 
     */
    @WebMethod(operationName = "setCustomerDestination")
    public int setCustomerDestination(@WebParam(name = "id") int id,
            @WebParam(name = "address") String address,
            @WebParam(name = "zip") String zip,
            @WebParam(name = "telephone") String telephone,
            @WebParam(name = "cellphone") String cellphone,
            @WebParam(name = "fax") String fax,
            @WebParam(name = "email") String email,
            @WebParam(name = "billingName") String billingName,
            @WebParam(name = "billingAddress") String billingAddress,
            @WebParam(name = "billingNo") String billingNo,
            @WebParam(name = "notifyByMail") Boolean notifyByMail,
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
        // Abro la sesion
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        // Reviso que el customerId del usuario logeado corresponda al del customerDestination
        CustomerDestinations customerDestination = (CustomerDestinations)session.load(CustomerDestinations.class, id);
        if (customerDestination.getCustomerId() != webUser.getCustomerId()) {
            return 0;
        }
        // Cargo el CustomerDestination y guardo
        //CustomerDestinations customerDestination = new CustomerDestinations();
        //customerDestination.setId(id);
        customerDestination.setAddress(address);
        customerDestination.setZip(zip);
        customerDestination.setTel(telephone);
        customerDestination.setCel(cellphone);
        customerDestination.setFax(fax);
        customerDestination.setEmail(email);
        customerDestination.setBillingName(billingName);
        customerDestination.setBillingAddress(billingAddress);
        customerDestination.setBillingNo(billingNo);
        customerDestination.setNotifyByMail(notifyByMail);
        session.saveOrUpdate(customerDestination);
        // Cierro la sesion
        session.getTransaction().commit();
        session.close();
        // Retorno
        return 1;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "setCustomerContact")
    public int setCustomerContact(@WebParam(name = "id") int id,
            @WebParam(name = "customerDestinationId") int customerDestinationId,
            @WebParam(name = "prefixId") int prefixId,
            @WebParam(name = "name") String name,
            @WebParam(name = "lastname") String lastname,
            @WebParam(name = "jobTitle") String jobTitle,
            @WebParam(name = "address") String address,
            @WebParam(name = "tel") String tel,
            @WebParam(name = "cel") String cel,
            @WebParam(name = "fax") String fax,
            @WebParam(name = "email") String email,
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
        // Abro la sesion
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        // Reviso que el customerId del usuario logeado corresponda al del customerContact
        CustomerDestinations customerDestination =
                (CustomerDestinations)session.load(CustomerDestinations.class, customerDestinationId);
        if (customerDestination.getCustomerId() != webUser.getCustomerId()) {
            return 0;
        }
        // Cargo el CustomerContact y guardo
        CustomerContacts customerContact = new CustomerContacts();
        if (id == 0) {
            customerContact.setId(id);
            customerContact.setContactClasificationId(9);
            customerContact.setAvailableReception(false);
            customerContact.setInfoContact(false);
            customerContact.setAccountingContact(false);
        } else {
            customerContact = (CustomerContacts)session.load(CustomerContacts.class, id);
        }
        customerContact.setCustomerDestinationId(customerDestinationId);
        customerContact.setPrefixId(prefixId);
        customerContact.setJobTitle(jobTitle);
        customerContact.setName(name);
        customerContact.setLastname(lastname);
        customerContact.setTel(tel);
        customerContact.setCel(cel);
        customerContact.setFax(fax);
        customerContact.setEmail(email);
        customerContact.setAddress(address);
        // Imprimo todos los parametros
        //System.out.println(customerContact.getId()+" "+customerContact.getCustomerDestinationId()+" "+customerContact.getPrefixId()+" "+customerContact.getJobTitle()+" "+customerContact.getName()+" "+customerContact.getLastname());
        //System.out.println(customerContact.getTel()+" "+customerContact.getCel()+" "+customerContact.getFax()+" "+customerContact.getEmail()+" "+customerContact.getAddress()+" "+customerContact.getContactClasificationId());
        //System.out.println(customerContact.isAvailableReception()+" "+customerContact.getInfoContact()+" "+customerContact.getAccountingContact());
        session.saveOrUpdate(customerContact);
        // Cierro la sesion
        session.getTransaction().commit();
        session.close();
        // Retorno
        System.out.println("este es lo que regreso "+customerContact.getId());
        return customerContact.getId();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "eliminateCustomerContact")
    public int eliminateCustomerContact(@WebParam(name = "id") int id,
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
        // Abro la sesion
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        // Reviso que el customerId del usuario logeado corresponda al del customerContact
        CustomerContacts customerContact = (CustomerContacts)session.load(CustomerContacts.class, id);
        CustomerDestinations customerDestination =
                (CustomerDestinations)session.load(CustomerDestinations.class, customerContact.getCustomerDestinationId());
        if (customerDestination.getCustomerId() != webUser.getCustomerId()) {
            return 0;
        }
        // Ahora elimino el customerContact
        session.delete(customerContact);
        // Cierro la sesion
        session.getTransaction().commit();
        session.close();
        // Retorno
        return 1;
    }
}
