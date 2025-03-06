package mains.imageDownloader;

import org.apache.commons.lang3.StringUtils;

import twitter4j.PaginationToken;
import twitter4j.Tweet;
import twitter4j.TweetsResponse;
import twitter4j.TwitterException;
import twitter4j.TwitterV2;
import twitter4j.UsersResponse;
import utils.Twitter4jV2Helper;

/**
 * DownloadTwitterMediaByScreenName.
 *
 * @author cyrus
 */
public class DownloadTwitterMediaByScreenName extends AbstractDownloadTwitterImage {

	/**
	 * 検索対象のスクリーン名一覧.
	 */
	private static String[] SCREEN_NAME_ARRAY = { "CHANGE ME" };

	/**
	 * main.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("■start");
		try {
			// Twitter4J(V2)を取得
			TwitterV2 twitterV2 = Twitter4jV2Helper.getTwitter4jV2();

			// 全ての検索対象のスクリーン名に対して実行
			for (String screenName : SCREEN_NAME_ARRAY) {
				if (StringUtils.isNotBlank(screenName)) {
					downloadTwitterMediaByScreenName(twitterV2, screenName);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("■done");
	}

	/**
	 * スクリーン名から投稿のメディアをダウンロード.
	 *
	 * @param twitter
	 * @param screenName
	 * @throws TwitterException
	 * @throws InterruptedException 
	 */
	private static void downloadTwitterMediaByScreenName(TwitterV2 twitterV2, String screenName)
			throws TwitterException, InterruptedException {
		// スクリーン名からユーザー情報を取得
		System.out.println("■searching : " + screenName);
		UsersResponse usersResponse = Twitter4jV2Helper.getUserByScreenName(twitterV2, screenName);

		PaginationToken paginationToken = null;
		while (true) {
			// ユーザーIDからタイムラインを取得
			TweetsResponse tweetsResponse = Twitter4jV2Helper
					.getTimelineByUserId(twitterV2, usersResponse.getUsers().get(0).getId(), paginationToken);
			System.out.println("■result count : " + tweetsResponse.getTweets().size());

			// 全てのツイートに対して実行
			for (Tweet tweet : tweetsResponse.getTweets()) {
				try {
					System.out.println("■tweet : " + tweet.getText());

					// メディアをダウンロード
					downloadTweetMedia(screenName, tweetsResponse, tweet);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// トークンを取得
			paginationToken = tweetsResponse.getMeta().getNextToken();

			// 次のページが存在しない場合
			if (paginationToken == null) {
				// 終了
				break;
			} else {
				// FIXME 15分間スリープ
				Thread.sleep(1000 * 60 * 15);
			}
		}
	}
}