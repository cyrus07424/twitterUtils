package mains.getAccessToken.notWork;

import java.io.IOException;

import mains.getAccessToken.AbstractGetAccessToken;
import twitter4j.TwitterException;

/**
 * ぶっかけったーのアクセストークンを取得.
 *
 * @author cyrus
 */
public class GetAccessTokenBukkaketter extends AbstractGetAccessToken {

	/**
	 * main.
	 *
	 * @param args
	 * @throws TwitterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TwitterException, IOException {
		getAccessToken("3idWTgCLz4TNVQcT1mYA", "hYnmiojjElCoGoVyKr4WDXouaoXbE4geFzk9VBeeGA");
	}
}