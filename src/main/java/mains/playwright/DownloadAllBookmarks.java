package mains.playwright;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.LoadState;

import utils.FileHelper;
import utils.HttpClientHelper;
import utils.PlaywrightHelper;

/**
 * ブックマークの全てのポストをダウンロード.
 *
 * @author cyrus
 */
public class DownloadAllBookmarks {

	/**
	 * デバッグモード.
	 */
	private static final boolean DEBUG_MODE = true;

	/**
	 * 処理を実行したブックマークを削除するかどうか.
	 */
	private static final boolean REMOVE_PROCESSED_BOOKMARK = true;

	/**
	 * 無限ループモード.
	 */
	private static final boolean INFINITY_LOOP = true;

	/**
	 * 出力先Excelファイル.
	 */
	private static final File DATA_FILE = new File("data/DownloadAllBookmarks.xlsx");

	/**
	 * 画像ダウンロード先ディレクトリ.
	 */
	private static final File DOWNLOAD_DIRECTORY = new File("CHANGE ME");

	/**
	 * メイン.
	 *
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("■start.");

		// 出力先Excelファイルが存在しない場合
		if (!DATA_FILE.exists()) {
			// ディレクトリを作成
			FileUtils.createParentDirectories(DATA_FILE);

			// ブックを作成
			try (Workbook workbook = new SXSSFWorkbook()) {
				// シートを作成
				Sheet sheet = workbook.createSheet();
				if (sheet instanceof SXSSFSheet) {
					((SXSSFSheet) sheet).trackAllColumnsForAutoSizing();
				}

				// ファイル出力
				try (FileOutputStream fileOutputStream = new FileOutputStream(DATA_FILE)) {
					workbook.write(fileOutputStream);
				}
			}
		}

		// ブックを読み込み
		try (Workbook workbook = WorkbookFactory.create(new FileInputStream(DATA_FILE))) {
			// シートを取得
			Sheet sheet = workbook.getSheetAt(0);

			// Playwrightを作成
			try (Playwright playwright = Playwright.create()) {
				// ブラウザ起動オプションを取得
				BrowserType.LaunchOptions launchOptions = PlaywrightHelper.getLaunchOptions();

				// ブラウザを起動
				try (Browser browser = playwright.chromium().launch(launchOptions)) {
					System.out.println("■launched");

					// ブラウザコンテキストオプションを取得
					Browser.NewContextOptions newContextOptions = PlaywrightHelper.getNewContextOptions(true);

					// ブラウザコンテキストを取得
					try (BrowserContext context = browser.newContext(newContextOptions)) {
						// ページを取得
						try (Page page = context.newPage()) {
							// FIXME ページを設定
							page.onDialog(dialog -> {
								dialog.dismiss();
							});

							// ブックマーク一覧画面を表示
							page.navigate("https://x.com/i/bookmarks");

							// 処理済みの投稿数
							int processedPostCount;
							LOOP1: while (true) {
								processedPostCount = 0;
								try {
									// FIXME
									Thread.sleep(5000);

									// 一番上までスクロール
									page.evaluate("window.scrollTo(0, 0);");
								} catch (Exception e) {
									e.printStackTrace();
								} finally {
									// 読み込み完了まで待機
									page.waitForLoadState(LoadState.LOAD);
								}

								// ブックマーク検索欄を取得
								Locator searchForm = page.locator("input[placeholder='ブックマークを検索']");

								// 全てのブックマークを取得
								List<Locator> articleList = page.locator("article").all();
								if (DEBUG_MODE) {
									System.out.println("articleList count: " + articleList.size());
								}
								while (articleList.isEmpty()) {
									try {
										// ブックマークの検索をクリア
										searchForm.clear();

										// FIXME ランダムな文字でブックマークを検索
										switch (ThreadLocalRandom.current().nextInt(10)) {
										case 0:
											searchForm.fill(RandomStringUtils.insecure().nextAscii(1));
											break;
										case 1:
											searchForm.fill(RandomStringUtils.insecure().nextGraph(1));
											break;
										case 2:
											searchForm.fill(RandomStringUtils.insecure().nextPrint(1));
											break;
										case 3:
											searchForm.fill(RandomStringUtils.insecure()
													.nextAlphabetic(ThreadLocalRandom.current().nextInt(5)));
											break;
										case 4:
											searchForm.fill(getRandomHiragana());
											break;
										case 5:
											searchForm.fill("lang:ja");
											break;
										case 6:
											searchForm.fill(getRandomDirectoryName());
											break;
										default:
											searchForm.fill(RandomStringUtils.insecure().next(1));
											break;
										}

										// FIXME
										Thread.sleep(5000);
									} catch (Exception e) {
										e.printStackTrace();
									} finally {
										// 読み込み完了まで待機
										page.waitForLoadState(LoadState.LOAD);

										// 全てのブックマークを取得
										articleList = page.locator("article").all();
									}
								}

								// 全てのブックマークの順序を反転
								Collections.reverse(articleList);

								// 全てのブックマークに対して実行
								LOOP2: for (Locator post : articleList) {
									try {
										// 行を作成
										Row row = sheet.createRow(sheet.getLastRowNum() + 1);

										// ブックマークの投稿者を取得
										Locator userName = post.locator("div[data-testid='User-Name']");
										List<Locator> aList = userName.locator("a[role='link']").all();

										// 投稿者名を取得
										String userFullName = aList.get(0).textContent();
										if (DEBUG_MODE) {
											System.out.println("userFullName: " + userFullName);
										}
										Cell userFullNameCell = row.createCell(0);
										userFullNameCell.setCellValue(userFullName);

										// 投稿者のスクリーン名を取得
										String userScreenName = aList.get(1).textContent();
										userScreenName = StringUtils.remove(userScreenName, '@');
										if (DEBUG_MODE) {
											System.out.println("userScreenName: " + userScreenName);
										}
										Cell userScreenNameCell = row.createCell(1);
										userScreenNameCell.setCellValue(userScreenName);

										// 投稿のURLを取得
										String postUrl = aList.get(2).getAttribute("href");
										if (DEBUG_MODE) {
											System.out.println("postUrl: " + postUrl);
										}
										Cell postUrlCell = row.createCell(2);
										postUrlCell.setCellValue(postUrl);

										// ブックマークの本文を取得
										Locator tweetText = post.locator("div[data-testid='tweetText']");
										if (0 < tweetText.count()) {
											String tweetTextContent = tweetText.first().textContent();
											if (DEBUG_MODE) {
												System.out.println("tweetTextContent: " + tweetTextContent);
											}
											Cell tweetTextCell = row.createCell(3);
											tweetTextCell.setCellValue(tweetTextContent);
										}

										// ダウンロード済みフラグ
										boolean downloaded = false;

										// 投稿のメディアを取得
										Locator tweetPhoto = post.locator("div[data-testid='tweetPhoto']");

										// メディア内に動画が存在する場合
										if (0 < tweetPhoto.locator("div[data-testid='videoComponent']").count()) {
											// 投稿のIDを取得
											String statusId = getStatusId(postUrl);

											// JSON API?のURLを作成
											String apiUrl = String.format(
													"https://cdn.syndication.twimg.com/tweet-result?id=%s&token=x",
													statusId);
											if (DEBUG_MODE) {
												System.out.println("apiUrl: " + apiUrl);
											}

											// FIXME リファラURLを作成
											String refererUrl = String.format("https://x.com%s", postUrl);

											// JSON APIを呼び出し
											JsonNode jsonNode = HttpClientHelper.getHttpResponse(apiUrl, refererUrl);
											if (DEBUG_MODE) {
												System.out.println("jsonNode: " + jsonNode);
											}

											// 動画のURLを抽出
											if (jsonNode.has("video")) {
												JsonNode video = jsonNode.get("video");
												if (video.has("variants")) {
													ArrayNode videoVariants = (ArrayNode) video.get("variants");
													JsonNode lastVideoVariant = videoVariants
															.get(videoVariants.size() - 1);
													String videoUrl = lastVideoVariant.get("src").asText();
													if (DEBUG_MODE) {
														System.out.println("videoUrl: " + videoUrl);
													}

													// 画像をダウンロード
													FileHelper.saveContent(userScreenName, videoUrl,
															DOWNLOAD_DIRECTORY);

													// ダウンロード済みフラグを変更
													downloaded = true;
												}
											} else {
												if (jsonNode.has("tombstone")) {
													JsonNode tombstone = jsonNode.get("tombstone");
													if (tombstone.has("text")) {
														JsonNode tombstoneText = tombstone.get("text");
														if (tombstoneText.has("text")) {
															String tombstoneTextText = tombstoneText.get("text")
																	.asText();
															if (StringUtils.contains(tombstoneTextText,
																	"Age-restricted adult content")) {
																// FIXME ダウンロード済み扱いにする
																downloaded = true;
															}
														}
													}
												}
											}
										}

										// メディア内の全ての画像に対して実行
										for (Locator img : tweetPhoto.locator("img").all()) {
											// 画像のURLを取得
											String src = img.getAttribute("src");
											if (DEBUG_MODE) {
												System.out.println("originalSrc: " + src);
											}

											// 画像のURLのクエリパラメータを修正
											String fixedSrc = new URIBuilder(src).setParameter("name", "orig").build()
													.toString();
											if (DEBUG_MODE) {
												System.out.println("fixedSrc: " + fixedSrc);
											}

											// 画像をダウンロード
											FileHelper.saveContent(userScreenName, fixedSrc, DOWNLOAD_DIRECTORY);

											// ダウンロード済みフラグを変更
											downloaded = true;
										}

										// 画像がダウンロード済みの場合
										if (downloaded) {
											// ブックマークから削除
											if (REMOVE_PROCESSED_BOOKMARK) {
												Locator removeBookmarkLocator = post
														.locator("button[data-testid='removeBookmark']");
												removeBookmarkLocator.scrollIntoViewIfNeeded();
												removeBookmarkLocator.click();
											}

											processedPostCount++;
										}
									} catch (Exception e) {
										e.printStackTrace();
									} finally {
										try {
											// FIXME
											Thread.sleep(1000);
										} catch (Exception e) {
											e.printStackTrace();
										}
									}
								}

								if (!INFINITY_LOOP && processedPostCount == 0) {
									break;
								} else {
									try {
										// ブックマークの検索をクリア
										searchForm.clear();

										// FIXME
										Thread.sleep(5000);
									} catch (Exception e) {
										e.printStackTrace();
									} finally {
										// 読み込み完了まで待機
										page.waitForLoadState(LoadState.LOAD);
									}
								}
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 列幅の自動調整
			for (int i = 0; i <= 4; i++) {
				sheet.autoSizeColumn(i);
			}

			// ファイル出力
			try (FileOutputStream fileOutputStream = new FileOutputStream(DATA_FILE)) {
				workbook.write(fileOutputStream);
			}
		}

		System.out.println("■done.");
	}

	/**
	 * 投稿のURLから投稿のIDを取得.
	 * 
	 * @param postUrl
	 * @return
	 */
	private static String getStatusId(String postUrl) {
		// FIXME
		for (String temp : postUrl.split("/")) {
			if (StringUtils.isNumeric(temp)) {
				return temp;
			}
		}
		return null;
	}

	/**
	 * 画像ダウンロード先ディレクトリからランダムにサブディレクトリ名を取得.
	 * @return
	 */
	private static String getRandomDirectoryName() {
		return DOWNLOAD_DIRECTORY.listFiles()[ThreadLocalRandom.current()
				.nextInt(DOWNLOAD_DIRECTORY.listFiles().length)].getName();
	}

	/**
	 * ランダムなひらがな1文字を取得.
	 * @return
	 */
	private static String getRandomHiragana() {
		char start = 'ぁ';
		char end = 'ゖ';
		Random random = new Random();
		return String.valueOf((char) (start + random.nextInt(end - start + 1)));
	}
}