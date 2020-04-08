package mains.getAccessToken.work;

import java.io.IOException;

import mains.getAccessToken.AbstractGetAccessToken;
import twitter4j.TwitterException;

/**
 * Path 2.0のアクセストークンを取得.
 *
 * @author cyrus
 */
public class GetAccessTokenPath20 extends AbstractGetAccessToken {

	/**
	 * main.
	 *
	 * @param args
	 * @throws TwitterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TwitterException, IOException {
		getAccessToken("9UiCHv8e9fXfdYiIK26Jfg", "qFcIA6Jo7mNURwzpdw1ieXkSzK4vwtF2O6NKo7i8");
	}
}