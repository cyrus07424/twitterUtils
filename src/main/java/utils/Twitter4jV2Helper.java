package utils;

import twitter4j.TwitterException;
import twitter4j.TwitterV2;
import twitter4j.TwitterV2ExKt;
import twitter4j.UsersResponse;
import twitter4j.V2DefaultFields;

/**
 * Twitter4Jヘルパー(V2).
 *
 * @author cyrus
 */
public class Twitter4jV2Helper {

	/**
	 * Twitter4J(V2).
	 */
	private static TwitterV2 twitterV2;

	/**
	 * APIのリクエスト1回毎のウエイト(ms).
	 */
	private static final long WAIT_MILLIS = 5000;

	/**
	 * Twitter4J(V2)を取得.
	 *
	 * @return
	 */
	public static synchronized TwitterV2 getTwitter4jV2() {
		try {
			// FIXME ウエイト
			Thread.sleep(WAIT_MILLIS);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (twitterV2 == null) {
			try {
				twitterV2 = TwitterV2ExKt.getV2(Twitter4jV1Helper.getTwitter4j());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return twitterV2;
	}

	/**
	 * ユーザーIDからユーザー情報を取得.
	 *
	 * @param userId
	 * @return
	 * @throws TwitterException
	 */
	public static UsersResponse getUserByUserId(Long userId) throws TwitterException {
		return getTwitter4jV2().getUsers(
				new long[] { userId },
				V2DefaultFields.tweetFields,
				V2DefaultFields.userFields,
				"pinned_tweet_id");
	}

	/**
	 * スクリーン名からユーザー情報を取得.
	 *
	 * @param screenName
	 * @return
	 * @throws TwitterException
	 */
	public static UsersResponse getUserByScreenName(String screenName) throws TwitterException {
		return getTwitter4jV2().getUsersBy(
				new String[] { screenName },
				V2DefaultFields.tweetFields,
				V2DefaultFields.userFields,
				"pinned_tweet_id");
	}
}