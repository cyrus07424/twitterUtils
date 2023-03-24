package mains.getAccessToken.notWork;

import java.io.IOException;

import mains.getAccessToken.AbstractGetAccessToken;
import twitter4j.TwitterException;

/**
 * Windows Signupのアクセストークンを取得.
 *
 * @author cyrus
 */
public class GetAccessTokenWindowsSignup extends AbstractGetAccessToken {

	/**
	 * main.
	 *
	 * @param args
	 * @throws TwitterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TwitterException, IOException {
		getAccessToken("j5YtKmlRwbrSgklEmpffQQ", "ggWiqOLcFCZSLXzBwGZF8Ab8btwupF3MZs5cZXxHYs");
	}
}