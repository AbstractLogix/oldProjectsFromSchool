package beans;
// Generated 16/05/2010 09:58:09 PM by Hibernate Tools 3.2.1.GA


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CustomerContactsId generated by hbm2java
 */
@Embeddable
public class CustomerContactsId  implements java.io.Serializable {


     private int id;
     private int customerDestinationId;
     private int prefixId;
     private String jobTitle;
     private String name;
     private String lastname;
     private String tel;
     private String cel;
     private String fax;
     private String email;
     private String address;
     private int contactClasificationId;
     private boolean availableReception;
     private Boolean infoContact;
     private Boolean accountingContact;
     private String rowguid;

    public CustomerContactsId() {
    }

	
    public CustomerContactsId(int id, int customerDestinationId, int prefixId, String name, String lastname, int contactClasificationId, boolean availableReception, String rowguid) {
        this.id = id;
        this.customerDestinationId = customerDestinationId;
        this.prefixId = prefixId;
        this.name = name;
        this.lastname = lastname;
        this.contactClasificationId = contactClasificationId;
        this.availableReception = availableReception;
        this.rowguid = rowguid;
    }
    public CustomerContactsId(int id, int customerDestinationId, int prefixId, String jobTitle, String name, String lastname, String tel, String cel, String fax, String email, String address, int contactClasificationId, boolean availableReception, Boolean infoContact, Boolean accountingContact, String rowguid) {
       this.id = id;
       this.customerDestinationId = customerDestinationId;
       this.prefixId = prefixId;
       this.jobTitle = jobTitle;
       this.name = name;
       this.lastname = lastname;
       this.tel = tel;
       this.cel = cel;
       this.fax = fax;
       this.email = email;
       this.address = address;
       this.contactClasificationId = contactClasificationId;
       this.availableReception = availableReception;
       this.infoContact = infoContact;
       this.accountingContact = accountingContact;
       this.rowguid = rowguid;
    }
   

    @Column(name="id", nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    @Column(name="customerDestinationId", nullable=false)
    public int getCustomerDestinationId() {
        return this.customerDestinationId;
    }
    
    public void setCustomerDestinationId(int customerDestinationId) {
        this.customerDestinationId = customerDestinationId;
    }

    @Column(name="prefixId", nullable=false)
    public int getPrefixId() {
        return this.prefixId;
    }
    
    public void setPrefixId(int prefixId) {
        this.prefixId = prefixId;
    }

    @Column(name="jobTitle", length=200)
    public String getJobTitle() {
        return this.jobTitle;
    }
    
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Column(name="name", nullable=false, length=200)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    @Column(name="lastname", nullable=false, length=200)
    public String getLastname() {
        return this.lastname;
    }
    
    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    @Column(name="address", length=150)
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name="contactClasificationId", nullable=false)
    public int getContactClasificationId() {
        return this.contactClasificationId;
    }
    
    public void setContactClasificationId(int contactClasificationId) {
        this.contactClasificationId = contactClasificationId;
    }

    @Column(name="availableReception", nullable=false)
    public boolean isAvailableReception() {
        return this.availableReception;
    }
    
    public void setAvailableReception(boolean availableReception) {
        this.availableReception = availableReception;
    }

    @Column(name="infoContact")
    public Boolean getInfoContact() {
        return this.infoContact;
    }
    
    public void setInfoContact(Boolean infoContact) {
        this.infoContact = infoContact;
    }

    @Column(name="accountingContact")
    public Boolean getAccountingContact() {
        return this.accountingContact;
    }
    
    public void setAccountingContact(Boolean accountingContact) {
        this.accountingContact = accountingContact;
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
		 if ( !(other instanceof CustomerContactsId) ) return false;
		 CustomerContactsId castOther = ( CustomerContactsId ) other; 
         
		 return (this.getId()==castOther.getId())
 && (this.getCustomerDestinationId()==castOther.getCustomerDestinationId())
 && (this.getPrefixId()==castOther.getPrefixId())
 && ( (this.getJobTitle()==castOther.getJobTitle()) || ( this.getJobTitle()!=null && castOther.getJobTitle()!=null && this.getJobTitle().equals(castOther.getJobTitle()) ) )
 && ( (this.getName()==castOther.getName()) || ( this.getName()!=null && castOther.getName()!=null && this.getName().equals(castOther.getName()) ) )
 && ( (this.getLastname()==castOther.getLastname()) || ( this.getLastname()!=null && castOther.getLastname()!=null && this.getLastname().equals(castOther.getLastname()) ) )
 && ( (this.getTel()==castOther.getTel()) || ( this.getTel()!=null && castOther.getTel()!=null && this.getTel().equals(castOther.getTel()) ) )
 && ( (this.getCel()==castOther.getCel()) || ( this.getCel()!=null && castOther.getCel()!=null && this.getCel().equals(castOther.getCel()) ) )
 && ( (this.getFax()==castOther.getFax()) || ( this.getFax()!=null && castOther.getFax()!=null && this.getFax().equals(castOther.getFax()) ) )
 && ( (this.getEmail()==castOther.getEmail()) || ( this.getEmail()!=null && castOther.getEmail()!=null && this.getEmail().equals(castOther.getEmail()) ) )
 && ( (this.getAddress()==castOther.getAddress()) || ( this.getAddress()!=null && castOther.getAddress()!=null && this.getAddress().equals(castOther.getAddress()) ) )
 && (this.getContactClasificationId()==castOther.getContactClasificationId())
 && (this.isAvailableReception()==castOther.isAvailableReception())
 && ( (this.getInfoContact()==castOther.getInfoContact()) || ( this.getInfoContact()!=null && castOther.getInfoContact()!=null && this.getInfoContact().equals(castOther.getInfoContact()) ) )
 && ( (this.getAccountingContact()==castOther.getAccountingContact()) || ( this.getAccountingContact()!=null && castOther.getAccountingContact()!=null && this.getAccountingContact().equals(castOther.getAccountingContact()) ) )
 && ( (this.getRowguid()==castOther.getRowguid()) || ( this.getRowguid()!=null && castOther.getRowguid()!=null && this.getRowguid().equals(castOther.getRowguid()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getId();
         result = 37 * result + this.getCustomerDestinationId();
         result = 37 * result + this.getPrefixId();
         result = 37 * result + ( getJobTitle() == null ? 0 : this.getJobTitle().hashCode() );
         result = 37 * result + ( getName() == null ? 0 : this.getName().hashCode() );
         result = 37 * result + ( getLastname() == null ? 0 : this.getLastname().hashCode() );
         result = 37 * result + ( getTel() == null ? 0 : this.getTel().hashCode() );
         result = 37 * result + ( getCel() == null ? 0 : this.getCel().hashCode() );
         result = 37 * result + ( getFax() == null ? 0 : this.getFax().hashCode() );
         result = 37 * result + ( getEmail() == null ? 0 : this.getEmail().hashCode() );
         result = 37 * result + ( getAddress() == null ? 0 : this.getAddress().hashCode() );
         result = 37 * result + this.getContactClasificationId();
         result = 37 * result + (this.isAvailableReception()?1:0);
         result = 37 * result + ( getInfoContact() == null ? 0 : this.getInfoContact().hashCode() );
         result = 37 * result + ( getAccountingContact() == null ? 0 : this.getAccountingContact().hashCode() );
         result = 37 * result + ( getRowguid() == null ? 0 : this.getRowguid().hashCode() );
         return result;
   }   


}

