package mains.getAccessToken.notWork;

import java.io.IOException;

import mains.getAccessToken.AbstractGetAccessToken;
import twitter4j.TwitterException;

/**
 * Biyon≡(　ε:)のアクセストークンを取得.
 *
 * @author cyrus
 */
public class GetAccessTokenTwitterBiyon extends AbstractGetAccessToken {

	/**
	 * main.
	 *
	 * @param args
	 * @throws TwitterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TwitterException, IOException {
		getAccessToken("1OX99vQSUgHp7Vlf2Q", "p98mpJ4P6BXmhrCj9MyQg0Ae2G2JwckyGNEUBiMF2do");
	}
}