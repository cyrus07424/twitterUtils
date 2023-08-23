package mains.examples.v2;

import java.util.Map;

import twitter4j.Tweet;
import twitter4j.TwitterException;
import twitter4j.TwitterV2;
import twitter4j.UsersResponse;
import twitter4j.V2DefaultFields;
import utils.Twitter4jV2Helper;

/**
 * ユーザーのタイムラインを取得(V2).
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
			// Twitter4j(V2)を取得
			TwitterV2 twitterV2 = Twitter4jV2Helper.getTwitter4jV2();

			// 自分の情報を取得
			UsersResponse usersResponse = twitterV2.getMe(
					"pinned_tweet_id",
					V2DefaultFields.tweetFields,
					V2DefaultFields.userFields);
			System.out.println("■start: " + usersResponse.getUsers().get(0).getScreenName());

			// 全てのツイートに対して実行
			for (Map.Entry<Long, Tweet> tweet : usersResponse.getTweetsMap().entrySet()) {
				System.out.println("■tweet: " + tweet.getValue().getText());
			}
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}
}