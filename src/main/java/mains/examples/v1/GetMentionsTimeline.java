package mains.examples.v1;

import java.util.List;

import mains.examples.AbstractExample;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import utils.Twitter4jV1Helper;

/**
 * ユーザーのメンションを取得.
 *
 * @author cyrus
 */
public class GetMentionsTimeline extends AbstractExample {

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
			List<Status> statusList = twitter.timelines().getMentionsTimeline();
			System.out.println("■statusList: " + statusList);

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