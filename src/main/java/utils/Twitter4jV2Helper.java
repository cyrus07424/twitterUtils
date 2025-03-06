package utils;

import twitter4j.PaginationToken;
import twitter4j.TweetsResponse;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterV2;
import twitter4j.TwitterV2ExKt;
import twitter4j.UsersResponse;
import twitter4j.V2DefaultFields;

/**
 * Twitter4Jヘルパー(V2).<br>
 * https://github.com/takke/twitter4j-v2
 *
 * @author cyrus
 */
public class Twitter4jV2Helper {

	/**
	 * Twitter4J(V2)を取得.
	 *
	 * @return
	 * @throws TwitterException 
	 */
	public static TwitterV2 getTwitter4jV2() throws TwitterException {
		return getTwitter4jV2(Twitter4jV1Helper.getTwitter4j());
	}

	/**
	 * Twitter4J(V2)を取得.
	 *
	 * @return
	 */
	public static TwitterV2 getTwitter4jV2(Twitter twitter) {
		return TwitterV2ExKt.getV2(twitter);
	}

	/**
	 * ユーザーIDからユーザー情報を取得.<br>
	 * https://docs.x.com/x-api/users/user-lookup-by-id
	 *
	 * @param twitterV2
	 * @param userId
	 * @return
	 * @throws TwitterException
	 */
	public static UsersResponse getUserByUserId(TwitterV2 twitterV2, Long userId) throws TwitterException {
		return getTwitter4jV2().getUsers(
				new long[] { userId },
				V2DefaultFields.tweetFields,
				V2DefaultFields.userFields,
				"pinned_tweet_id");
	}

	/**
	 * スクリーン名からユーザー情報を取得.<br>
	 * https://docs.x.com/x-api/users/user-lookup-by-username
	 *
	 * @param twitterV2
	 * @param screenName
	 * @return
	 * @throws TwitterException
	 */
	public static UsersResponse getUserByScreenName(TwitterV2 twitterV2, String screenName) throws TwitterException {
		return getTwitter4jV2().getUsersBy(
				new String[] { screenName },
				V2DefaultFields.tweetFields,
				V2DefaultFields.userFields,
				"pinned_tweet_id");
	}

	/**
	 * ユーザーIDからタイムラインを取得.<br>
	 * https://docs.x.com/x-api/posts/user-posts-timeline-by-user-id
	 *
	 * @param twitterV2
	 * @param userId
	 * @param paginationTokan
	 * @return
	 * @throws TwitterException
	 */
	public static TweetsResponse getTimelineByUserId(TwitterV2 twitterV2, Long userId, PaginationToken paginationToken)
			throws TwitterException {
		return getTwitter4jV2().getUserTweets(
				userId,
				null,
				"replies,retweets",
				V2DefaultFields.expansions,
				100,
				V2DefaultFields.mediaFields,
				paginationToken,
				V2DefaultFields.placeFields,
				V2DefaultFields.pollFields,
				null,
				null,
				V2DefaultFields.tweetFields,
				null,
				V2DefaultFields.userFields);
	}
}