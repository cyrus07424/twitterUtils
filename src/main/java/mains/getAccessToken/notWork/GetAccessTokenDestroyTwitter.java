package mains.getAccessToken.notWork;

import java.io.IOException;

import mains.getAccessToken.AbstractGetAccessToken;
import twitter4j.TwitterException;

/**
 * DestroyTwitterのアクセストークンを取得.
 *
 * @author cyrus
 */
public class GetAccessTokenDestroyTwitter extends AbstractGetAccessToken {

	/**
	 * main.
	 *
	 * @param args
	 * @throws TwitterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TwitterException, IOException {
		getAccessToken("40iqOgCcXcJYwqoa02D7nQ", "o0emdpQvijub2tMXpA7wAVwt3tI4FSx447NfWECS8");
	}
}