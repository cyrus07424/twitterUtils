package mains.getAccessToken.notWork;

import java.io.IOException;

import mains.getAccessToken.AbstractGetAccessToken;
import twitter4j.TwitterException;

/**
 * mikutterのアクセストークンを取得.
 *
 * @author cyrus
 */
public class GetAccessTokenMikutter extends AbstractGetAccessToken {

	/**
	 * main.
	 *
	 * @param args
	 * @throws TwitterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TwitterException, IOException {
		getAccessToken("AmDS1hCCXWstbss5624kVw", "KOPOooopg9Scu7gJUBHBWjwkXz9xgPJxnhnhO55VQ");
	}
}