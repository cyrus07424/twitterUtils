package mains.getAccessToken.notWork;

import java.io.IOException;

import mains.getAccessToken.AbstractGetAccessToken;
import twitter4j.TwitterException;

/**
 * Mobile Clientのアクセストークンを取得.
 *
 * @author cyrus
 */
public class GetAccessTokenMobileClient extends AbstractGetAccessToken {

	/**
	 * main.
	 *
	 * @param args
	 * @throws TwitterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TwitterException, IOException {
		getAccessToken("m9QsrrmJoANGROAiNKaC8g", "udnsc1IAyTQnkj0KPfZffb9usZ6ZqVoXcdD3oxIVo");
	}
}