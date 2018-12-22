package mains;

import twitter4j.MediaEntity;
import twitter4j.Status;
import utils.FileHelper;

/**
 * AbstractDownloadTwitterImage.
 *
 * @author cyrus
 */
public class AbstractDownloadTwitterImage {

	/**
	 * ツイートの画像を保存.
	 *
	 * @param keyword
	 * @param status
	 */
	protected static void downloadStatusImage(String keyword, Status status) {
		// 全てのメディアに対して実行
		for (MediaEntity mediaEntity : status.getMediaEntities()) {
			// URLを取得
			String url = mediaEntity.getMediaURLHttps();
			System.out.println("url : " + url);

			// 保存
			FileHelper.saveContent(keyword, url);
		}
	}
}