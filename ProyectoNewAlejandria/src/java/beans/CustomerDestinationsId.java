package beans;
// Generated 16/05/2010 09:58:09 PM by Hibernate Tools 3.2.1.GA


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CustomerDestinationsId generated by hbm2java
 */
@Embeddable
public class CustomerDestinationsId  implements java.io.Serializable {


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
     private String rowguid;

    public CustomerDestinationsId() {
    }

	
    public CustomerDestinationsId(int id, int customerId, int cityId, String address, String billingName, String billingAddress, String billingNo, int salesPersonId, String rowguid) {
        this.id = id;
        this.customerId = customerId;
        this.cityId = cityId;
        this.address = address;
        this.billingName = billingName;
        this.billingAddress = billingAddress;
        this.billingNo = billingNo;
        this.salesPersonId = salesPersonId;
        this.rowguid = rowguid;
    }
    public CustomerDestinationsId(int id, int customerId, int cityId, String tel, String cel, String fax, String email, String address, String zip, String billingName, String billingAddress, String billingNo, int salesPersonId, Integer customerAgentId, Boolean notifyByMail, String rowguid) {
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
       this.rowguid = rowguid;
    }
   

    @Column(name="id", nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    @Column(name="customerId", nullable=false)
    public int getCustomerId() {
        return this.customerId;
    }
    
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Column(name="cityId", nullable=false)
    public int getCityId() {
        return this.cityId;
    }
    
    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    @Column(name="tel", length=20)
    public String getTel() {
        return this.tel;
    }
    
    public void setTel(String tel) {
        this.tel = tel;
    }

    @Column(name="cel", length=20)
    public String getCel() {
        return this.cel;
    }
    
    public void setCel(String cel) {
        this.cel = cel;
    }

    @Column(name="fax", length=20)
    public String getFax() {
        return this.fax;
    }
    
    public void setFax(String fax) {
        this.fax = fax;
    }

    @Column(name="email", length=100)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name="address", nullable=false, length=150)
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name="zip", length=10)
    public String getZip() {
        return this.zip;
    }
    
    public void setZip(String zip) {
        this.zip = zip;
    }

    @Column(name="billingName", nullable=false, length=200)
    public String getBillingName() {
        return this.billingName;
    }
    
    public void setBillingName(String billingName) {
        this.billingName = billingName;
    }

    @Column(name="billingAddress", nullable=false, length=200)
    public String getBillingAddress() {
        return this.billingAddress;
    }
    
    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    @Column(name="billingNo", nullable=false, length=20)
    public String getBillingNo() {
        return this.billingNo;
    }
    
    public void setBillingNo(String billingNo) {
        this.billingNo = billingNo;
    }

    @Column(name="salesPersonId", nullable=false)
    public int getSalesPersonId() {
        return this.salesPersonId;
    }
    
    public void setSalesPersonId(int salesPersonId) {
        this.salesPersonId = salesPersonId;
    }

    @Column(name="customerAgentId")
    public Integer getCustomerAgentId() {
        return this.customerAgentId;
    }
    
    public void setCustomerAgentId(Integer customerAgentId) {
        this.customerAgentId = customerAgentId;
    }

    @Column(name="notifyByMail")
    public Boolean getNotifyByMail() {
        return this.notifyByMail;
    }
    
    public void setNotifyByMail(Boolean notifyByMail) {
        this.notifyByMail = notifyByMail;
    }

    @Column(name="rowguid", unique=true, nullable=false, length=36)
    public String getRowguid() {
        return this.rowguid;
    }
    
    public void setRowguid(String rowguid) {
        this.rowguid = rowguid;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof CustomerDestinationsId) ) return false;
		 CustomerDestinationsId castOther = ( CustomerDestinationsId ) other; 
         
		 return (this.getId()==castOther.getId())
 && (this.getCustomerId()==castOther.getCustomerId())
 && (this.getCityId()==castOther.getCityId())
 && ( (this.getTel()==castOther.getTel()) || ( this.getTel()!=null && castOther.getTel()!=null && this.getTel().equals(castOther.getTel()) ) )
 && ( (this.getCel()==castOther.getCel()) || ( this.getCel()!=null && castOther.getCel()!=null && this.getCel().equals(castOther.getCel()) ) )
 && ( (this.getFax()==castOther.getFax()) || ( this.getFax()!=null && castOther.getFax()!=null && this.getFax().equals(castOther.getFax()) ) )
 && ( (this.getEmail()==castOther.getEmail()) || ( this.getEmail()!=null && castOther.getEmail()!=null && this.getEmail().equals(castOther.getEmail()) ) )
 && ( (this.getAddress()==castOther.getAddress()) || ( this.getAddress()!=null && castOther.getAddress()!=null && this.getAddress().equals(castOther.getAddress()) ) )
 && ( (this.getZip()==castOther.getZip()) || ( this.getZip()!=null && castOther.getZip()!=null && this.getZip().equals(castOther.getZip()) ) )
 && ( (this.getBillingName()==castOther.getBillingName()) || ( this.getBillingName()!=null && castOther.getBillingName()!=null && this.getBillingName().equals(castOther.getBillingName()) ) )
 && ( (this.getBillingAddress()==castOther.getBillingAddress()) || ( this.getBillingAddress()!=null && castOther.getBillingAddress()!=null && this.getBillingAddress().equals(castOther.getBillingAddress()) ) )
 && ( (this.getBillingNo()==castOther.getBillingNo()) || ( this.getBillingNo()!=null && castOther.getBillingNo()!=null && this.getBillingNo().equals(castOther.getBillingNo()) ) )
 && (this.getSalesPersonId()==castOther.getSalesPersonId())
 && ( (this.getCustomerAgentId()==castOther.getCustomerAgentId()) || ( this.getCustomerAgentId()!=null && castOther.getCustomerAgentId()!=null && this.getCustomerAgentId().equals(castOther.getCustomerAgentId()) ) )
 && ( (this.getNotifyByMail()==castOther.getNotifyByMail()) || ( this.getNotifyByMail()!=null && castOther.getNotifyByMail()!=null && this.getNotifyByMail().equals(castOther.getNotifyByMail()) ) )
 && ( (this.getRowguid()==castOther.getRowguid()) || ( this.getRowguid()!=null && castOther.getRowguid()!=null && this.getRowguid().equals(castOther.getRowguid()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getId();
         result = 37 * result + this.getCustomerId();
         result = 37 * result + this.getCityId();
         result = 37 * result + ( getTel() == null ? 0 : this.getTel().hashCode() );
         result = 37 * result + ( getCel() == null ? 0 : this.getCel().hashCode() );
         result = 37 * result + ( getFax() == null ? 0 : this.getFax().hashCode() );
         result = 37 * result + ( getEmail() == null ? 0 : this.getEmail().hashCode() );
         result = 37 * result + ( getAddress() == null ? 0 : this.getAddress().hashCode() );
         result = 37 * result + ( getZip() == null ? 0 : this.getZip().hashCode() );
         result = 37 * result + ( getBillingName() == null ? 0 : this.getBillingName().hashCode() );
         result = 37 * result + ( getBillingAddress() == null ? 0 : this.getBillingAddress().hashCode() );
         result = 37 * result + ( getBillingNo() == null ? 0 : this.getBillingNo().hashCode() );
         result = 37 * result + this.getSalesPersonId();
         result = 37 * result + ( getCustomerAgentId() == null ? 0 : this.getCustomerAgentId().hashCode() );
         result = 37 * result + ( getNotifyByMail() == null ? 0 : this.getNotifyByMail().hashCode() );
         result = 37 * result + ( getRowguid() == null ? 0 : this.getRowguid().hashCode() );
         return result;
   }   


}


