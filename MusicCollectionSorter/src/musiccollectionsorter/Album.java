package musiccollectionsorter;

import java.util.ArrayList;
/**
 *
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
    
    public Album (String artistName, String albumName, ArrayList<Track> tracks)
    {
        this.artistName = artistName;
        this.albumName = albumName;
        this.tracks = tracks;
    }    
    
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
    
    @Override
    public String toString() 
    {
        return artistName + " " + albumName + " " + tracks;
    }

    @Override
    public int compareTo(Album album2)
    {
      return this.albumName.compareToIgnoreCase(album2.albumName );
    }
    
}
