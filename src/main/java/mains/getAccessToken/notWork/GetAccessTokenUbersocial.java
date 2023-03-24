package mains.getAccessToken.notWork;

import java.io.IOException;

import mains.getAccessToken.AbstractGetAccessToken;
import twitter4j.TwitterException;

/**
 * Ubersocialのアクセストークンを取得.
 *
 * @author cyrus
 */
public class GetAccessTokenUbersocial extends AbstractGetAccessToken {

	/**
	 * main.
	 *
	 * @param args
	 * @throws TwitterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TwitterException, IOException {
		getAccessToken("xy2SjfpKMhDZSECIlgpS7g", "n2rHTrtQeuxNCJYk3l2cxM7Jf1J1mTZTfJVersSlzU");
	}
}