package mains.getAccessToken.work;

import java.io.IOException;

import mains.getAccessToken.AbstractGetAccessToken;
import twitter4j.TwitterException;

/**
 * Tweetbot for iOSのアクセストークンを取得(2).
 *
 * @author cyrus
 */
public class GetAccessTokenTweetbotForIOs2 extends AbstractGetAccessToken {

	/**
	 * main.
	 *
	 * @param args
	 * @throws TwitterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TwitterException, IOException {
		getAccessToken("v8xVBZlXBp2AJoWI3VjDjzDkC", "Wpoom4N6tpTgfywzCt6y83gvZubbYoT0vL0V8FXzhyXA74218D");
	}
}