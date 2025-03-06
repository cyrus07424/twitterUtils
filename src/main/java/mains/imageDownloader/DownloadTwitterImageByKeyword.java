package mains.imageDownloader;

import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import utils.Twitter4jV1Helper;

/**
 * DownloadTwitterImageByKeyword.
 *
 * @author cyrus
 */
public class DownloadTwitterImageByKeyword extends AbstractDownloadTwitterImage {

	/**
	 * 検索対象のハッシュタグ.
	 */
	private static String KEYWORD = "CHANGE ME";

	/**
	 * main.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("start");
		try {
			// Twitter4Jを取得
			Twitter twitter = Twitter4jV1Helper.getTwitter4j();

			// ハッシュタグで検索
			System.out.println("searching...");
			QueryResult queryResult = Twitter4jV1Helper.getTimelineByKeyword(twitter, KEYWORD);

			while (true) {
				System.out.println("result count : " + queryResult.getTweets().size());

				// 全てのツイートに対して実行
				for (Status status : queryResult.getTweets()) {
					System.out.println("status : " + status.getText());

					downloadStatusMedia(KEYWORD, status);
				}

				// 次のページが存在する場合
				if (queryResult.hasNext()) {
					// 次のページを取得
					System.out.println("searching next page...");
					queryResult = Twitter4jV1Helper.getTimelineByQuery(twitter, queryResult.nextQuery());
				} else {
					// 終了
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("done");
	}
}