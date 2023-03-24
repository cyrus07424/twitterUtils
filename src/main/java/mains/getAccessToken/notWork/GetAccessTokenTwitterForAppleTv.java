package mains.getAccessToken.notWork;

import java.io.IOException;

import mains.getAccessToken.AbstractGetAccessToken;
import twitter4j.TwitterException;

/**
 * Twitter for Apple TVのアクセストークンを取得.
 *
 * @author cyrus
 */
public class GetAccessTokenTwitterForAppleTv extends AbstractGetAccessToken {

	/**
	 * main.
	 *
	 * @param args
	 * @throws TwitterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TwitterException, IOException {
		getAccessToken("dUx44WidVJnwtAQacwYSRyawo", "2fsWxPWaXY83ICRjJf5fs22W4OwNqLOKLGyEyJBhV20TldPV6k");
	}
}