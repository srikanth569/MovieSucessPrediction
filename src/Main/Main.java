package Main;

import IMDB.IMDBInfoGatherer;
import Twitter.TweetGatherer;

/**
 * Created by srikanth on 2/8/15.
 */
public class Main {
    public static void main(String[] args) {
        TweetGatherer tweetGatherer = new TweetGatherer();
        tweetGatherer.beginGathering();

        IMDBInfoGatherer imdbInfoGatherer = new IMDBInfoGatherer();
        imdbInfoGatherer.beginGathering();
    }
}
