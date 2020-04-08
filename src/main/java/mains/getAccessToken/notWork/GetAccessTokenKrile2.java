package mains.getAccessToken.notWork;

import java.io.IOException;

import mains.getAccessToken.AbstractGetAccessToken;
import twitter4j.TwitterException;

/**
 * Krile2のアクセストークンを取得.
 *
 * @author cyrus
 */
public class GetAccessTokenKrile2 extends AbstractGetAccessToken {

	/**
	 * main.
	 *
	 * @param args
	 * @throws TwitterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TwitterException, IOException {
		getAccessToken("K3FZBXVOzsm271KC1jPPHA", "tOJVCdRrlzc08WilwcU5BtwuGzgbo2MlTWJIFRYaeow");
	}
}