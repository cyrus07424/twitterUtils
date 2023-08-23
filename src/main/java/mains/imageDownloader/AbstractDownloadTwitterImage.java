package mains.imageDownloader;

import org.apache.commons.lang3.StringUtils;

import twitter4j.MediaEntity;
import twitter4j.Status;
import utils.FileHelper;

/**
 * AbstractDownloadTwitterImage.
 *
 * @author cyrus
 */
public abstract class AbstractDownloadTwitterImage {

	/**
	 * ツイートのメディアを保存.
	 *
	 * @param prefix
	 * @param status
	 */
	protected static void downloadStatusMedia(String prefix, Status status) {
		// 全てのメディアに対して実行
		for (MediaEntity mediaEntity : status.getMediaEntities()) {
			// 動画の場合
			if (mediaEntity.getVideoVariants() != null && 0 < mediaEntity.getVideoVariants().length) {
				// 使用する動画を取得
				int maxBitrate = 0;
				MediaEntity.Variant useVariant = null;
				for (MediaEntity.Variant variant : mediaEntity.getVideoVariants()) {
					if (StringUtils.equals(variant.getContentType(), "video/mp4")) {
						if (maxBitrate < variant.getBitrate()) {
							useVariant = variant;
						}
					}
				}
				if (useVariant == null) {
					useVariant = mediaEntity.getVideoVariants()[mediaEntity.getVideoVariants().length - 1];
				}

				// URLを取得
				String url = useVariant.getUrl();
				System.out.println("url : " + url);

				// 保存
				FileHelper.saveContent(prefix, url);
			} else {
				// URLを取得
				String url = mediaEntity.getMediaURLHttps();
				System.out.println("url : " + url);

				// 保存
				FileHelper.saveContent(prefix, url);
			}
		}
	}
}