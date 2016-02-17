package beans;
// Generated Jul 16, 2009 12:30:56 PM by Hibernate Tools 3.2.1.GA

import java.util.ArrayList;




/**
 * CustomerDestinations generated by hbm2java
 */
public class CustomerDestinations  implements java.io.Serializable {

     private int id;
     private int customerId;
     private int cityId;
     private String tel;
     private String cel;
     private String fax;
     private String email;
     private String address;
     private String zip;
     private String billingName;
     private String billingAddress;
     private String billingNo;
     private int salesPersonId;
     private Integer customerAgentId;
     private Boolean notifyByMail;

     private ArrayList<CustomerServices> customerServices = new ArrayList<CustomerServices>();
     private ArrayList<CustomerContacts> customerContacts = new ArrayList<CustomerContacts>();

    public CustomerDestinations() {
    }

	
    public CustomerDestinations(int id, int customerId, int cityId, String address, String billingName, String billingAddress, String billingNo, int salesPersonId, String rowguid) {
        this.id = id;
        this.customerId = customerId;
        this.cityId = cityId;
        this.address = address;
        this.billingName = billingName;
        this.billingAddress = billingAddress;
        this.billingNo = billingNo;
        this.salesPersonId = salesPersonId;
    }
    public CustomerDestinations(int id, int customerId, int cityId, String tel, String cel, String fax, String email, String address, String zip, String billingName, String billingAddress, String billingNo, int salesPersonId, Integer customerAgentId, Boolean notifyByMail, String rowguid) {
       this.id = id;
       this.customerId = customerId;
       this.cityId = cityId;
       this.tel = tel;
       this.cel = cel;
       this.fax = fax;
       this.email = email;
       this.address = address;
       this.zip = zip;
       this.billingName = billingName;
       this.billingAddress = billingAddress;
       this.billingNo = billingNo;
       this.salesPersonId = salesPersonId;
       this.customerAgentId = customerAgentId;
       this.notifyByMail = notifyByMail;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public int getCustomerId() {
        return this.customerId;
    }
    
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public int getCityId() {
        return this.cityId;
    }
    
    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
    public String getTel() {
        return this.tel;
    }
    
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getCel() {
        return this.cel;
    }
    
    public void setCel(String cel) {
        this.cel = cel;
    }
    public String getFax() {
        return this.fax;
    }
    
    public void setFax(String fax) {
        this.fax = fax;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    public String getZip() {
        return this.zip;
    }
    
    public void setZip(String zip) {
        this.zip = zip;
    }
    public String getBillingName() {
        return this.billingName;
    }
    
    public void setBillingName(String billingName) {
        this.billingName = billingName;
    }
    public String getBillingAddress() {
        return this.billingAddress;
    }
    
    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }
    public String getBillingNo() {
        return this.billingNo;
    }
    
    public void setBillingNo(String billingNo) {
        this.billingNo = billingNo;
    }
    public int getSalesPersonId() {
        return this.salesPersonId;
    }
    
    public void setSalesPersonId(int salesPersonId) {
        this.salesPersonId = salesPersonId;
    }
    public Integer getCustomerAgentId() {
        return this.customerAgentId;
    }
    
    public void setCustomerAgentId(Integer customerAgentId) {
        this.customerAgentId = customerAgentId;
    }
    public Boolean getNotifyByMail() {
        return this.notifyByMail;
    }
    
    public void setNotifyByMail(Boolean notifyByMail) {
        this.notifyByMail = notifyByMail;
    }

    public ArrayList<CustomerServices> getCustomerServices() {
        return customerServices;
    }

    public void setCustomerServices(ArrayList<CustomerServices> customerServices) {
        this.customerServices = customerServices;
    }

    public ArrayList<CustomerContacts> getCustomerContacts() {
        return customerContacts;
    }

    public void setCustomerContacts(ArrayList<CustomerContacts> customerContacts) {
        this.customerContacts = customerContacts;
    }
}


