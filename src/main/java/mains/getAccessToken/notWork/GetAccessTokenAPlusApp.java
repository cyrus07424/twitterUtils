package mains.getAccessToken.notWork;

import java.io.IOException;

import mains.getAccessToken.AbstractGetAccessToken;
import twitter4j.TwitterException;

/**
 * A.Plus Appのアクセストークンを取得.
 *
 * @author cyrus
 */
public class GetAccessTokenAPlusApp extends AbstractGetAccessToken {

	/**
	 * main.
	 *
	 * @param args
	 * @throws TwitterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TwitterException, IOException {
		getAccessToken("DC2XbSUhfLntKJX2XhSB", "Vd0CL3NFXgQdfg0qxPRLybuBfR6wj58R5Dl7fbJw");
	}
}