package mains.imageDownloader;

import twitter4j.TwitterException;
import twitter4j.v1.ResponseList;
import twitter4j.v1.Status;
import twitter4j.v1.User;
import utils.Twitter4jHelper;

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
			// 全ての検索対象のスクリーン名に対して実行
			for (String screenName : SCREEN_NAME_ARRAY) {
				downloadTwitterMediaByScreenName(screenName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("■done");
	}

	/**
	 * スクリーン名から投稿のメディアをダウンロード.
	 *
	 * @param screenName
	 * @throws TwitterException
	 */
	private static void downloadTwitterMediaByScreenName(String screenName) throws TwitterException {
		// スクリーン名で検索
		System.out.println("■searching : " + screenName);
		User user = Twitter4jHelper.getUserByScreenName(screenName);
		ResponseList<Status> responseList = Twitter4jHelper.getTimelineByUserId(user.getId(), null);

		long minId = Long.MAX_VALUE;
		while (true) {
			System.out.println("■result count : " + responseList.size());

			// 全てのツイートに対して実行
			for (Status status : responseList) {
				System.out.println("■status : " + status.getText());

				// リツイートではない場合
				if (!status.isRetweet()) {
					// メディアをダウンロード
					downloadStatusMedia(screenName, status);
				}

				if (status.getId() < minId) {
					minId = status.getId();
				}
			}

			// 次のページが存在する場合
			if (!responseList.isEmpty()) {
				// 次のページを取得
				System.out.println("■searching next page...");
				responseList = Twitter4jHelper.getTimelineByUserId(user.getId(), minId - 1);
			} else {
				// 終了
				break;
			}
		}
	}
}