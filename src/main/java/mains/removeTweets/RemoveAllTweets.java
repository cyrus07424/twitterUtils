package mains.removeTweets;

import java.util.List;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.Status;
import utils.Twitter4jV1Helper;

/**
 * 全てのツイートを削除.
 *
 * @author cyrus
 */
public class RemoveAllTweets {

	/**
	 * main.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// Twitter4jを取得
			Twitter twitter = Twitter4jV1Helper.getTwitter4j();

			System.out.println("■start: " + twitter.users().verifyCredentials().getScreenName());

			// 結果が空になるまで繰り返す
			List<Status> statusList;
			while (!(statusList = twitter.timelines().getUserTimeline()).isEmpty()) {
				// 全てのツイートに対して実行
				for (Status status : statusList) {
					System.out.println(status);
					try {
						// ツイートを削除
						System.out.println("destroyStatus : " + status.getId());
						twitter.tweets().destroyStatus(status.getId());
					} catch (TwitterException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}
}