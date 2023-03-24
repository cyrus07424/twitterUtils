package mains.getAccessToken.notWork;

import java.io.IOException;

import mains.getAccessToken.AbstractGetAccessToken;
import twitter4j.TwitterException;

/**
 * TweetDeckのアクセストークンを取得.
 *
 * @author cyrus
 */
public class GetAccessTokenTweetDeck extends AbstractGetAccessToken {

	/**
	 * main.
	 *
	 * @param args
	 * @throws TwitterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TwitterException, IOException {
		getAccessToken("yT577ApRtZw51q4NPMPPOQ", "3neq3XqN5fO3obqwZoajavGFCUrC42ZfbrLXy5sCv8");
	}
}