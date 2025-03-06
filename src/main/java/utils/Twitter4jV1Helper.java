package utils;

import org.apache.commons.lang3.StringUtils;

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
import twitter4j.auth.OAuth2Token;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Twitter4Jヘルパー(V1).<br>
 * https://github.com/Twitter4J/Twitter4J
 *
 * @author cyrus
 */
public class Twitter4jV1Helper {

	/**
	 * Twitter4Jを取得.
	 *
	 * @return
	 * @throws TwitterException 
	 */
	public static Twitter getTwitter4j() throws TwitterException {
		ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
		configurationBuilder.setDebugEnabled(true)
				.setOAuthConsumerKey(Configurations.TWITTER_CONSUMER_KEY)
				.setOAuthConsumerSecret(Configurations.TWITTER_CONSUMER_SECRET);
		if (StringUtils.isNotBlank(Configurations.TWITTER_ACCESS_TOKEN)
				&& StringUtils.isNotBlank(Configurations.TWITTER_ACCESS_TOKEN_SECRET)) {
			configurationBuilder.setOAuthAccessToken(Configurations.TWITTER_ACCESS_TOKEN)
					.setOAuthAccessTokenSecret(Configurations.TWITTER_ACCESS_TOKEN_SECRET);
		} else {
			configurationBuilder.setApplicationOnlyAuthEnabled(true);
		}
		TwitterFactory twitterFactory = new TwitterFactory(configurationBuilder.build());
		Twitter twitter = twitterFactory.getInstance();

		if (StringUtils.isBlank(Configurations.TWITTER_ACCESS_TOKEN)
				|| StringUtils.isBlank(Configurations.TWITTER_ACCESS_TOKEN_SECRET)) {
			// トークンを取得
			OAuth2Token token = twitter.getOAuth2Token();
			System.out.println("token : " + token);
		} else {
			// TODO
		}

		return twitter;
	}

	/**
	 * ユーザーIDからユーザー情報を取得.
	 *
	 * @param twitter
	 * @param userId
	 * @return
	 * @throws TwitterException
	 */
	public static User getUserByUserId(Twitter twitter, Long userId) throws TwitterException {
		return twitter.users().showUser(userId);
	}

	/**
	 * スクリーン名からユーザー情報を取得.
	 *
	 * @param twitter
	 * @param screenName
	 * @return
	 * @throws TwitterException
	 */
	public static User getUserByScreenName(Twitter twitter, String screenName) throws TwitterException {
		return twitter.users().showUser(screenName);
	}

	/**
	 * 投稿IDから投稿情報を取得.
	 *
	 * @param twitter
	 * @param statusId
	 * @return
	 * @throws TwitterException
	 */
	public static Status getStatusByStatusId(Twitter twitter, Long statusId) throws TwitterException {
		return twitter.tweets().showStatus(statusId);
	}

	/**
	 * クエリからタイムラインを取得.
	 *
	 * @param twitter
	 * @param query
	 * @return
	 * @throws TwitterException
	 */
	public static QueryResult getTimelineByQuery(Twitter twitter, Query query) throws TwitterException {
		return twitter.search().search(query);
	}

	/**
	 * ハッシュタグからタイムラインを取得.
	 *
	 * @param twitter
	 * @param hashtag
	 * @return
	 * @throws TwitterException
	 */
	public static QueryResult getTimelineByHashtag(Twitter twitter, String hashtag) throws TwitterException {
		return twitter.search().search(new Query("#" + hashtag));
	}

	/**
	 * キーワードからタイムラインを取得.
	 *
	 * @param twitter
	 * @param keyword
	 * @return
	 * @throws TwitterException
	 */
	public static QueryResult getTimelineByKeyword(Twitter twitter, String keyword) throws TwitterException {
		return twitter.search().search(new Query(keyword));
	}

	/**
	 * ユーザーIDからタイムラインを取得.<br>
	 * https://developer.twitter.com/en/docs/tweets/timelines/api-reference/get-statuses-user_timeline.html
	 *
	 * @param twitter
	 * @param userId
	 * @param maxId
	 * @return
	 * @throws TwitterException
	 */
	public static ResponseList<Status> getTimelineByUserId(Twitter twitter, Long userId, Long maxId)
			throws TwitterException {
		Paging paging = new Paging();
		paging.setCount(200);
		if (maxId != null) {
			paging.setMaxId(maxId);
		}
		return twitter.timelines().getUserTimeline(userId, paging);
	}

	/**
	 * ユーザーIDからフォロー中ユーザー情報一覧を取得.
	 *
	 * @param twitter
	 * @param userId
	 * @param cursor
	 * @return
	 * @throws TwitterException
	 */
	public static PagableResponseList<User> getFollowingUserListByUserId(Twitter twitter, Long userId, Long cursor)
			throws TwitterException {
		int count = 200;
		if (cursor != null) {
			return twitter.friendsFollowers().getFriendsList(userId, cursor, count, true, true);
		} else {
			return twitter.friendsFollowers().getFriendsList(userId, -1, count, true, true);
		}
	}
}