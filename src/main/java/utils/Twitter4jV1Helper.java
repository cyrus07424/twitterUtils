package utils;

import constants.Configurations;
import twitter4j.PagableResponseList;
import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Twitter4Jヘルパー(V1).
 *
 * @author cyrus
 */
public class Twitter4jV1Helper {

	/**
	 * Twitter4J.
	 */
	private static Twitter twitter;

	/**
	 * APIのリクエスト1回毎のウエイト(ms).
	 */
	private static final long WAIT_MILLIS = 5000;

	/**
	 * Twitter4Jを取得.
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
				ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
				configurationBuilder.setDebugEnabled(true)
						.setOAuthConsumerKey(Configurations.TWITTER_CONSUMER_KEY)
						.setOAuthConsumerSecret(Configurations.TWITTER_CONSUMER_SECRET)
						.setOAuthAccessToken(Configurations.TWITTER_ACCESS_TOKEN)
						.setOAuthAccessTokenSecret(Configurations.TWITTER_ACCESS_TOKEN_SECRET);
				TwitterFactory twitterFactory = new TwitterFactory(configurationBuilder.build());
				twitter = twitterFactory.getInstance();
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
		return getTwitter4j().users().showUser(userId);
	}

	/**
	 * スクリーン名からユーザー情報を取得.
	 *
	 * @param screenName
	 * @return
	 * @throws TwitterException
	 */
	public static User getUserByScreenName(String screenName) throws TwitterException {
		return getTwitter4j().users().showUser(screenName);
	}

	/**
	 * 投稿IDから投稿情報を取得.
	 *
	 * @param statusId
	 * @return
	 * @throws TwitterException
	 */
	public static Status getStatusByStatusId(Long statusId) throws TwitterException {
		return getTwitter4j().tweets().showStatus(statusId);
	}

	/**
	 * クエリからタイムラインを取得.
	 *
	 * @param query
	 * @return
	 * @throws TwitterException
	 */
	public static QueryResult getTimelineByQuery(Query query) throws TwitterException {
		return getTwitter4j().search().search(query);
	}

	/**
	 * ハッシュタグからタイムラインを取得.
	 *
	 * @param hashtag
	 * @return
	 * @throws TwitterException
	 */
	public static QueryResult getTimelineByHashtag(String hashtag) throws TwitterException {
		return getTwitter4j().search().search(new Query("#" + hashtag));
	}

	/**
	 * キーワードからタイムラインを取得.
	 *
	 * @param keyword
	 * @return
	 * @throws TwitterException
	 */
	public static QueryResult getTimelineByKeyword(String keyword) throws TwitterException {
		return getTwitter4j().search().search(new Query(keyword));
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
		Paging paging = new Paging();
		paging.setCount(200);
		if (maxId != null) {
			paging.setMaxId(maxId);
		}
		return getTwitter4j().timelines().getUserTimeline(userId, paging);
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
			return getTwitter4j().friendsFollowers().getFriendsList(userId, cursor, count, true, true);
		} else {
			return getTwitter4j().friendsFollowers().getFriendsList(userId, -1, count, true, true);
		}
	}
}