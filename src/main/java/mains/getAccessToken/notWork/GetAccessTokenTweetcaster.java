package mains.getAccessToken.notWork;

import java.io.IOException;

import mains.getAccessToken.AbstractGetAccessToken;
import twitter4j.TwitterException;

/**
 * Tweetcasterのアクセストークンを取得.
 *
 * @author cyrus
 */
public class GetAccessTokenTweetcaster extends AbstractGetAccessToken {

	/**
	 * main.
	 *
	 * @param args
	 * @throws TwitterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TwitterException, IOException {
		getAccessToken("rvCsVGIl2etwokzk0rZ3w", "DeyIhM6lJKEXhutMyRWuHiAbEG7ITs12J03i0Xm4");
	}
}