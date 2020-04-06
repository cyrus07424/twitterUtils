package mains.getAccessToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

/**
 * アクセストークンを取得.
 *
 * @author cyrus
 */
public class AbstractGetAccessToken {

	/**
	 * アクセストークンを取得.<br>
	 * http://twitter4j.org/ja/code-examples.html
	 *
	 * @param consumerKey
	 * @param consumerSecret
	 * @throws TwitterException
	 * @throws IOException
	 */
	public static void getAccessToken(String consumerKey, String consumerSecret) throws TwitterException, IOException {
		Twitter twitter = TwitterFactory.getSingleton();
		twitter.setOAuthConsumer(consumerKey, consumerSecret);
		RequestToken requestToken = twitter.getOAuthRequestToken();
		AccessToken accessToken = null;
		while (null == accessToken) {
			System.out.println("Open the following URL and grant access to your account:");
			System.out.println(requestToken.getAuthorizationURL());
			System.out.print("Enter the PIN(if aviailable) or just hit enter.[PIN]:");
			try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
				String pin = br.readLine();
				if (pin.length() > 0) {
					accessToken = twitter.getOAuthAccessToken(requestToken, pin);
				} else {
					accessToken = twitter.getOAuthAccessToken();
				}
			} catch (TwitterException te) {
				if (401 == te.getStatusCode()) {
					System.out.println("Unable to get the access token.");
				} else {
					te.printStackTrace();
				}
			}
		}
		System.out.println("useId:" + twitter.verifyCredentials().getId());
		System.out.println("accessToken:" + accessToken.getToken());
		System.out.println("tokenSecret:" + accessToken.getTokenSecret());
	}
}
