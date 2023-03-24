package mains.getAccessToken.notWork;

import java.io.IOException;

import mains.getAccessToken.AbstractGetAccessToken;
import twitter4j.TwitterException;

/**
 * Instagramのアクセストークンを取得.
 *
 * @author cyrus
 */
public class GetAccessTokenInstagram extends AbstractGetAccessToken {

	/**
	 * main.
	 *
	 * @param args
	 * @throws TwitterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TwitterException, IOException {
		getAccessToken("7YBPrscvh0RIThrWYVeGg", "sMO1vDyJ9A0xfOE6RyWNjhTUS1sNqsa7Ae14gOZnw");
	}
}