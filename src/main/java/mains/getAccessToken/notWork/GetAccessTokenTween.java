package mains.getAccessToken.notWork;

import java.io.IOException;

import mains.getAccessToken.AbstractGetAccessToken;
import twitter4j.TwitterException;

/**
 * Tweenのアクセストークンを取得.
 *
 * @author cyrus
 */
public class GetAccessTokenTween extends AbstractGetAccessToken {

	/**
	 * main.
	 *
	 * @param args
	 * @throws TwitterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TwitterException, IOException {
		getAccessToken("iOQHfiCUsyOyamW8JJ8jg", "5PS2oa5f2VaKMPrlZa7DTbz0aFULKd3Ojxqgsm142Dw");
	}
}