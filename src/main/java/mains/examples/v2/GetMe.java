package mains.examples.v2;

import mains.examples.AbstractExample;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterV2;
import twitter4j.UsersResponse;
import twitter4j.V2DefaultFields;
import utils.Twitter4jV1Helper;
import utils.Twitter4jV2Helper;

/**
 * 自分の情報を取得(V2).
 *
 * @author cyrus
 */
public class GetMe extends AbstractExample {

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

			// 自分の情報を取得
			UsersResponse usersResponse = twitterV2.getMe(
					"pinned_tweet_id",
					V2DefaultFields.tweetFields,
					V2DefaultFields.userFields);

			// ユーザー情報を出力
			dumpUsersResponse(usersResponse);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}
}