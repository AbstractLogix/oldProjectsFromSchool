package musiccollectionsorter;

/**
 *
 * @author omiranda
 */
public class Track implements Comparable
{
    
    String songName;
    public Track (String songName)
    {
        this.songName = songName;
    }

    @Override
    public String toString()
    {
        return songName;
    }
    @Override
    public int compareTo(Object o)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
