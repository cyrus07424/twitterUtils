package mains.getAccessToken.notWork;

import java.io.IOException;

import mains.getAccessToken.AbstractGetAccessToken;
import twitter4j.TwitterException;

/**
 * OpenTweenのアクセストークンを取得.
 *
 * @author cyrus
 */
public class GetAccessTokenOpenTween extends AbstractGetAccessToken {

	/**
	 * main.
	 *
	 * @param args
	 * @throws TwitterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TwitterException, IOException {
		getAccessToken("ST6eAABKDRKTqbN7pPo2A", "BJMEiivrXlqGESzdb8D0bvLfNYf3fifXRDMFjMogXg");
	}
}