package mains.getAccessToken.notWork;

import java.io.IOException;

import mains.getAccessToken.AbstractGetAccessToken;
import twitter4j.TwitterException;

/**
 * Twitter for Androidのアクセストークンを取得.
 *
 * @author cyrus
 */
public class GetAccessTokenTwitterForAndroid extends AbstractGetAccessToken {

	/**
	 * main.
	 *
	 * @param args
	 * @throws TwitterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TwitterException, IOException {
		getAccessToken("3nVuSoBZnx6U4vzUxf5w", "Bcs59EFbbsdF6Sl9Ng71smgStWEGwXXKSjYvPVt7qys");
	}
}