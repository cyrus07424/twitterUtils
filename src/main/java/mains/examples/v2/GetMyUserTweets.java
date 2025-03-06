package mains.examples.v2;

import mains.examples.AbstractExample;
import twitter4j.Tweet;
import twitter4j.TweetsResponse;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterV2;
import twitter4j.V2DefaultFields;
import utils.Twitter4jV1Helper;
import utils.Twitter4jV2Helper;

/**
 * 自分のタイムラインを取得(V2).
 *
 * @author cyrus
 */
public class GetMyUserTweets extends AbstractExample {

	/**
	 * 取得する最大件数(5-100).
	 */
	private static final int MAX_RESULTS = 5;

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

			// 自分のタイムラインを取得
			TweetsResponse userMentionsResponse = twitterV2.getUserTweets(
					twitter.users().verifyCredentials().getId(),
					null,
					null,
					V2DefaultFields.expansions,
					MAX_RESULTS,
					V2DefaultFields.mediaFields,
					null,
					V2DefaultFields.placeFields,
					V2DefaultFields.pollFields,
					null,
					null,
					V2DefaultFields.tweetFields,
					null,
					V2DefaultFields.userFields);

			// 全てのタイムラインに対して実行
			for (Tweet tweet : userMentionsResponse.getTweets()) {
				System.out.println("■tweet: " + tweet.getText());
			}
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}
}