package beans;
// Generated Jul 27, 2009 9:03:31 AM by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;

/**
 * IinvoiceDetails generated by hbm2java
 */
public class IinvoiceDetails  implements java.io.Serializable {


     private int id;
     private String documentNo;
     private String documentType;
     private Integer quantity;
     private String description;
     private BigDecimal total;
     private BigDecimal totalDollars;
     private Integer invoiceId;
     private String rowguid;

    public IinvoiceDetails() {
    }

	
    public IinvoiceDetails(int id, String rowguid) {
        this.id = id;
        this.rowguid = rowguid;
    }
    public IinvoiceDetails(int id, String documentNo, String documentType, Integer quantity, String description, BigDecimal total, BigDecimal totalDollars, Integer invoiceId, String rowguid) {
       this.id = id;
       this.documentNo = documentNo;
       this.documentType = documentType;
       this.quantity = quantity;
       this.description = description;
       this.total = total;
       this.totalDollars = totalDollars;
       this.invoiceId = invoiceId;
       this.rowguid = rowguid;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getDocumentNo() {
        return this.documentNo;
    }
    
    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }
    public String getDocumentType() {
        return this.documentType;
    }
    
    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }
    public Integer getQuantity() {
        return this.quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public BigDecimal getTotal() {
        return this.total;
    }
    
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    public BigDecimal getTotalDollars() {
        return this.totalDollars;
    }
    
    public void setTotalDollars(BigDecimal totalDollars) {
        this.totalDollars = totalDollars;
    }
    public Integer getInvoiceId() {
        return this.invoiceId;
    }
    
    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }
    public String getRowguid() {
        return this.rowguid;
    }
    
    public void setRowguid(String rowguid) {
        this.rowguid = rowguid;
    }




}

