package mains.getAccessToken.work;

import java.io.IOException;

import mains.getAccessToken.AbstractGetAccessToken;
import twitter4j.TwitterException;

/**
 * OSX Reverse OAuthのアクセストークンを取得.
 *
 * @author cyrus
 */
public class GetAccessTokenOsxReverseOAuth extends AbstractGetAccessToken {

	/**
	 * main.
	 *
	 * @param args
	 * @throws TwitterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TwitterException, IOException {
		getAccessToken("tXvOrlJDmLnTfiUqJ3Kuw", "AWcBS1BYQ3DRNKNgyZxexXLjV6dLwc7K6EI4PlAqHE");
	}
}