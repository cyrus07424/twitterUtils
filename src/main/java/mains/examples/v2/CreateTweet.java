package mains.examples.v2;

import java.util.Scanner;

import mains.examples.AbstractExample;
import twitter4j.CreateTweetResponse;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterV2;
import utils.Twitter4jV1Helper;
import utils.Twitter4jV2Helper;

/**
 * ポストを投稿(V2).
 *
 * @author cyrus
 */
public class CreateTweet extends AbstractExample {

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

			// Twitter4J(V2)を取得
			TwitterV2 twitterV2 = Twitter4jV2Helper.getTwitter4jV2(twitter);

			try (Scanner scanner = new Scanner(System.in)) {
				System.out.println("投稿内容を入力してください:");
				String text = scanner.nextLine();

				// ポストを投稿
				CreateTweetResponse userMentionsResponse = twitterV2.createTweet(
						null,
						null,
						null,
						null,
						null,
						null,
						null,
						null,
						null,
						null,
						null,
						text);
			}
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}
}