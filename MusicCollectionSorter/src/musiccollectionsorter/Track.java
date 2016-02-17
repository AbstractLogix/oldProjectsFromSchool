package musiccollectionsorter;

/**
 *
 * @author Oscar Miranda
 * this class creates objects of type track
 */
public class Track
{
    String songName;
    public Track (String songName)
    {
        this.songName = songName;
    }

    @Override
    public String toString()
    {
        return this.songName;
    }
}
