package mains.examples.v1;

import java.util.Scanner;

import mains.examples.AbstractExample;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import utils.Twitter4jV1Helper;

/**
 * ツイートを投稿.
 *
 * @author cyrus
 */
public class UpdateStatus extends AbstractExample {

	/**
	 * main.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// Twitter4Jを取得
			Twitter twitter = Twitter4jV1Helper.getTwitter4j();

			// 認証されたユーザー情報を出力
			dumpTwitterUsers(twitter);

			try (Scanner scanner = new Scanner(System.in)) {
				System.out.println("投稿内容を入力してください:");
				String text = scanner.nextLine();
				
				// ツイートを投稿
				twitter.tweets().updateStatus(new StatusUpdate(text));
			}
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}
}