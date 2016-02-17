package beans;
// Generated 16/05/2010 09:58:09 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * WarehouseTrackingId generated by hbm2java
 */
@Embeddable
public class WarehouseTrackingId  implements java.io.Serializable {


     private int id;
     private Date hour;
     private Integer difference;
     private Integer warehouseId;
     private String tracking;
     private String rowguid;

    public WarehouseTrackingId() {
    }

	
    public WarehouseTrackingId(int id, String rowguid) {
        this.id = id;
        this.rowguid = rowguid;
    }
    public WarehouseTrackingId(int id, Date hour, Integer difference, Integer warehouseId, String tracking, String rowguid) {
       this.id = id;
       this.hour = hour;
       this.difference = difference;
       this.warehouseId = warehouseId;
       this.tracking = tracking;
       this.rowguid = rowguid;
    }
   

    @Column(name="id", nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    @Column(name="hour", length=23)
    public Date getHour() {
        return this.hour;
    }
    
    public void setHour(Date hour) {
        this.hour = hour;
    }

    @Column(name="difference")
    public Integer getDifference() {
        return this.difference;
    }
    
    public void setDifference(Integer difference) {
        this.difference = difference;
    }

    @Column(name="warehouseId")
    public Integer getWarehouseId() {
        return this.warehouseId;
    }
    
    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    @Column(name="tracking", length=100)
    public String getTracking() {
        return this.tracking;
    }
    
    public void setTracking(String tracking) {
        this.tracking = tracking;
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
		 if ( !(other instanceof WarehouseTrackingId) ) return false;
		 WarehouseTrackingId castOther = ( WarehouseTrackingId ) other; 
         
		 return (this.getId()==castOther.getId())
 && ( (this.getHour()==castOther.getHour()) || ( this.getHour()!=null && castOther.getHour()!=null && this.getHour().equals(castOther.getHour()) ) )
 && ( (this.getDifference()==castOther.getDifference()) || ( this.getDifference()!=null && castOther.getDifference()!=null && this.getDifference().equals(castOther.getDifference()) ) )
 && ( (this.getWarehouseId()==castOther.getWarehouseId()) || ( this.getWarehouseId()!=null && castOther.getWarehouseId()!=null && this.getWarehouseId().equals(castOther.getWarehouseId()) ) )
 && ( (this.getTracking()==castOther.getTracking()) || ( this.getTracking()!=null && castOther.getTracking()!=null && this.getTracking().equals(castOther.getTracking()) ) )
 && ( (this.getRowguid()==castOther.getRowguid()) || ( this.getRowguid()!=null && castOther.getRowguid()!=null && this.getRowguid().equals(castOther.getRowguid()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getId();
         result = 37 * result + ( getHour() == null ? 0 : this.getHour().hashCode() );
         result = 37 * result + ( getDifference() == null ? 0 : this.getDifference().hashCode() );
         result = 37 * result + ( getWarehouseId() == null ? 0 : this.getWarehouseId().hashCode() );
         result = 37 * result + ( getTracking() == null ? 0 : this.getTracking().hashCode() );
         result = 37 * result + ( getRowguid() == null ? 0 : this.getRowguid().hashCode() );
         return result;
   }   


}


