package mains.getAccessToken.notWork;

import java.io.IOException;

import mains.getAccessToken.AbstractGetAccessToken;
import twitter4j.TwitterException;

/**
 * Jingのアクセストークンを取得.
 *
 * @author cyrus
 */
public class GetAccessTokenJing extends AbstractGetAccessToken {

	/**
	 * main.
	 *
	 * @param args
	 * @throws TwitterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TwitterException, IOException {
		getAccessToken("9tGyVimyayvyqUlz7L7JYA", "PcBO252zXfSh4SljRY0kp7rZvWeF4lq2k0lYycu0");
	}
}