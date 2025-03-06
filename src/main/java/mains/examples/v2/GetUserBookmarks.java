package mains.examples.v2;

import mains.examples.AbstractExample;
import twitter4j.Tweet;
import twitter4j.TweetsResponse;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterV2;
import utils.Twitter4jV1Helper;
import utils.Twitter4jV2Helper;

/**
 * ユーザーのブックマークを取得(V2).
 *
 * @author cyrus
 */
public class GetUserBookmarks extends AbstractExample {

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

			// ブックマークを取得
/* 
403:The request is understood, but it has been refused. An accompanying error message will explain why. This code is used when requests are being denied due to update limits (https://support.twitter.com/articles/15364-about-twitter-limits-update-api-dm-and-following).
{
  "title": "Unsupported Authentication",
  "detail": "Authenticating with OAuth 1.0a User Context is forbidden for this endpoint.  Supported authentication types are [OAuth 2.0 User Context].",
  "type": "https://api.twitter.com/2/problems/unsupported-authentication",
  "status": 403
}
 */
			TweetsResponse bookmarkResponse = twitterV2.getBookmarks(
					twitter.users().verifyCredentials().getId(),
					null,
					MAX_RESULTS,
					null,
					null,
					null,
					null,
					null,
					null);

			// 全てのブックマークに対して実行
			for (Tweet tweet : bookmarkResponse.getTweets()) {
				System.out.println("■tweet: " + tweet.getText());
			}
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}
}