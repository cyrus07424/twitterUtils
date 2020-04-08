package mains.removeTweets;

import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
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

			System.out.println("■start: " + twitter.getScreenName());

			// 結果が空になるまで繰り返す
			List<Status> statusList;
			while (!(statusList = twitter.getUserTimeline()).isEmpty()) {
				// 全てのツイートに対して実行
				for (Status status : statusList) {
					System.out.println(status);
					try {
						// ツイートを削除
						twitter.destroyStatus(status.getId());
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