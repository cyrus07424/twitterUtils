package mains.getAccessToken.notWork;

import java.io.IOException;

import mains.getAccessToken.AbstractGetAccessToken;
import twitter4j.TwitterException;

/**
 * Blaq for Playbookのアクセストークンを取得.
 *
 * @author cyrus
 */
public class GetAccessTokenBlaqForPlaybook extends AbstractGetAccessToken {

	/**
	 * main.
	 *
	 * @param args
	 * @throws TwitterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TwitterException, IOException {
		getAccessToken("lRFo1RRPXusr9GAORfms7w", "uqujPqX6YRNT2BZ7zPu0IdIZCk3DYojpIPr3Vwgk");
	}
}