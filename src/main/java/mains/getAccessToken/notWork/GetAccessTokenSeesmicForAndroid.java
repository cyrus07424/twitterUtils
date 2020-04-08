package mains.getAccessToken.notWork;

import java.io.IOException;

import mains.getAccessToken.AbstractGetAccessToken;
import twitter4j.TwitterException;

/**
 * Seesmic for Androidのアクセストークンを取得.
 *
 * @author cyrus
 */
public class GetAccessTokenSeesmicForAndroid extends AbstractGetAccessToken {

	/**
	 * main.
	 *
	 * @param args
	 * @throws TwitterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TwitterException, IOException {
		getAccessToken("WMztNBVHsW9S4QKf6S1R", "b2k7ccc9TATOqhKrB2eJpPoZabATy2yXtjJ8LlG0");
	}
}