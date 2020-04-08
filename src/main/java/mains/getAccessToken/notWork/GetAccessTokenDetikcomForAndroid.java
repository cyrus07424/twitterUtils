package mains.getAccessToken.notWork;

import java.io.IOException;

import mains.getAccessToken.AbstractGetAccessToken;
import twitter4j.TwitterException;

/**
 * detikcom for Androidのアクセストークンを取得.
 *
 * @author cyrus
 */
public class GetAccessTokenDetikcomForAndroid extends AbstractGetAccessToken {

	/**
	 * main.
	 *
	 * @param args
	 * @throws TwitterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TwitterException, IOException {
		getAccessToken("kD5eBZypDVLPlXJVuxmaUw", "PekMoIZkcYy63JCdEdej5CVaxywIJsjrxPjjXX9qGI");
	}
}