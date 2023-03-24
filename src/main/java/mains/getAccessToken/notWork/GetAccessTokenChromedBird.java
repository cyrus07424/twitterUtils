package mains.getAccessToken.notWork;

import java.io.IOException;

import mains.getAccessToken.AbstractGetAccessToken;
import twitter4j.TwitterException;

/**
 * ChromedBirdのアクセストークンを取得.
 *
 * @author cyrus
 */
public class GetAccessTokenChromedBird extends AbstractGetAccessToken {

	/**
	 * main.
	 *
	 * @param args
	 * @throws TwitterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TwitterException, IOException {
		getAccessToken("KkrTiBu0hEMJ9dqS3YCxw", "MsuvABdvwSn2ezvdQzN4uiRR44JK8jESTIJ1hrhe0U");
	}
}