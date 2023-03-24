package mains.tools;

import java.io.File;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orangesignal.csv.Csv;
import com.orangesignal.csv.CsvConfig;
import com.orangesignal.csv.handlers.StringArrayListHandler;

/**
 * like.jsからCSVファイルに変換.
 *
 * @author cyrus
 */
public class ConvertLikeJsToCsv {

	/**
	 * 入力ファイル(like.js).<br>
	 * 先頭の「window.YTD.like.part0 = 」の部分は削除しておく.
	 */
	private static File INPUT_LIKE_JS_FILE = new File("data/like.js");

	/**
	 * 出力ファイル(CSV).
	 */
	private static File OUTPUT_CSV_FILE = new File("data/like.csv");

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
			headerRow.add("tweetId");
			headerRow.add("fullText");
			headerRow.add("expandedUrl");

			// 入力ファイルをJsonオブジェクトに変換
			JsonNode likeList = getObjectMapper().readTree(INPUT_LIKE_JS_FILE);

			// 全ての要素に対して実行
			for (JsonNode like : likeList) {
				// 要素内の情報を取得
				JsonNode likeBody = like.get("like");
				String id = likeBody.get("tweetId").asText();
				String text = null;
				if (likeBody.has("fullText")) {
					text = likeBody.get("fullText").asText();
					text = StringUtils.deleteWhitespace(text);
				}
				String expandedUrl = likeBody.get("expandedUrl").asText();

				// データ行を作成
				List<String> csvRow = new ArrayList<>();
				csvRow.add(id);
				csvRow.add(text);
				csvRow.add(expandedUrl);
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