/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
/**
 *
 * @author Ferenc
 */
public class DeleteFile implements Runnable{
    
    private String fileName;
    private Integer time;

    public DeleteFile(String fileName, Integer time) {
        this.fileName = fileName;
        this.time = time;
    }


    public void run() {

        try {
            Thread.sleep(time * 1);
            File deleteFile = new File(this.fileName);
            deleteFile.delete();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    
}
