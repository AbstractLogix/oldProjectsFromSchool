package musiccollectionsorter;

import java.util.ArrayList;
/**
 *
<<<<<<< HEAD
 * @author Oscar Miranda
 * 
 * this class is for creating Album objects
 * it implements the comparable interface which is
 * used to compare album objects by album name
 */
public class Album implements Comparable<Album>
{
    private String artistName, albumName;
    ArrayList<Track> tracks = new ArrayList();

    public Album () {
    }
=======
 * @author omiranda
 */
public class Album implements Comparable<Album>
{
    String artistName, albumName;
    ArrayList<Track> tracks = new ArrayList();

>>>>>>> 5f606b530c29e6742b08993925b632c6eff003aa
    
    public Album (String artistName, String albumName, ArrayList<Track> tracks)
    {
        this.artistName = artistName;
        this.albumName = albumName;
        this.tracks = tracks;
    }    
    
<<<<<<< HEAD
    public String getArtistName()
    {
        return artistName;
    }
    public String getAlbumName()
    {
        return albumName;
    }
    
    public void setArtistName(String artistName)
    {
        this.artistName = artistName;
    }
    public void setAlbumName(String albumName)
    {
        this.albumName = albumName;
    }
    
=======
>>>>>>> 5f606b530c29e6742b08993925b632c6eff003aa
    @Override
    public String toString() 
    {
        return artistName + " " + albumName + " " + tracks;
    }

    @Override
    public int compareTo(Album album2)
    {
<<<<<<< HEAD
      return this.albumName.compareToIgnoreCase(album2.albumName );
    }
    
=======
        Album other = (Album) album2;
        return albumName.compareTo(album2.albumName);
    }
>>>>>>> 5f606b530c29e6742b08993925b632c6eff003aa
}
