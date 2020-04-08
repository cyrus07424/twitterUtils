package mains.getAccessToken.notWork;

import java.io.IOException;

import mains.getAccessToken.AbstractGetAccessToken;
import twitter4j.TwitterException;

/**
 * Twidroyd for androidのアクセストークンを取得.
 *
 * @author cyrus
 */
public class GetAccessTokenTwidroydForAndroid extends AbstractGetAccessToken {

	/**
	 * main.
	 *
	 * @param args
	 * @throws TwitterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TwitterException, IOException {
		getAccessToken("pCun0Kilhc6VF7fno2g", "3gGiWlgvMWHYy3RrFqj8jWw00kGOg9eIIqOwGiBa1c");
	}
}