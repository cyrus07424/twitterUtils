package mains.getAccessToken.notWork;

import java.io.IOException;

import mains.getAccessToken.AbstractGetAccessToken;
import twitter4j.TwitterException;

/**
 * detikcom for Blackberryのアクセストークンを取得.
 *
 * @author cyrus
 */
public class GetAccessTokenDetikcomForBlackberry extends AbstractGetAccessToken {

	/**
	 * main.
	 *
	 * @param args
	 * @throws TwitterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TwitterException, IOException {
		getAccessToken("SWtEBuWHarToHiCP7o1uUA", "gJJtTr3x5Jpv8dGjXKksgkmSrWUxk3TGEOj0DXCc4gg");
	}
}