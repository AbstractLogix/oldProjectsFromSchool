
package musiccollectionsorter;

import java.util.Comparator;

/**
 *
 * @author Oscar Miranda
 * this class implements comparator and compares objects 
 * by artist name
 */
public class albumComparator implements Comparator<Album>
{
    @Override
    public int compare(Album o, Album t)
    {
        return o.getArtistName().compareToIgnoreCase(t.getArtistName());
    }    
}
