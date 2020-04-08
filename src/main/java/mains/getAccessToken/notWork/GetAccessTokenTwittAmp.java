package mains.getAccessToken.notWork;

import java.io.IOException;

import mains.getAccessToken.AbstractGetAccessToken;
import twitter4j.TwitterException;

/**
 * TwittAMPのアクセストークンを取得.
 *
 * @author cyrus
 */
public class GetAccessTokenTwittAmp extends AbstractGetAccessToken {

	/**
	 * main.
	 *
	 * @param args
	 * @throws TwitterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TwitterException, IOException {
		getAccessToken("XHOuZ5bBMAMleIpm4h28JA", "nZ0IsdMUSdSSuSIDojjpVxYqCeXIBCPii5o565DKs");
	}
}