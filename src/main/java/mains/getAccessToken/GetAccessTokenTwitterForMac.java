package mains.getAccessToken;

import java.io.IOException;

import twitter4j.TwitterException;

/**
 * Twitter for Macのアクセストークンを取得.
 *
 * @author cyrus
 */
public class GetAccessTokenTwitterForMac extends AbstractGetAccessToken {

	/**
	 * main.
	 *
	 * @param args
	 * @throws TwitterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TwitterException, IOException {
		getAccessToken("3rJOl1ODzm9yZy63FACdg", "5jPoQ5kQvMJFDYRNE8bQ4rHuds4xJqhvgNJM4awaE8");
	}
}