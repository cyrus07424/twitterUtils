package mains.getAccessToken.notWork;

import java.io.IOException;

import mains.getAccessToken.AbstractGetAccessToken;
import twitter4j.TwitterException;

/**
 * Echofonのアクセストークンを取得.
 *
 * @author cyrus
 */
public class GetAccessTokenEchofon extends AbstractGetAccessToken {

	/**
	 * main.
	 *
	 * @param args
	 * @throws TwitterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TwitterException, IOException {
		getAccessToken("yqoymTNrS9ZDGsBnlFhIuw", "OMai1whT3sT3XMskI7DZ7xiju5i5rAYJnxSEHaKYvEs");
	}
}