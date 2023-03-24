package mains.getAccessToken.notWork;

import java.io.IOException;

import mains.getAccessToken.AbstractGetAccessToken;
import twitter4j.TwitterException;

/**
 * Bad Kittyのアクセストークンを取得.
 *
 * @author cyrus
 */
public class GetAccessTokenBadKitty extends AbstractGetAccessToken {

	/**
	 * main.
	 *
	 * @param args
	 * @throws TwitterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TwitterException, IOException {
		getAccessToken("U2wlL59B1VSoncuM5g", "470qgAWiz2Wq8zH1oTCyHol3TTwpOml4U8yeyZmxNtg");
	}
}