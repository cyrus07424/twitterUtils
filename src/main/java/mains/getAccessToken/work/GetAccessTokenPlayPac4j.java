package mains.getAccessToken.work;

import java.io.IOException;

import mains.getAccessToken.AbstractGetAccessToken;
import twitter4j.TwitterException;

/**
 * Play Pac4jのアクセストークンを取得.
 *
 * @author cyrus
 */
public class GetAccessTokenPlayPac4j extends AbstractGetAccessToken {

	/**
	 * main.
	 *
	 * @param args
	 * @throws TwitterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TwitterException, IOException {
		getAccessToken("HVSQGAw2XmiwcKOTvZFbQ", "FSiO9G9VRR4KCuksky0kgGuo8gAVndYymr4Nl7qc8AA");
	}
}