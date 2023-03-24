package mains.getAccessToken.notWork;

import java.io.IOException;

import mains.getAccessToken.AbstractGetAccessToken;
import twitter4j.TwitterException;

/**
 * Twitter for iPadのアクセストークンを取得.
 *
 * @author cyrus
 */
public class GetAccessTokenTwitterForIPad extends AbstractGetAccessToken {

	/**
	 * main.
	 *
	 * @param args
	 * @throws TwitterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TwitterException, IOException {
		getAccessToken("CjulERsDeqhhjSme66ECg", "IQWdVyqFxghAtURHGeGiWAsmCAGmdW3WmbEx6Hck");
	}
}