package mains.getAccessToken.notWork;

import java.io.IOException;

import mains.getAccessToken.AbstractGetAccessToken;
import twitter4j.TwitterException;

/**
 * Mixeroのアクセストークンを取得.
 *
 * @author cyrus
 */
public class GetAccessTokenMixero extends AbstractGetAccessToken {

	/**
	 * main.
	 *
	 * @param args
	 * @throws TwitterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TwitterException, IOException {
		getAccessToken("bJaKx0V8hdDAfQFXK4P08g", "DRSZv2Bc1Qgl0HDe7gGjZXYuQwkwk9gZAYgEFNnWJs");
	}
}