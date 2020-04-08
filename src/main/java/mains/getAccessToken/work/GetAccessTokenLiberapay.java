package mains.getAccessToken.work;

import java.io.IOException;

import mains.getAccessToken.AbstractGetAccessToken;
import twitter4j.TwitterException;

/**
 * Liberapayのアクセストークンを取得.
 *
 * @author cyrus
 */
public class GetAccessTokenLiberapay extends AbstractGetAccessToken {

	/**
	 * main.
	 *
	 * @param args
	 * @throws TwitterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TwitterException, IOException {
		getAccessToken("h8bBZtoPNz63S5RkZdbo9R5zb", "Jye64vkWxa2dQu64feTnk0BM3j4JO8ZlTa4EQvMDwrweLkwPaw");
	}
}