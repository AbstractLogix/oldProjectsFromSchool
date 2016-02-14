package musiccollectionsorter;

import java.util.ArrayList;
/**
 *
 * @author omiranda
 */
public class Album implements Comparable<Album>
{
    String artistName, albumName;
    ArrayList<Track> tracks = new ArrayList();

    
    public Album (String artistName, String albumName, ArrayList<Track> tracks)
    {
        this.artistName = artistName;
        this.albumName = albumName;
        this.tracks = tracks;
    }    
    
    @Override
    public String toString() 
    {
        return artistName + " " + albumName + " " + tracks;
    }

    @Override
    public int compareTo(Album album2)
    {
        Album other = (Album) album2;
        return albumName.compareTo(album2.albumName);
    }
}
