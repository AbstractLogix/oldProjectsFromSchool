package beans;
// Generated 16/05/2010 09:58:09 PM by Hibernate Tools 3.2.1.GA


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CitiesId generated by hbm2java
 */
@Embeddable
public class CitiesId  implements java.io.Serializable {


     private int id;
     private String stateId;
     private String code;
     private String name;
     private String rowguid;

    public CitiesId() {
    }

    public CitiesId(int id, String stateId, String code, String name, String rowguid) {
       this.id = id;
       this.stateId = stateId;
       this.code = code;
       this.name = name;
       this.rowguid = rowguid;
    }
   

    @Column(name="id", nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    @Column(name="stateId", nullable=false, length=10)
    public String getStateId() {
        return this.stateId;
    }
    
    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    @Column(name="code", nullable=false, length=5)
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    @Column(name="name", nullable=false, length=50)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
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
		 if ( !(other instanceof CitiesId) ) return false;
		 CitiesId castOther = ( CitiesId ) other; 
         
		 return (this.getId()==castOther.getId())
 && ( (this.getStateId()==castOther.getStateId()) || ( this.getStateId()!=null && castOther.getStateId()!=null && this.getStateId().equals(castOther.getStateId()) ) )
 && ( (this.getCode()==castOther.getCode()) || ( this.getCode()!=null && castOther.getCode()!=null && this.getCode().equals(castOther.getCode()) ) )
 && ( (this.getName()==castOther.getName()) || ( this.getName()!=null && castOther.getName()!=null && this.getName().equals(castOther.getName()) ) )
 && ( (this.getRowguid()==castOther.getRowguid()) || ( this.getRowguid()!=null && castOther.getRowguid()!=null && this.getRowguid().equals(castOther.getRowguid()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getId();
         result = 37 * result + ( getStateId() == null ? 0 : this.getStateId().hashCode() );
         result = 37 * result + ( getCode() == null ? 0 : this.getCode().hashCode() );
         result = 37 * result + ( getName() == null ? 0 : this.getName().hashCode() );
         result = 37 * result + ( getRowguid() == null ? 0 : this.getRowguid().hashCode() );
         return result;
   }   


}

