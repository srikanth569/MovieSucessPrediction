package IMDB;

import Main.IGatherInterface;

import java.util.ArrayList;

/**
 * Created by srikanth on 2/8/15.
 */
public class IMDBInfoGatherer implements IGatherInterface {

    ArrayList<String> mArrayList = null;

    public IMDBInfoGatherer(ArrayList<String> arrayList) {
        mArrayList = arrayList;
    }

    @Override
    public void beginGathering() {
        IMDBStreamEndpointConsumer imdbStreamEndpointConsumer = new IMDBStreamEndpointConsumer(mArrayList);
        imdbStreamEndpointConsumer.start();
    }
}
