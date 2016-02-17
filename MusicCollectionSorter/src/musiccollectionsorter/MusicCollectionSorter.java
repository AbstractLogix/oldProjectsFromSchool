package musiccollectionsorter;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Oscar Miranda
 * This program reads, writes to a text file. 
 * after reading the file it sorts it by certain parameters
 * then gives a user the ability to search based on those parameters
 */
public class MusicCollectionSorter
{
    ArrayList<Album> albums = new ArrayList<>();
    ArrayList<Album> copy = new ArrayList<>();
    /**
     * @param args the command line arguments
     * main method prompts the user with choices on what action to perform
     * 
     */
    public static void main(String[] args)
    {
        MusicCollectionSorter demo = new MusicCollectionSorter();
        demo.getCatalog();
        //demo.printOutAlbums();
        boolean exit = false;
        while(!exit)
        {
            System.out.println("\n--------------------------------------\n");
            System.out.print("Please select one of the following options: \n" + "1. Search by Album Title\n"
                    + "2. Search by Artist\n" + "3. Add album to catalog\n" + "4. Quit\n >>");

            Scanner in = new Scanner(System.in);
            String input = in.nextLine();

            int choice = 0;
            try
            {
                choice = Integer.parseInt(input);
            }
            catch(NumberFormatException e)
            {
                System.out.println("/n Please enter a whole number!");
            }

            switch(choice)
            {
                case 1:
                    demo.sortByAlbum();
                    demo.searchByAlbum();
                    break;
                case 2:
                    demo.sortByArtist();
                    demo.searchByArtist();
                    break;
                case 3:
                    demo.addAlbumToCatalog();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    break;
            }        
        }        
    }
    /**
     * this method prints out the album objects toString method is overwritten
     */
    public void printOutAlbums()
    {
        for(Album albs : albums)
        {
            System.out.println(albs);
        }
    }
    /**
     * This method opens the catalog file and uses a scanner object to 
     * read the file. then creates objects of type Album and adds them
     * to an array list
     */
    public void getCatalog()
    {
        File catalog = new File("catalog2.txt");
        try
        {
            Scanner scanner = new Scanner(catalog);
            
            while(scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                String [] info = line.split(" ");
                
                ArrayList<Track> Tracks = new ArrayList<>();
                
                for(String item : Arrays.copyOfRange(info, 2, info.length -1))
                {
                    Tracks.add(new Track(item));
                }
                
                Album currentAlbum = new Album(
                        info[0],
                        info[1],
                        Tracks
                );
                
                albums.add(currentAlbum);
                copy.add(currentAlbum);
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
            System.exit(99);
        }
        catch(IOException e)
        {
            System.out.println("an I/O error occured");
            System.exit(98);
        }
    }
    /**
     * this method sorts the list by album name using the
     * comparable interface
     */
    public void sortByAlbum()
    {
        Collections.sort(albums);
    }
    /**
     * this will make a copy of the list and sort according to artist name
     * using the albumComparator class
     */
    public void sortByArtist()
    {
        Collections.sort(copy, new albumComparator());
    }
    /**
     * this method searches the albums list by album name
     */
    public void searchByAlbum()
    {
        Scanner in = new Scanner(System.in);
        String albumName;
        System.out.println("Input album name: ");
        albumName = in.nextLine().trim().replaceAll("\\s", "_").toLowerCase();
        Album album = new Album();
        album.setAlbumName(albumName);
        System.out.println("--------------------------------------");
        int arrayIndex = Collections.binarySearch(albums, album);
        if(arrayIndex > -1) {
            System.out.println(albums.get(arrayIndex));
        } else
        {
            System.out.println("Not found!");
        }
    }
    /**
     * this method searches the albums list by artist name
     */
    private void searchByArtist()
    {
        Scanner in = new Scanner(System.in);
        String artistName;
        System.out.println("Input artist name: ");
        artistName = in.nextLine().trim().replaceAll("\\s", "_").toLowerCase();
        Album album = new Album();
        album.setArtistName(artistName);
        System.out.println("--------------------------------------");
        int arrayIndex;
        arrayIndex = Collections.binarySearch(copy, album, new albumComparator());
        try
        {
            String compArtist = copy.get(arrayIndex).getArtistName();
            if(arrayIndex != 0)
            {
                while(arrayIndex < copy.size() - 1 && compArtist.equals(copy.get(arrayIndex).getArtistName()))
                {
                    System.out.println(copy.get(arrayIndex));
                    arrayIndex++;
                }
                while(compArtist.equals(copy.get(arrayIndex).getArtistName()))
                {                
                    System.out.print(copy.get(arrayIndex));
                    arrayIndex--;
                }
            }
        } catch(IndexOutOfBoundsException e){System.out.println("Artist not found.");}
    }
    /**
     * This method adds an album to the file.
     * 
     */
    public void addAlbumToCatalog()
    {
        Scanner in1 = new Scanner(System.in);
        File aFile = new File("catalog2.txt");
        String artistName = "", albumName = "";
        int numOfTracks = 0;
        ArrayList<Track> tracks = new ArrayList<>();
        try
        {
            FileWriter aFW = new FileWriter(aFile, true);
            PrintWriter output = new PrintWriter(aFW);
        
            System.out.println("Please enter Artist ");
            artistName = in1.nextLine().trim().replaceAll("\\s", "_").toLowerCase();


            System.out.println("Please enter name of Album ");
            albumName = in1.nextLine().trim().replaceAll("\\s", "_").toLowerCase();
            
            try 
            {
                System.out.println("How many tracks? ");
                numOfTracks = in1.nextInt();
            } catch(InputMismatchException e){System.out.println("Must enter an integer");}

            
            while(tracks.size() < numOfTracks)
            {
                System.out.println("Please enter track name ");
                tracks.add(new Track(in1.nextLine().trim().replaceAll("\\s", "_")));
            }
            
            Album newAlbum = new Album(artistName, albumName, tracks);
            albums.add(newAlbum);
            output.println(newAlbum);
            output.close();
        }
        
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
            System.exit(99);
        } 
        
        catch (IOException e)
        {
            System.out.println("an I/O error occured");
            System.exit(98);
        }
    }
}
