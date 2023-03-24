package mains.getAccessToken.work;

import java.io.IOException;

import mains.getAccessToken.AbstractGetAccessToken;
import twitter4j.TwitterException;

/**
 * iOS Reverse OAuthのアクセストークンを取得.
 *
 * @author cyrus
 */
public class GetAccessTokenIOsReverseOAuth extends AbstractGetAccessToken {

	/**
	 * main.
	 *
	 * @param args
	 * @throws TwitterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TwitterException, IOException {
		getAccessToken("WXZE9QillkIZpTANgLNT9g", "Aau5SVNpBZc0WCqn7DtQqMFZakGiuaEYAnrby3GUIE");
	}
}