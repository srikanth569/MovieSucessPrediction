package Twitter;

import Main.IGatherInterface;

import java.util.ArrayList;

/**
 * Created by srikanth on 2/8/15.
 */
public class TweetGatherer implements IGatherInterface {

    ArrayList<String> mArrayList = null;

    public TweetGatherer(ArrayList<String> arrayList) {
        mArrayList = arrayList;
    }

    @Override
    public void beginGathering() {

        TwitterStreamEndpointConsumer twitterStreamEndpointConsumer = new TwitterStreamEndpointConsumer(mArrayList);
        twitterStreamEndpointConsumer.start();
    }
}
