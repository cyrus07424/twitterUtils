package mains.getAccessToken.notWork;

import java.io.IOException;

import mains.getAccessToken.AbstractGetAccessToken;
import twitter4j.TwitterException;

/**
 * Seesmic Twhirlのアクセストークンを取得.
 *
 * @author cyrus
 */
public class GetAccessTokenSeesmicTwhirl extends AbstractGetAccessToken {

	/**
	 * main.
	 *
	 * @param args
	 * @throws TwitterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TwitterException, IOException {
		getAccessToken("nvyZCpiELVuWhBvPh3Sw", "scTemkSBpESGKBlSSCYQuinaQjpf5Hc0BBUBZpFbNsw");
	}
}