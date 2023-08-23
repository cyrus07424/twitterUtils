package mains.removeTweets;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.io.FilenameUtils;

import com.orangesignal.csv.Csv;
import com.orangesignal.csv.handlers.StringArrayListHandler;

import twitter4j.Twitter;
import utils.FileHelper;
import utils.OrangeSignalHelper;
import utils.Twitter4jV1Helper;

/**
 * CSVファイルから全てのツイートを削除.
 *
 * @author cyrus
 */
public class RemoveAllTweetsFromCsv {

	/**
	 * CSVファイル.
	 */
	private static File CSV_FILE = new File("data/tweets.csv");

	/**
	 * main.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// Twitter4jを取得
			Twitter twitter = Twitter4jV1Helper.getTwitter4j();

			System.out.println("■start: " + twitter.users().verifyCredentials().getScreenName());

			while (true) {
				// CSVファイルから全行読み込む
				List<String[]> rowList = Csv.load(CSV_FILE, OrangeSignalHelper.getCsvConfig(),
						new StringArrayListHandler());
				System.out.println("rowList size : " + rowList.size());

				// データが存在しない場合
				if (rowList.isEmpty()) {
					// 終了
					return;
				}

				try {
					// 最後の行を取得
					String[] row = rowList.get(rowList.size() - 1);

					// ツイートIDを取得
					long tweetId = Long.parseLong(row[0]);

					// ツイートを削除
					System.out.println("destroyStatus : " + tweetId);
					twitter.tweets().destroyStatus(tweetId);
				} catch (Exception e) {
					e.printStackTrace();
				}

				// 最後の行を削除
				rowList.remove(rowList.size() - 1);

				// 新しいCSVファイルへ保存
				File newCsvFile = FileHelper.getNewTemporaryFile();
				Csv.save(rowList, newCsvFile, StandardCharsets.UTF_8.name(), OrangeSignalHelper.getCsvConfig(),
						new StringArrayListHandler());

				// ファイルを移動(1)
				CSV_FILE.renameTo(getTemrporaryFile());

				// ファイルを移動(2)
				newCsvFile.renameTo(CSV_FILE);

				// ファイルを削除
				getTemrporaryFile().delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 一時ファイル名を取得.
	 *
	 * @return
	 */
	private static File getTemrporaryFile() {
		String baseName = FilenameUtils.getBaseName(CSV_FILE.getName());
		String extension = FilenameUtils.getExtension(CSV_FILE.getName());
		return new File(CSV_FILE.getParentFile(), String.format("%s_temp.%s", baseName, extension));
	}
}