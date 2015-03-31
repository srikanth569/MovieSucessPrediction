package Twitter;

import org.json.JSONObject;
import org.scribe.model.Response;

import java.io.*;

/**
 * Created by srikanth on 2/8/15.
 */
public class TweetResponseHandler {

    private Response mResponse = null;
    private String mFilename = null;

    public TweetResponseHandler(Response response, String trackingStrings) {
        mResponse = response;
        mFilename = trackingStrings;
    }

    public void handleResponse() {
        assert mResponse != null : "The response cannot be null";

        BufferedReader reader = new BufferedReader(new InputStreamReader(mResponse.getStream()));

        String filename = "/Users/srikanth/Documents/Thesis/" + mFilename+".txt";

        FileWriter fw = null;
        String tweets;
        try {
            while ((tweets = reader.readLine()) != null) {
                try {
                    System.out.println(tweets);

                    JSONObject jObject = new JSONObject(tweets);
                    String text = jObject.getString("text");
                    String id = jObject.getString("id_str");
                    text = text.replace("\n", " ").replace("\r", " ");
                    System.out.println("The text in the json string is " + text);
                    fw = new FileWriter(filename, true); //the true will append the new data
                    fw.append(id + "{||||}" + text + "\n");//appends the string to the file
                    fw.flush();
                    fw.close();
                } catch (Exception e) {
                    System.out.println("The tweet in the exception is "+tweets);
                    e.printStackTrace();
                    if (fw != null) {
                        fw.close();
                    }
                }
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
