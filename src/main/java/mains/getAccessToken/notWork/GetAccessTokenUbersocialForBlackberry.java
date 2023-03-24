package mains.getAccessToken.notWork;

import java.io.IOException;

import mains.getAccessToken.AbstractGetAccessToken;
import twitter4j.TwitterException;

/**
 * Ubersocial for Blackberryのアクセストークンを取得.
 *
 * @author cyrus
 */
public class GetAccessTokenUbersocialForBlackberry extends AbstractGetAccessToken {

	/**
	 * main.
	 *
	 * @param args
	 * @throws TwitterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TwitterException, IOException {
		getAccessToken("6XIk781PMICfV2w19BfQLw", "RoxBuNOkrln51abF0XT9IXevKCmbyo3dCJb435SOc4");
	}
}