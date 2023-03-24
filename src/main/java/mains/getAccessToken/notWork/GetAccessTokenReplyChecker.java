package mains.getAccessToken.notWork;

import java.io.IOException;

import mains.getAccessToken.AbstractGetAccessToken;
import twitter4j.TwitterException;

/**
 * リプライ数チェッ力のアクセストークンを取得.
 *
 * @author cyrus
 */
public class GetAccessTokenReplyChecker extends AbstractGetAccessToken {

	/**
	 * main.
	 *
	 * @param args
	 * @throws TwitterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TwitterException, IOException {
		getAccessToken("NGxrXAu1PXZz87f7FXOPw", "uid3GYxAn3HhcwnaKL5xf1GBi6jJ0XXdB8HAeW5vhQ");
	}
}