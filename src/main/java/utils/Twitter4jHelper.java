package utils;

import constants.Configurations;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.v1.PagableResponseList;
import twitter4j.v1.Paging;
import twitter4j.v1.Query;
import twitter4j.v1.QueryResult;
import twitter4j.v1.ResponseList;
import twitter4j.v1.Status;
import twitter4j.v1.User;

/**
 * Twitter4jヘルパー.
 *
 * @author cyrus
 */
public class Twitter4jHelper {

	/**
	 * Twitter4j.
	 */
	private static Twitter twitter;

	/**
	 * APIのリクエスト1回毎のウエイト(ms).
	 */
	private static final long WAIT_MILLIS = 5000;

	/**
	 * Twitter4jを取得.
	 *
	 * @return
	 */
	public static synchronized Twitter getTwitter4j() {
		try {
			// FIXME ウエイト
			Thread.sleep(WAIT_MILLIS);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (twitter == null) {
			try {
				twitter = Twitter.newBuilder().prettyDebugEnabled(true)
						.oAuthConsumer(Configurations.TWITTER_CONSUMER_KEY, Configurations.TWITTER_CONSUMER_SECRET)
						.oAuthAccessToken(Configurations.TWITTER_ACCESS_TOKEN,
								Configurations.TWITTER_ACCESS_TOKEN_SECRET)
						.build();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return twitter;
	}

	/**
	 * ユーザーIDからユーザー情報を取得.
	 *
	 * @param userId
	 * @return
	 * @throws TwitterException
	 */
	public static User getUserByUserId(Long userId) throws TwitterException {
		return getTwitter4j().v1().users().showUser(userId);
	}

	/**
	 * スクリーン名からユーザー情報を取得.
	 *
	 * @param screenName
	 * @return
	 * @throws TwitterException
	 */
	public static User getUserByScreenName(String screenName) throws TwitterException {
		return getTwitter4j().v1().users().showUser(screenName);
	}

	/**
	 * 投稿IDから投稿情報を取得.
	 *
	 * @param statusId
	 * @return
	 * @throws TwitterException
	 */
	public static Status getStatusByStatusId(Long statusId) throws TwitterException {
		return getTwitter4j().v1().tweets().showStatus(statusId);
	}

	/**
	 * クエリからタイムラインを取得.
	 *
	 * @param query
	 * @return
	 * @throws TwitterException
	 */
	public static QueryResult getTimelineByQuery(Query query) throws TwitterException {
		return getTwitter4j().v1().search().search(query);
	}

	/**
	 * ハッシュタグからタイムラインを取得.
	 *
	 * @param hashtag
	 * @return
	 * @throws TwitterException
	 */
	public static QueryResult getTimelineByHashtag(String hashtag) throws TwitterException {
		return getTwitter4j().v1().search().search(Query.of("#" + hashtag));
	}

	/**
	 * キーワードからタイムラインを取得.
	 *
	 * @param keyword
	 * @return
	 * @throws TwitterException
	 */
	public static QueryResult getTimelineByKeyword(String keyword) throws TwitterException {
		return getTwitter4j().v1().search().search(Query.of(keyword));
	}

	/**
	 * ユーザーIDからタイムラインを取得.<br>
	 * https://developer.twitter.com/en/docs/tweets/timelines/api-reference/get-statuses-user_timeline.html
	 *
	 * @param userId
	 * @param maxId
	 * @return
	 * @throws TwitterException
	 */
	public static ResponseList<Status> getTimelineByUserId(Long userId, Long maxId) throws TwitterException {
		Paging paging = Paging.ofCount(200);
		if (maxId != null) {
			paging = paging.maxId(maxId);
		}
		return getTwitter4j().v1().timelines().getUserTimeline(userId, paging);
	}

	/**
	 * ユーザーIDからフォロー中ユーザー情報一覧を取得.
	 *
	 * @param userId
	 * @param cursor
	 * @return
	 * @throws TwitterException
	 */
	public static PagableResponseList<User> getFollowingUserListByUserId(Long userId, Long cursor)
			throws TwitterException {
		int count = 200;
		if (cursor != null) {
			return getTwitter4j().v1().friendsFollowers().getFriendsList(userId, cursor, count, true, true);
		} else {
			return getTwitter4j().v1().friendsFollowers().getFriendsList(userId, -1, count, true, true);
		}
	}
}