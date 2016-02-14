package musiccollectionsorter;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author omiranda
 */
public class MusicCollectionSorter
{
    ArrayList<Album> albums = new ArrayList<>();
    String catLine;
    /**
     * @param args the command line arguments
     * 
     * 
     */
    public static void main(String[] args)
    {
        //new UserInput();
        // TODO code application logic here
        MusicCollectionSorter demo = new MusicCollectionSorter();
        demo.getCatalog();
        demo.printOutAlbums();
        
        
    }
    
    public void printOutAlbums()
    {
        for(Album albs : albums)
        {
            System.out.println(albs);
        }
    }
    
    public void getCatalog()
    {
        File catalog = new File("/Users/omiranda/NetBeansProjects/MusicCollectionSorter/src/musiccollectionsorter/catalog2.txt");
        try
        {
            Scanner myCatalog = new Scanner(catalog);
            while(myCatalog.hasNext())
            {
                String artistName = myCatalog.next();
                String albumName = myCatalog.next();
                ArrayList<Track> tracks = new ArrayList();
                
                while(myCatalog.hasNext()){
                    Track myTracks = new Track(myCatalog.next());
                    tracks.add(myTracks);
                    if(myCatalog.hasNextLine()){ break;}
                }
                Album myAlbum = new Album(artistName, albumName, tracks);
                albums.add(myAlbum);
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("couldn't find file");
            System.exit(0);
        }
        catch(IOException e)
        {
            System.out.println("an I/O error occured");
            System.exit(0);
        }
    }
}
