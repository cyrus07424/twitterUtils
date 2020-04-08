package mains.getAccessToken.notWork;

import java.io.IOException;

import mains.getAccessToken.AbstractGetAccessToken;
import twitter4j.TwitterException;

/**
 * MivoTVのアクセストークンを取得.
 *
 * @author cyrus
 */
public class GetAccessTokenMivoTv extends AbstractGetAccessToken {

	/**
	 * main.
	 *
	 * @param args
	 * @throws TwitterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TwitterException, IOException {
		getAccessToken("7A6TSGzUKxGSIxZKCsXsmA", "4PgJEFC7ovURlrkS9qWtAIrpyHKddsZfq00GVw962o");
	}
}