package mains.getAccessToken.notWork;

import java.io.IOException;

import mains.getAccessToken.AbstractGetAccessToken;
import twitter4j.TwitterException;

/**
 * detikcom for iPhoneのアクセストークンを取得.
 *
 * @author cyrus
 */
public class GetAccessTokenDetikcomForIPhone extends AbstractGetAccessToken {

	/**
	 * main.
	 *
	 * @param args
	 * @throws TwitterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TwitterException, IOException {
		getAccessToken("pYHDVBc04K4EAhUyImw", "CpLMtELKahoqhSli1ZrEaZt6EvJevCSZ1XDoGdBU");
	}
}