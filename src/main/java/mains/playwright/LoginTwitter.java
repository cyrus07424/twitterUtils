package mains.playwright;

import java.util.Scanner;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.LoadState;

import utils.PlaywrightHelper;

/**
 * Twitterにログイン.
 *
 * @author cyrus
 */
public class LoginTwitter {

	/**
	 * メイン.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("■start.");

		// Playwrightを作成
		try (Playwright playwright = Playwright.create()) {
			// ブラウザ起動オプションを取得
			BrowserType.LaunchOptions launchOptions = PlaywrightHelper.getLaunchOptions();

			// ブラウザを起動
			try (Browser browser = playwright.chromium().launch(launchOptions)) {
				System.out.println("■launched");

				// ブラウザコンテキストオプションを取得
				Browser.NewContextOptions newContextOptions = PlaywrightHelper.getNewContextOptions(false);

				// ブラウザコンテキストを取得
				try (BrowserContext context = browser.newContext(newContextOptions)) {
					// ページを取得
					try (Page page = context.newPage()) {
						// ログイン画面を表示
						page.navigate("https://x.com/i/flow/login");

						// 読み込み完了まで待機
						page.waitForLoadState(LoadState.NETWORKIDLE);

						// Scanner
						try (Scanner scanner = new Scanner(System.in)) {
							System.out.print("電話番号/メールアドレス/ユーザー名を入力してください: ");
							String email = scanner.nextLine();

							// 電話番号/メールアドレス/ユーザー名を入力
							page.locator("input[name='text']").fill(email);

							// 次へボタンをクリック
							page.locator(
									"#layers > div:nth-child(2) > div > div > div > div > div > div.css-175oi2r.r-1ny4l3l.r-18u37iz.r-1pi2tsx.r-1777fci.r-1xcajam.r-ipm5af.r-g6jmlv.r-1awozwy > div.css-175oi2r.r-1wbh5a2.r-htvplk.r-1udh08x.r-1867qdf.r-kwpbio.r-rsyp9y.r-1pjcn9w.r-1279nm1 > div > div > div.css-175oi2r.r-1ny4l3l.r-6koalj.r-16y2uox.r-kemksi.r-1wbh5a2 > div.css-175oi2r.r-16y2uox.r-1wbh5a2.r-f8sm7e.r-13qz1uu.r-1ye8kvj > div > div > div > button:nth-child(6)")
									.click();

							try {
								// FIXME
								Thread.sleep(5000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}

							// 読み込み完了まで待機
							page.waitForLoadState(LoadState.NETWORKIDLE);

							System.out.print("パスワードを入力してください: ");
							String password = scanner.nextLine();

							// パスワードを入力
							page.locator("input[name='password']").fill(password);

							// ログインボタンをクリック
							page.locator(
									"#layers > div:nth-child(2) > div > div > div > div > div > div.css-175oi2r.r-1ny4l3l.r-18u37iz.r-1pi2tsx.r-1777fci.r-1xcajam.r-ipm5af.r-g6jmlv.r-1awozwy > div.css-175oi2r.r-1wbh5a2.r-htvplk.r-1udh08x.r-1867qdf.r-kwpbio.r-rsyp9y.r-1pjcn9w.r-1279nm1 > div > div > div.css-175oi2r.r-1ny4l3l.r-6koalj.r-16y2uox.r-kemksi.r-1wbh5a2 > div.css-175oi2r.r-16y2uox.r-1wbh5a2.r-f8sm7e.r-13qz1uu.r-1ye8kvj > div.css-175oi2r.r-1f0wa7y > div > div.css-175oi2r > div > div > button")
									.click();

							try {
								// FIXME
								Thread.sleep(5000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}

							// 読み込み完了まで待機
							page.waitForLoadState(LoadState.NETWORKIDLE);

							try {
								System.out.print("認証コードを入力してください: ");
								String code = scanner.nextLine();

								// 認証コードを入力
								page.locator("input[name='text']").fill(code);

								// 次へボタンをクリック
								page.locator(
										"#layers > div:nth-child(2) > div > div > div > div > div > div.css-175oi2r.r-1ny4l3l.r-18u37iz.r-1pi2tsx.r-1777fci.r-1xcajam.r-ipm5af.r-g6jmlv.r-1awozwy > div.css-175oi2r.r-1wbh5a2.r-htvplk.r-1udh08x.r-1867qdf.r-kwpbio.r-rsyp9y.r-1pjcn9w.r-1279nm1 > div > div > div.css-175oi2r.r-1ny4l3l.r-6koalj.r-16y2uox.r-kemksi.r-1wbh5a2 > div.css-175oi2r.r-16y2uox.r-1wbh5a2.r-f8sm7e.r-13qz1uu.r-1ye8kvj > div.css-175oi2r.r-1f0wa7y > div > div > div > button")
										.click();

								try {
									// FIXME
									Thread.sleep(5000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}

								// 読み込み完了まで待機
								page.waitForLoadState(LoadState.NETWORKIDLE);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					} finally {
						// コンテキストのステートを出力
						PlaywrightHelper.storageState(context);
					}
				}
			}
		} finally {
			System.out.println("■done.");
		}
	}
}