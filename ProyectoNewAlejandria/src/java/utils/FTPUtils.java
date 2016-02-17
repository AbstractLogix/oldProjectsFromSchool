package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.net.ftp.FTPClient;

/**
 *
 * @author Ehernandez
 */
public class FTPUtils {

    //static Logger logger = Logger.getLogger(FTPUtils.class);
    
    public FTPUtils(){
    }
    
    //Metodo para cargar archivo al servidor
    public String upload(String fileName, String url){
        String result = "-1";
        FTPClient client = new FTPClient();
        try{
            //client.connect("cpsworldwide.com"); //Nombre del host
            client.connect("app.cpslogistics.com"); 
            //boolean login = client.login("cpsworldwide", "jThink8182"); //Usuario y contrase�a del ftp
            boolean login = client.login("repositorio", "jIntelligent");
            if(login){ //Verifica si se inicio sesi�n correctamente
                //System.out.println("Se ha conectado correctamente.");
                client.changeWorkingDirectory("/majestic/attachments");//nos movemos dentro del arbol de directorios
                FileInputStream fis = new FileInputStream(url);//Se abre un archivo de nuestra maquina local
                client.setFileType(client.BINARY_FILE_TYPE);//Se pone tipo binario para poder enviar archivos de cualquier tipo
                client.enterLocalPassiveMode();
                boolean res = client.storeFile(fileName, fis); //Se envia el nombre del archivo con su extensi�n y el archivo
                if(res){ //Verifica si se ha agrego el archivo correctamente
                    //System.out.println("Se agrego el archivo correctamente.");
                    result = "ftp://app.cpslogistics.com/majestic/attachments/alejandria/" + fileName; //Se retorna la url de referencia
                } else {
                    System.out.println("no se pudo agregar el archivo, ftpReplyCode: "+client.getReplyCode());
                    System.out.println("replyString: "+client.getReplyString());
                }
                client.logout(); //Se debe cerrar la sesi�n
            }else{
                result = "-2";
                System.out.println("No se ha podido iniciar sesi�n en el servidor FTP");
            }            
            client.disconnect(); //Se debe desconectar
        }catch(IOException ex){
            result = "0";
            System.out.println("No se pudo subir el archivo.");
            ex.printStackTrace();
        }
        return result;
    }
    
    //Metodo para descargar el archivo
    public String download(String fileName, String originalFileName){
        String result = "0";
        FTPClient client = new FTPClient();
        String absolutePath = this.getClass().getResource("/").toString().substring(6); //quito la parte del url que empieza con file:/
        String path = absolutePath.substring(0, absolutePath.indexOf("WEB-INF/classes/")) + "docs/temps/"; //quito la parte del path a las clases y lo dejo hasta /web
        path = path.replace("%20", " "); //Si el path tiene espacios java le pone %20 entonces lo reemplazo por espacio
        try{
            client.connect("192.168.157.92"); 
            //client.connect("cpsworldwide.com"); //Nombre del host
            boolean login = client.login("repositorio", "jIntelligent");
            //boolean login = client.login("cpsworldwide", "jThink8182"); //Usuario y contrase�a del ftp
            if(login){ //Verifica si se inicio sesi�n correctamente
                //System.out.println("Se ha conectado correctamente.");
                //System.out.println("este es el path: "+path);
                client.changeWorkingDirectory("/majestic/attachments/alejandria/");//nos movemos dentro del arbol de directorios
                FileOutputStream fos = new FileOutputStream(path + originalFileName); //Se define donde se guardara el archivo, esto debe de ser en una carpeta local
                client.setFileType(client.BINARY_FILE_TYPE); //Se define de tipo binario para poder descargar cualquier tipo de archivo
                client.enterLocalPassiveMode();
                boolean download = client.retrieveFile(fileName, fos); //Se descarga el archivo, se envia el nombre con el que aparece en el servidor ftp y la variable donde se va a guardar el archivo
                if(download){ //Verifica si la descarga ha sido correcta
                    
                    result = originalFileName; //Se retorna el nombre del archivo
                }else{
                
                    result = "1";
                }
                fos.close();
                //Se le da un tiempo de vida al archivo que se creo en la carpeta local
                new Thread(new DeleteFile(path + originalFileName, 900000)).start();
                
                client.logout(); //Se debe cerrar la sesi�n
            }else{
                result = "1";
                System.out.println("No se ha podido iniciar sesi�n en el servidor FTP");
            }
            client.disconnect(); //Se debe desconectar
        }catch(IOException ex){
            result = "1";
            System.out.println("No se pudo subir el archivo.");
            //logger.error("There was an error uploading the file class: FTPUtils method: download(String fileName, String originalFileName)");
            ex.printStackTrace();
        }
        return result;
    }
    
}
