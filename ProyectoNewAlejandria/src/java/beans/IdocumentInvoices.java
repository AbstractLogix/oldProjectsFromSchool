package beans;
// Generated Jul 27, 2009 9:16:07 AM by Hibernate Tools 3.2.1.GA



/**
 * IdocumentInvoices generated by hbm2java
 */
public class IdocumentInvoices  implements java.io.Serializable {


     private int id;
     private String documentType;
     private String documentNo;
     private int invoiceId;
     private String rowguid;

    public IdocumentInvoices() {
    }

    public IdocumentInvoices(int id, String documentType, String documentNo, int invoiceId, String rowguid) {
       this.id = id;
       this.documentType = documentType;
       this.documentNo = documentNo;
       this.invoiceId = invoiceId;
       this.rowguid = rowguid;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getDocumentType() {
        return this.documentType;
    }
    
    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }
    public String getDocumentNo() {
        return this.documentNo;
    }
    
    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }
    public int getInvoiceId() {
        return this.invoiceId;
    }
    
    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }
    public String getRowguid() {
        return this.rowguid;
    }
    
    public void setRowguid(String rowguid) {
        this.rowguid = rowguid;
    }




}

