package constants;

import java.io.File;
import java.nio.file.Path;

/**
 * 環境設定.
 *
 * @author cyrus
 */
public interface Configurations {

	/**
	 * Twitter oAuth設定.
	 */
	String TWITTER_CONSUMER_KEY = "CHANGE ME";

	String TWITTER_CONSUMER_SECRET = "CHANGE ME";

	String TWITTER_ACCESS_TOKEN = "CHANGE ME";

	String TWITTER_ACCESS_TOKEN_SECRET = "CHANGE ME";

	/**
	 * ブラウザをヘッドレスモードで使用するか.
	 */
	boolean USE_HEADLESS_MODE = false;

	/**
	 * ブラウザのステートの出力先.
	 */
	Path STATE_PATH = new File("data/state.json").toPath();

	/**
	 * User Agent.
	 */
	String HTTP_CLIENT_USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36";
}