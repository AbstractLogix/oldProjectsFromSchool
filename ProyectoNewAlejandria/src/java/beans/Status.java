package beans;
// Generated Jul 23, 2009 8:28:25 AM by Hibernate Tools 3.2.1.GA



/**
 * Status generated by hbm2java
 */
public class Status  implements java.io.Serializable {


     private int id;
     private String stepId;
     private String name;
     private String rowguid;

    public Status() {
    }

	
    public Status(int id, String stepId, String rowguid) {
        this.id = id;
        this.stepId = stepId;
        this.rowguid = rowguid;
    }
    public Status(int id, String stepId, String name, String rowguid) {
       this.id = id;
       this.stepId = stepId;
       this.name = name;
       this.rowguid = rowguid;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getStepId() {
        return this.stepId;
    }
    
    public void setStepId(String stepId) {
        this.stepId = stepId;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getRowguid() {
        return this.rowguid;
    }
    
    public void setRowguid(String rowguid) {
        this.rowguid = rowguid;
    }




}


