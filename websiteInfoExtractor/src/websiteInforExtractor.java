import org.jsoup.examples.ListLinks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by omiranda on 5/31/15.
 *
 * Will take a website[s] URL and return video links
 *
 * but what if we sent queries instead and just returned
 * that to the user sorted w/ filters?
 * videos, music, photo --> basically media content
 *
 */
public class websiteInforExtractor {

    public static void main(String args[]){
        URL url;
        InputStream is = null;
        BufferedReader br;
        String line, urlString = " ";
        Scanner input = new Scanner(System.in);


        System.out.println("Please enter website url: ");
        urlString = "https://" + input.next().trim() + "/";

        try {
            url = new URL(urlString);
            is = url.openStream();  // throws an IOException
            br = new BufferedReader(new InputStreamReader(is));

            while ((line = br.readLine()) != null) {
                videoUrlGetter(line);
            }
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException ioe) {
                // nothing to see here
            }
        }
    }

    public static void videoUrlGetter(String html){
        Scanner scanner = new Scanner(html);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(line.contains("/watch")){
                System.out.println(line);
            }
        }
        scanner.close();
    }
}
