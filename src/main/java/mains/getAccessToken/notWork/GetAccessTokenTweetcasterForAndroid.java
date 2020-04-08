package mains.getAccessToken.notWork;

import java.io.IOException;

import mains.getAccessToken.AbstractGetAccessToken;
import twitter4j.TwitterException;

/**
 * Tweetcaster for Androidのアクセストークンを取得.
 *
 * @author cyrus
 */
public class GetAccessTokenTweetcasterForAndroid extends AbstractGetAccessToken {

	/**
	 * main.
	 *
	 * @param args
	 * @throws TwitterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TwitterException, IOException {
		getAccessToken("279MLYOwrFyFFcU6J68u6w", "ck62P6E731zXWKZgNcwu0cJh6K3LTtTOlCQfgOg");
	}
}