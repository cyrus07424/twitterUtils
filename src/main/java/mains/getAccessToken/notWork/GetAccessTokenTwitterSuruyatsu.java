package mains.getAccessToken.notWork;

import java.io.IOException;

import mains.getAccessToken.AbstractGetAccessToken;
import twitter4j.TwitterException;

/**
 * ツイッターするやつのアクセストークンを取得.
 *
 * @author cyrus
 */
public class GetAccessTokenTwitterSuruyatsu extends AbstractGetAccessToken {

	/**
	 * main.
	 *
	 * @param args
	 * @throws TwitterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TwitterException, IOException {
		getAccessToken("wv3DiVsDsNEmMnGcOiht9A", "YMe809PNegRS6Oo60om16ptfH9lWmMnrDtUO75yejUY");
	}
}