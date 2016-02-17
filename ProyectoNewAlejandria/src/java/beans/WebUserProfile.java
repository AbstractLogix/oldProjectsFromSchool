package beans;
// Generated Aug 7, 2009 8:31:26 AM by Hibernate Tools 3.2.1.GA



/**
 * WebUserProfile generated by hbm2java
 */
public class WebUserProfile  implements java.io.Serializable {


     private int id;
     private Integer customerId;
     private String language;
     private String defaultView;

    public WebUserProfile() {
        clearWebUserProfile();
    }

    private void clearWebUserProfile() {
        this.id = 0;
        this.customerId = 0;
        this.language = "";
        this.defaultView = "";
    }

	
    public WebUserProfile(int id) {
        this.id = id;
    }
    public WebUserProfile(int id, Integer customerId, String language, String defaultView) {
       this.id = id;
       this.customerId = customerId;
       this.language = language;
       this.defaultView = defaultView;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Integer getCustomerId() {
        return this.customerId;
    }
    
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    public String getLanguage() {
        return this.language;
    }
    
    public void setLanguage(String language) {
        this.language = language;
    }
    public String getDefaultView() {
        return this.defaultView;
    }
    
    public void setDefaultView(String defaultView) {
        this.defaultView = defaultView;
    }




}


