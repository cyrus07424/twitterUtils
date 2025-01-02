package utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * ファイルヘルパー.
 *
 * @author cyrus
 */
public class FileHelper {

	/**
	 * 最大リトライ回数.
	 */
	private static final int MAX_RETRY_COUNT = 5;

	/**
	 * 一時ファイルを作成して取得.
	 *
	 * @return
	 * @throws IOException
	 */
	public static File getNewTemporaryFile() throws IOException {
		return File.createTempFile("app-", "temp");
	}

	/**
	 * URLのデータを保存.
	 *
	 * @param prefix
	 * @param urlString
	 */
	public static void saveContent(String prefix, String urlString) {
		saveContent(prefix, urlString, new File(String.format("./downloads")));
	}

	/**
	 * URLのデータを保存.
	 *
	 * @param prefix
	 * @param urlString
	 * @param destinationDirectory
	 */
	public static void saveContent(String prefix, String urlString, File destinationDirectory) {
		int retryCount = 0;
		while (retryCount < MAX_RETRY_COUNT) {
			try {
				// 接続
				URL url = new URL(urlString);
				URLConnection urlConnection = url.openConnection();

				// 保存先ファイルを作成
				String fileName = FilenameUtils.getName(url.getPath());
				if (StringUtils.isBlank(FilenameUtils.getExtension(fileName))) {
					fileName = String.format("%s.%s", fileName,
							getExtentionByContentType(urlConnection.getContentType()));
				}
				File destinationFile = new File(
						String.format("%s/%s/%s", destinationDirectory.getPath(), prefix, fileName));
				if (!destinationFile.exists()) {
					FileUtils.createParentDirectories(destinationFile);

					// データを保存
					FileUtils.copyToFile(urlConnection.getInputStream(), destinationFile);

					// 更新日時を設定
					if (urlConnection.getLastModified() != 0) {
						destinationFile.setLastModified(urlConnection.getLastModified());
					}
				}
				break;
			} catch (Exception e) {
				e.printStackTrace();
				retryCount++;
			}
		}
	}

	/**
	 * コンテンツタイプから拡張子を取得.
	 * @param contentType
	 * @return
	 */
	private static String getExtentionByContentType(String contentType) {
		// FIXME
		switch (contentType) {
		case "image/jpeg":
			return "jpg";
		case "image/png":
			return "png";
		default:
			return "ext";
		}
	}
}