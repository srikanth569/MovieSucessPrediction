package Twitter;

import Main.IGatherInterface;

import java.util.ArrayList;

/**
 * Created by srikanth on 2/8/15.
 */
public class TweetGatherer implements IGatherInterface {

    @Override
    public void beginGathering() {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("American Sniper");
        TwitterStreamEndpointConsumer twitterStreamEndpointConsumer = new TwitterStreamEndpointConsumer(arrayList);
        twitterStreamEndpointConsumer.start();
    }
}
