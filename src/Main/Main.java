package Main;

import Twitter.TweetGatherer;

import java.util.ArrayList;

/**
 * Created by srikanth on 2/8/15.
 */
public class Main {
    public static void main(String[] args) {

        // IMDBInfoGatherer imdbInfoGatherer = new IMDBInfoGatherer(arrayList);
        // imdbInfoGatherer.beginGathering();

        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("Furious 7");

        MyThread furious7 = new MyThread(arrayList);
      //  furious7.start();

        ArrayList<String> arrayList1 = new ArrayList<>();
        arrayList1.add("effie gray");

        MyThread last_knights = new MyThread(arrayList1);
        last_knights.start();
    }
}

class MyThread extends Thread {
    ArrayList<String> mSearchStrings;

    MyThread(ArrayList<String> searchStrings) {
        this.mSearchStrings = searchStrings;
    }

    public void run() {

        TweetGatherer tweetGatherer = new TweetGatherer(this.mSearchStrings);
        tweetGatherer.beginGathering();
    }
}
