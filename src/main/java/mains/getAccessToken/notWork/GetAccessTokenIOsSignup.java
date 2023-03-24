package mains.getAccessToken.notWork;

import java.io.IOException;

import mains.getAccessToken.AbstractGetAccessToken;
import twitter4j.TwitterException;

/**
 * iOS Signupのアクセストークンを取得.
 *
 * @author cyrus
 */
public class GetAccessTokenIOsSignup extends AbstractGetAccessToken {

	/**
	 * main.
	 *
	 * @param args
	 * @throws TwitterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TwitterException, IOException {
		getAccessToken("IHUYavQ7mmPBhNiBBlF9Q", "cIBZT9N7fMro4kxZa5J2sGVopLTwq9MnBDyvQXNuM");
	}
}