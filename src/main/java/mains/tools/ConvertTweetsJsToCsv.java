package mains.tools;

import java.io.File;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orangesignal.csv.Csv;
import com.orangesignal.csv.CsvConfig;
import com.orangesignal.csv.handlers.StringArrayListHandler;

/**
 * tweets.jsからCSVファイルに変換.
 *
 * @author cyrus
 */
public class ConvertTweetsJsToCsv {

	/**
	 * 入力ファイル(tweets.js).<br>
	 * 先頭の「window.YTD.tweet.part0 = 」の部分は削除しておく.
	 */
	private static File INPUT_TWEET_JS_FILE = new File("data/tweets.js");

	/**
	 * 出力ファイル(CSV).
	 */
	private static File OUTPUT_CSV_FILE = new File("data/tweets.csv");

	/**
	 * main.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// CSVファイルを作成
			CsvConfig csvConfig = new CsvConfig();
			csvConfig.setQuoteDisabled(false);
			csvConfig.setEscapeDisabled(false);

			// 行一覧
			List<String[]> csvRowList = new ArrayList<>();

			// ヘッダー行を作成
			List<String> headerRow = new ArrayList<>();
			headerRow.add("id");
			headerRow.add("date");
			headerRow.add("full_text");

			// 入力ファイルをJsonオブジェクトに変換
			JsonNode tweetList = getObjectMapper().readTree(INPUT_TWEET_JS_FILE);

			// 全ての要素に対して実行
			for (JsonNode tweet : tweetList) {
				// 要素内の情報を取得
				JsonNode tweetBody = tweet.get("tweet");
				String id = tweetBody.get("id").asText();
				String date = tweetBody.get("created_at").asText();
				String text = tweetBody.get("full_text").asText().replaceAll("[\r\n]", "");

				// データ行を作成
				List<String> csvRow = new ArrayList<>();
				csvRow.add(id);
				csvRow.add(date);
				csvRow.add(text);
				System.out.println(id);

				// データ行を追加
				csvRowList.add(csvRow.toArray(new String[0]));
			}

			// データ行をtweetIdの昇順でソート
			Collections.sort(csvRowList, (r1, r2) -> new BigDecimal(r1[0]).compareTo(new BigDecimal(r2[0])));

			// ヘッダー行を先頭に追加
			csvRowList.add(0, headerRow.toArray(new String[0]));

			// CSVファイルを保存
			Csv.save(csvRowList, OUTPUT_CSV_FILE, StandardCharsets.UTF_8.name(), csvConfig,
					new StringArrayListHandler());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ObjectMapperを取得.
	 *
	 * @return
	 */
	private static ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}
}