package Twitter;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by srikanth on 2/8/15.
 * <p/>
 * Reference : http://www.jeffkuchta.com/Tutorial/2013/02/21/java--twitter-public-streams-with-support-from-heroku-spark--oauth/
 */
public class TwitterStreamEndpointConsumer extends Thread {

    private OAuthService mOAuthService;
    private Token mAccessToken;
    private OAuthRequest mOAuthRequest;
    private static final String TWITTER_END_POINT = "https://stream.twitter.com/1.1/statuses/filter.json";
    private static final String HOST_NAME = "stream.twitter.com";
    private static final String STREAM_READER = "Twitter Stream Reader";
    private List<String> mKeyWords = null;

    public TwitterStreamEndpointConsumer(List<String> keyWords) {
        if (mKeyWords == null) {
           this.mKeyWords = new ArrayList<>();
        }
        if (mKeyWords.size() > 0) {
            this.mKeyWords.clear();
        }
        this.mKeyWords.addAll(keyWords);
    }

    public void run() {
        System.out.println("Starting Twitter public stream consumer thread.");
        mOAuthService = new ServiceBuilder().provider(TwitterApi.class).apiKey(EnvironmentConstants.API_KEY).apiSecret(EnvironmentConstants.API_SECRET_KEY).build();
        mAccessToken = new Token(EnvironmentConstants.OAUTH_TOKEN, EnvironmentConstants.OAUTH_TOKEN_SECRET);
        System.out.println("Connecting to Twitter public stream ");
        mOAuthRequest = new OAuthRequest(Verb.POST, TWITTER_END_POINT);
        mOAuthRequest.addHeader("version", "HTTP/1.1");
        mOAuthRequest.addHeader("host", HOST_NAME);
        mOAuthRequest.setConnectionKeepAlive(true);
        mOAuthRequest.addHeader("user-agent", STREAM_READER);
        String trackingStrings = getTrackingStrings();
        mOAuthRequest.addBodyParameter("track",trackingStrings);
        mOAuthRequest.addBodyParameter("language", "en");
        mOAuthService.signRequest(mAccessToken, mOAuthRequest);
        Response response = mOAuthRequest.send();
        System.out.print("The response code is " + response.getCode());
        if (response.getCode() == 420) {
            System.out.println("Twitter 420 warning, please use caution");
            System.exit(1);
        }
        if (response.getCode() == 200) {
            trackingStrings = trackingStrings.replace(",","");
            trackingStrings = trackingStrings.replace(" ","");
            TweetResponseHandler handler = new TweetResponseHandler(response , trackingStrings);
            handler.handleResponse();
        }
    }


    private String getTrackingStrings() {
        if (mKeyWords == null) {
            return "";
        }

        if (mKeyWords.size() > 0) {
            StringBuilder builder = new StringBuilder();
            Iterator<String> item = mKeyWords.iterator();
            while (item.hasNext()) {
                builder.append(item.next() + ", ");
            }
            return builder.toString();
        }

        return "";
    }
}
