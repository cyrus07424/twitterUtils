package mains.removeTweets;

import java.util.List;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.v1.Status;
import utils.Twitter4jHelper;

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
			Twitter twitter = Twitter4jHelper.getTwitter4j();

			System.out.println("■start: " + twitter.v1().users().verifyCredentials().getScreenName());

			// 結果が空になるまで繰り返す
			List<Status> statusList;
			while (!(statusList = twitter.v1().timelines().getUserTimeline()).isEmpty()) {
				// 全てのツイートに対して実行
				for (Status status : statusList) {
					System.out.println(status);
					try {
						// ツイートを削除
						twitter.v1().tweets().destroyStatus(status.getId());
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