/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

/**
 *
 * @author Otto
 */
public class WebUserPermissions implements java.io.Serializable {

    private int id;
    private Integer webUserId;
    private Integer permissionId;

    public WebUserPermissions() {
        clearWebUserPermissions();
    }

    private void clearWebUserPermissions() {
        this.id = 0;
        this.webUserId = 0;
        this.permissionId = 0;
    }

    public WebUserPermissions(int id) {
        this.id = id;
    }

    public WebUserPermissions(int id, Integer webUserId, Integer permissionId) {
        this.id = id;
        this.webUserId = webUserId;
        this.permissionId = permissionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public Integer getWebUserId() {
        return webUserId;
    }

    public void setWebUserId(Integer webUserId) {
        this.webUserId = webUserId;
    }

}
