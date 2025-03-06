package mains.imageDownloader;

import java.net.URISyntaxException;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;

import twitter4j.Media;
import twitter4j.Media.Variant;
import twitter4j.MediaEntity;
import twitter4j.MediaKey;
import twitter4j.Status;
import twitter4j.Tweet;
import twitter4j.TweetsResponse;
import utils.FileHelper;

/**
 * AbstractDownloadTwitterImage.
 *
 * @author cyrus
 */
public abstract class AbstractDownloadTwitterImage {

	/**
	 * ツイートのメディアを保存(V1).
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

	/**
	 * ツイートのメディアを保存(V2).
	 *
	 * @param prefix
	 * @param tweetsResponse
	 * @param tweet
	 * @throws URISyntaxException 
	 */
	protected static void downloadTweetMedia(String prefix, TweetsResponse tweetsResponse, Tweet tweet)
			throws URISyntaxException {
		if (tweet.getMediaKeys() != null) {
			// 全てのメディアキーに対して実行
			for (MediaKey mediaKey : tweet.getMediaKeys()) {
				// メディアを取得
				Media media = tweetsResponse.getMediaMap().get(mediaKey);

				// メディアタイプによって分岐
				switch (media.getType()) {
				case Photo: {
					Media.Photo photo = media.getAsPhoto();

					// URLを取得
					String url = photo.getUrl();
					System.out.println("url : " + url);

					// フルサイズ画像のURLを作成
					String fixedUrl = new URIBuilder(url).setParameter("name", "orig").build().toString();
					System.out.println("fixedUrl: " + fixedUrl);

					// 保存
					FileHelper.saveContent(prefix, fixedUrl);
					break;
				}
				case Video: {
					Media.Video video = media.getAsVideo();

					// 使用する動画を取得
					int maxBitrate = 0;
					Variant useVariant = null;
					for (Variant variant : video.getVariants()) {
						if (StringUtils.equals(variant.getContentType(), "video/mp4")) {
							if (maxBitrate < variant.getBitRate()) {
								useVariant = variant;
							}
						}
					}
					if (useVariant == null) {
						useVariant = video.getVariants()[video.getVariants().length - 1];
					}

					// URLを取得
					String url = useVariant.getUrl();
					System.out.println("url : " + url);

					// 保存
					FileHelper.saveContent(prefix, url);
					break;
				}
				case AnimatedGif: {
					Media.AnimatedGif animatedGif = media.getAsAnimatedGif();

					// URLを取得
					String url = animatedGif.getPreviewImageUrl();
					System.out.println("url : " + url);

					// TODO フルサイズ画像のURLを作成

					// 保存
					FileHelper.saveContent(prefix, url);
					break;
				}
				default:
					break;
				}
			}
		}
	}
}