package musiccollectionsorter;

/**
 *
<<<<<<< HEAD
 * @author Oscar Miranda
 * this class creates objects of type track
 */
public class Track
{
=======
 * @author omiranda
 */
public class Track implements Comparable
{
    
>>>>>>> 5f606b530c29e6742b08993925b632c6eff003aa
    String songName;
    public Track (String songName)
    {
        this.songName = songName;
    }

    @Override
    public String toString()
    {
<<<<<<< HEAD
        return this.songName;
    }
=======
        return songName;
    }
    @Override
    public int compareTo(Object o)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
>>>>>>> 5f606b530c29e6742b08993925b632c6eff003aa
}
