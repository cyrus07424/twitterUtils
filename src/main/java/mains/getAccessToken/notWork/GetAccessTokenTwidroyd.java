package mains.getAccessToken.notWork;

import java.io.IOException;

import mains.getAccessToken.AbstractGetAccessToken;
import twitter4j.TwitterException;

/**
 * Twidroydのアクセストークンを取得.
 *
 * @author cyrus
 */
public class GetAccessTokenTwidroyd extends AbstractGetAccessToken {

	/**
	 * main.
	 *
	 * @param args
	 * @throws TwitterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TwitterException, IOException {
		getAccessToken("0YCqwPFyKUGYEZKIUrh6CA", "CLsIH4fMdA8nHf2jqTjGBk42MRrgyPmj2tzCwBSeU");
	}
}