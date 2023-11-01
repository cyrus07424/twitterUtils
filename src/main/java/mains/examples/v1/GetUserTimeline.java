package mains.examples.v1;

import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import utils.Twitter4jV1Helper;

/**
 * ユーザーのタイムラインを取得.
 *
 * @author cyrus
 */
public class GetUserTimeline {

	/**
	 * main.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// Twitter4Jを取得
			Twitter twitter = Twitter4jV1Helper.getTwitter4j();

			System.out.println("■start: " + twitter.users().verifyCredentials().getScreenName());
			System.out.println("■timezone: " + twitter.users().getAccountSettings().getTimeZone().tzinfoName());

			// ユーザーのタイムラインを取得
			List<Status> statusList = twitter.timelines().getUserTimeline();
			// 全てのツイートに対して実行
			for (Status status : statusList) {
				System.out.println(status);
				System.out.println(status.getCreatedAt());
			}
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}
}