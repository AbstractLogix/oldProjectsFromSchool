package beans;
// Generated 16/05/2010 09:58:09 PM by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * WarehouseDetailsId generated by hbm2java
 */
@Embeddable
public class WarehouseDetailsId  implements java.io.Serializable {


     private int id;
     private Integer warehouseId;
     private Integer pieces;
     private String packetTypeId;
     private BigDecimal length;
     private BigDecimal width;
     private BigDecimal height;
     private BigDecimal weight;
     private String measure;
     private String rowguid;

    public WarehouseDetailsId() {
    }

	
    public WarehouseDetailsId(int id, String rowguid) {
        this.id = id;
        this.rowguid = rowguid;
    }
    public WarehouseDetailsId(int id, Integer warehouseId, Integer pieces, String packetTypeId, BigDecimal length, BigDecimal width, BigDecimal height, BigDecimal weight, String measure, String rowguid) {
       this.id = id;
       this.warehouseId = warehouseId;
       this.pieces = pieces;
       this.packetTypeId = packetTypeId;
       this.length = length;
       this.width = width;
       this.height = height;
       this.weight = weight;
       this.measure = measure;
       this.rowguid = rowguid;
    }
   

    @Column(name="id", nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    @Column(name="warehouseId")
    public Integer getWarehouseId() {
        return this.warehouseId;
    }
    
    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    @Column(name="pieces")
    public Integer getPieces() {
        return this.pieces;
    }
    
    public void setPieces(Integer pieces) {
        this.pieces = pieces;
    }

    @Column(name="packetTypeId", length=2)
    public String getPacketTypeId() {
        return this.packetTypeId;
    }
    
    public void setPacketTypeId(String packetTypeId) {
        this.packetTypeId = packetTypeId;
    }

    @Column(name="length", precision=18)
    public BigDecimal getLength() {
        return this.length;
    }
    
    public void setLength(BigDecimal length) {
        this.length = length;
    }

    @Column(name="width", precision=18)
    public BigDecimal getWidth() {
        return this.width;
    }
    
    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    @Column(name="height", precision=18)
    public BigDecimal getHeight() {
        return this.height;
    }
    
    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    @Column(name="weight", precision=18)
    public BigDecimal getWeight() {
        return this.weight;
    }
    
    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    @Column(name="measure", length=10)
    public String getMeasure() {
        return this.measure;
    }
    
    public void setMeasure(String measure) {
        this.measure = measure;
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
		 if ( !(other instanceof WarehouseDetailsId) ) return false;
		 WarehouseDetailsId castOther = ( WarehouseDetailsId ) other; 
         
		 return (this.getId()==castOther.getId())
 && ( (this.getWarehouseId()==castOther.getWarehouseId()) || ( this.getWarehouseId()!=null && castOther.getWarehouseId()!=null && this.getWarehouseId().equals(castOther.getWarehouseId()) ) )
 && ( (this.getPieces()==castOther.getPieces()) || ( this.getPieces()!=null && castOther.getPieces()!=null && this.getPieces().equals(castOther.getPieces()) ) )
 && ( (this.getPacketTypeId()==castOther.getPacketTypeId()) || ( this.getPacketTypeId()!=null && castOther.getPacketTypeId()!=null && this.getPacketTypeId().equals(castOther.getPacketTypeId()) ) )
 && ( (this.getLength()==castOther.getLength()) || ( this.getLength()!=null && castOther.getLength()!=null && this.getLength().equals(castOther.getLength()) ) )
 && ( (this.getWidth()==castOther.getWidth()) || ( this.getWidth()!=null && castOther.getWidth()!=null && this.getWidth().equals(castOther.getWidth()) ) )
 && ( (this.getHeight()==castOther.getHeight()) || ( this.getHeight()!=null && castOther.getHeight()!=null && this.getHeight().equals(castOther.getHeight()) ) )
 && ( (this.getWeight()==castOther.getWeight()) || ( this.getWeight()!=null && castOther.getWeight()!=null && this.getWeight().equals(castOther.getWeight()) ) )
 && ( (this.getMeasure()==castOther.getMeasure()) || ( this.getMeasure()!=null && castOther.getMeasure()!=null && this.getMeasure().equals(castOther.getMeasure()) ) )
 && ( (this.getRowguid()==castOther.getRowguid()) || ( this.getRowguid()!=null && castOther.getRowguid()!=null && this.getRowguid().equals(castOther.getRowguid()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getId();
         result = 37 * result + ( getWarehouseId() == null ? 0 : this.getWarehouseId().hashCode() );
         result = 37 * result + ( getPieces() == null ? 0 : this.getPieces().hashCode() );
         result = 37 * result + ( getPacketTypeId() == null ? 0 : this.getPacketTypeId().hashCode() );
         result = 37 * result + ( getLength() == null ? 0 : this.getLength().hashCode() );
         result = 37 * result + ( getWidth() == null ? 0 : this.getWidth().hashCode() );
         result = 37 * result + ( getHeight() == null ? 0 : this.getHeight().hashCode() );
         result = 37 * result + ( getWeight() == null ? 0 : this.getWeight().hashCode() );
         result = 37 * result + ( getMeasure() == null ? 0 : this.getMeasure().hashCode() );
         result = 37 * result + ( getRowguid() == null ? 0 : this.getRowguid().hashCode() );
         return result;
   }   


}

