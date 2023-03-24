package mains.getAccessToken.notWork;

import java.io.IOException;

import mains.getAccessToken.AbstractGetAccessToken;
import twitter4j.TwitterException;

/**
 * ShootingStarProのアクセストークンを取得.
 *
 * @author cyrus
 */
public class GetAccessTokenShootingStarPro extends AbstractGetAccessToken {

	/**
	 * main.
	 *
	 * @param args
	 * @throws TwitterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TwitterException, IOException {
		getAccessToken("I8ye5YHbnFUVzrWdyEkXw", "UTXlrSs9IuZuhfxfwBDckzMDHCI8HRlTNtitiV2OL4");
	}
}