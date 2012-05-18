package eu.zomtec.em2012.updater;

import java.io.IOException;
import java.text.ParseException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;

public class LeagueService {
	private LeagueParser leagueParser = new LeagueParser();
	
	private String feedUrl = "http://nak.zomtec.eu/kicker-demo.json";

	public String getFeedUrl() {
		return feedUrl;
	}

	public void setFeedUrl(String feedUrl) {
		this.feedUrl = feedUrl;
	}

	public String loadFeed() throws ClientProtocolException, IOException {
		final HttpClient httpClient = new DefaultHttpClient();
		
        try {
            final HttpGet httpget = new HttpGet(feedUrl);
            final ResponseHandler<String> responseHandler = new BasicResponseHandler();
            final String responseBody = httpClient.execute(httpget, responseHandler);
            return responseBody;
        } finally {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
        	httpClient.getConnectionManager().shutdown();
        }
	}

	public League loadLeague() throws ClientProtocolException, IOException, JSONException, ParseException {
		final String feed = loadFeed();
		return leagueParser.parse(feed);
	}
}
