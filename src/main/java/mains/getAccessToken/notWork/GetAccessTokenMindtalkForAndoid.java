package mains.getAccessToken.notWork;

import java.io.IOException;

import mains.getAccessToken.AbstractGetAccessToken;
import twitter4j.TwitterException;

/**
 * Mindtalk for Androidのアクセストークンを取得.
 *
 * @author cyrus
 */
public class GetAccessTokenMindtalkForAndoid extends AbstractGetAccessToken {

	/**
	 * main.
	 *
	 * @param args
	 * @throws TwitterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TwitterException, IOException {
		getAccessToken("SGvBZnSxHbBdwcNncowOBQ", "ZmimffOX0ObOFD2piPUY22U3mTZKEtbm2nUP61QHp8");
	}
}