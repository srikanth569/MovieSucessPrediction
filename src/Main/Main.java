package Main;

import IMDB.IMDBInfoGatherer;
import Twitter.TweetGatherer;

import java.util.ArrayList;

/**
 * Created by srikanth on 2/8/15.
 */
public class Main {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("American Sniper");
        TweetGatherer tweetGatherer = new TweetGatherer(arrayList);
        tweetGatherer.beginGathering();

        IMDBInfoGatherer imdbInfoGatherer = new IMDBInfoGatherer(arrayList);
        imdbInfoGatherer.beginGathering();
    }
}
