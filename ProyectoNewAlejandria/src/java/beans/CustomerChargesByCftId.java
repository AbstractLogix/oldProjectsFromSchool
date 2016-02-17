package beans;
// Generated 16/05/2010 09:58:09 PM by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CustomerChargesByCftId generated by hbm2java
 */
@Embeddable
public class CustomerChargesByCftId  implements java.io.Serializable {


     private int id;
     private int chargesByCftId;
     private BigDecimal flatFee;
     private String rowguid;

    public CustomerChargesByCftId() {
    }

	
    public CustomerChargesByCftId(int id, int chargesByCftId, String rowguid) {
        this.id = id;
        this.chargesByCftId = chargesByCftId;
        this.rowguid = rowguid;
    }
    public CustomerChargesByCftId(int id, int chargesByCftId, BigDecimal flatFee, String rowguid) {
       this.id = id;
       this.chargesByCftId = chargesByCftId;
       this.flatFee = flatFee;
       this.rowguid = rowguid;
    }
   

    @Column(name="id", nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    @Column(name="chargesByCftId", nullable=false)
    public int getChargesByCftId() {
        return this.chargesByCftId;
    }
    
    public void setChargesByCftId(int chargesByCftId) {
        this.chargesByCftId = chargesByCftId;
    }

    @Column(name="flatFee", precision=18)
    public BigDecimal getFlatFee() {
        return this.flatFee;
    }
    
    public void setFlatFee(BigDecimal flatFee) {
        this.flatFee = flatFee;
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
		 if ( !(other instanceof CustomerChargesByCftId) ) return false;
		 CustomerChargesByCftId castOther = ( CustomerChargesByCftId ) other; 
         
		 return (this.getId()==castOther.getId())
 && (this.getChargesByCftId()==castOther.getChargesByCftId())
 && ( (this.getFlatFee()==castOther.getFlatFee()) || ( this.getFlatFee()!=null && castOther.getFlatFee()!=null && this.getFlatFee().equals(castOther.getFlatFee()) ) )
 && ( (this.getRowguid()==castOther.getRowguid()) || ( this.getRowguid()!=null && castOther.getRowguid()!=null && this.getRowguid().equals(castOther.getRowguid()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getId();
         result = 37 * result + this.getChargesByCftId();
         result = 37 * result + ( getFlatFee() == null ? 0 : this.getFlatFee().hashCode() );
         result = 37 * result + ( getRowguid() == null ? 0 : this.getRowguid().hashCode() );
         return result;
   }   


}


