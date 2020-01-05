package mains;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.User;
import utils.Twitter4jHelper;

/**
 * DownloadTwitterMediaByScreenName.
 *
 * @author cyrus
 */
public class DownloadTwitterMediaByScreenName extends AbstractDownloadTwitterImage {

	/**
	 * 検索対象のスクリーン名.
	 */
	private static String SCREEN_NAME = "CHANGE ME";

	/**
	 * main.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("start");
		try {
			// スクリーン名で検索
			System.out.println("searching...");
			User user = Twitter4jHelper.getUserByScreenName(SCREEN_NAME);
			ResponseList<Status> responseList = Twitter4jHelper.getTimelineByUserId(user.getId(), null);

			long minId = Long.MAX_VALUE;
			while (true) {
				System.out.println("result count : " + responseList.size());

				// 全てのツイートに対して実行
				for (Status status : responseList) {
					System.out.println("status : " + status.getText());

					// リツイートではない場合
					if (!status.isRetweet()) {
						// メディアをダウンロード
						downloadStatusMedia(SCREEN_NAME, status);
					}

					if (status.getId() < minId) {
						minId = status.getId();
					}
				}

				// 次のページが存在する場合
				if (!responseList.isEmpty()) {
					// 次のページを取得
					System.out.println("searching next page...");
					responseList = Twitter4jHelper.getTimelineByUserId(user.getId(), minId - 1);
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