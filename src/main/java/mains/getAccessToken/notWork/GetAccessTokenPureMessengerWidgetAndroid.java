package mains.getAccessToken.notWork;

import java.io.IOException;

import mains.getAccessToken.AbstractGetAccessToken;
import twitter4j.TwitterException;

/**
 * Pure messenger widget Androidのアクセストークンを取得.
 *
 * @author cyrus
 */
public class GetAccessTokenPureMessengerWidgetAndroid extends AbstractGetAccessToken {

	/**
	 * main.
	 *
	 * @param args
	 * @throws TwitterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TwitterException, IOException {
		getAccessToken("xCgJn9R7CI1zKcj4jdAAnw", "i3vrK5fhacyWwmNrSOTzHCmVCnA0BUVCsHdvgAtsess");
	}
}