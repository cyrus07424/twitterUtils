package mains.getAccessToken.work;

import java.io.IOException;

import mains.getAccessToken.AbstractGetAccessToken;
import twitter4j.TwitterException;

/**
 * moTweetsのアクセストークンを取得.
 *
 * @author cyrus
 */
public class GetAccessTokenMoTweets extends AbstractGetAccessToken {

	/**
	 * main.
	 *
	 * @param args
	 * @throws TwitterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TwitterException, IOException {
		getAccessToken("7DIGBXQCmgR28M8AR9Q", "Cc2dQkA4TdMXoeuB9sedA1uxGB1zrrNmHjF3jfbhSo");
	}
}