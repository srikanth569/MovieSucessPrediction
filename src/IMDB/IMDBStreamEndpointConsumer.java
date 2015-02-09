package IMDB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by srikanth on 2/8/15.
 */
public class IMDBStreamEndpointConsumer extends Thread {

    ArrayList<String> mKeyWords;
    URL url;
    private static final String BASE_URL = "http://www.omdbapi.com/?";

    public IMDBStreamEndpointConsumer(ArrayList<String> arrayList) {
        if (arrayList == null) {
            return;
        }
        if (mKeyWords == null) {
            mKeyWords = new ArrayList<String>();
        }
        if (mKeyWords.size() > 0) {
            mKeyWords.clear();
        }
        mKeyWords.addAll(arrayList);

    }

    @Override
    public void run() {

        System.out.println("Connecting to IMDB api");
        Iterator<String> item = mKeyWords.iterator();
        try {
            String urlAP = BASE_URL + "American+Sniper";
            url = new URL(urlAP);
            URLConnection yc = url.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            yc.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null)
                System.out.println(inputLine);
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}