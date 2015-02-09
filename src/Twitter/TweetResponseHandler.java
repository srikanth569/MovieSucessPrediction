package Twitter;

import org.scribe.model.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by srikanth on 2/8/15.
 */
public class TweetResponseHandler {

    private Response mResponse = null;

    public TweetResponseHandler(Response response) {
        mResponse = response;
    }

    public void handleResponse() {
        assert mResponse != null : "The response cannot be null";
        BufferedReader reader = new BufferedReader(new InputStreamReader(mResponse.getStream()));
        String tweets;
        try {
            while ((tweets = reader.readLine()) != null) {
                System.out.println(tweets);
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
