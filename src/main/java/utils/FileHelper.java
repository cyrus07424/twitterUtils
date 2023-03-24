package utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

/**
 * ファイルヘルパー.
 *
 * @author cyrus
 */
public class FileHelper {

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
		try {
			// 接続
			URL url = new URL(urlString);
			URLConnection urlConnection = url.openConnection();

			// 保存先ファイルを作成
			String fileName = FilenameUtils.getName(url.getPath());
			File destinationFile = new File(String.format("./downloads/%s/%s", prefix, fileName));
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