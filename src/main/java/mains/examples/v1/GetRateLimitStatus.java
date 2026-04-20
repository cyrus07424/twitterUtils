package mains.examples.v1;

import java.util.Map;
import java.util.Map.Entry;

import mains.examples.AbstractExample;
import twitter4j.RateLimitStatus;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import utils.Twitter4jV1Helper;

/**
 * RateLimitを取得.
 *
 * @author cyrus
 */
public class GetRateLimitStatus extends AbstractExample {

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

			// ユーザーのメンションを取得
			Map<String, RateLimitStatus> rateLimitStatusMap = twitter.help().getRateLimitStatus();
			System.out.println("■rateLimitStatusMap: " + rateLimitStatusMap);

			// 全てのツイートに対して実行
			for (Entry<String, RateLimitStatus> entry : rateLimitStatusMap.entrySet()) {
				System.out.println(entry);
			}
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}
}