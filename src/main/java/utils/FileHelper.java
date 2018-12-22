package utils;

import java.io.File;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.security.MessageDigest;

import org.apache.commons.io.FileUtils;

/**
 * ファイルヘルパー.
 *
 * @author cyrus
 */
public class FileHelper {

	/**
	 * URLのデータを保存.
	 *
	 * @param prefix
	 * @param url
	 */
	public static void saveContent(String prefix, String url) {
		try {
			// 接続
			URLConnection urlConnection = new URL(url).openConnection();
			String contentType = urlConnection.getContentType();
			String extention;
			switch (contentType) {
				case "image/jpeg":
					extention = "jpg";
					break;
				case "image/png":
					extention = "png";
					break;
				case "image/gif":
					extention = "gif";
					break;
				default:
					extention = "ext";
					break;
			}
			// 保存先ファイルを作成
			String fileName = String.format("%040x",
					new BigInteger(1, MessageDigest.getInstance("SHA-1").digest(url.getBytes())));
			File destinationFile = new File(
					String.format("./downloads/%s/%s.%s", prefix, fileName, extention));
			if (!destinationFile.exists()) {
				Files.createDirectories(destinationFile.getParentFile().toPath());

				// データを保存
				FileUtils.copyToFile(urlConnection.getInputStream(), destinationFile);

				// 更新日時を設定
				if (urlConnection.getLastModified() != 0) {
					destinationFile.setLastModified(urlConnection.getLastModified());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}