package beans;
// Generated 16/05/2010 09:58:09 PM by Hibernate Tools 3.2.1.GA


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * IdocumentInvoicesId generated by hbm2java
 */
@Embeddable
public class IdocumentInvoicesId  implements java.io.Serializable {


     private String documentType;
     private String documentNo;
     private int invoiceId;

    public IdocumentInvoicesId() {
    }

    public IdocumentInvoicesId(String documentType, String documentNo, int invoiceId) {
       this.documentType = documentType;
       this.documentNo = documentNo;
       this.invoiceId = invoiceId;
    }
   

    @Column(name="documentType", nullable=false, length=10)
    public String getDocumentType() {
        return this.documentType;
    }
    
    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    @Column(name="documentNo", nullable=false, length=50)
    public String getDocumentNo() {
        return this.documentNo;
    }
    
    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }

    @Column(name="invoiceId", nullable=false)
    public int getInvoiceId() {
        return this.invoiceId;
    }
    
    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof IdocumentInvoicesId) ) return false;
		 IdocumentInvoicesId castOther = ( IdocumentInvoicesId ) other; 
         
		 return ( (this.getDocumentType()==castOther.getDocumentType()) || ( this.getDocumentType()!=null && castOther.getDocumentType()!=null && this.getDocumentType().equals(castOther.getDocumentType()) ) )
 && ( (this.getDocumentNo()==castOther.getDocumentNo()) || ( this.getDocumentNo()!=null && castOther.getDocumentNo()!=null && this.getDocumentNo().equals(castOther.getDocumentNo()) ) )
 && (this.getInvoiceId()==castOther.getInvoiceId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getDocumentType() == null ? 0 : this.getDocumentType().hashCode() );
         result = 37 * result + ( getDocumentNo() == null ? 0 : this.getDocumentNo().hashCode() );
         result = 37 * result + this.getInvoiceId();
         return result;
   }   


}


