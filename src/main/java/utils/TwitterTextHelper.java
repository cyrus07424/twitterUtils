package utils;

import java.util.HashSet;
import java.util.Set;

import com.twitter.twittertext.Extractor;

/**
 * Twitter Textヘルパー.
 *
 * @author cyrus
 */
public class TwitterTextHelper {

	/**
	 * 文字列からハッシュタグの一覧を取得(Twitter用).
	 *
	 * @param text
	 * @return ハッシュタグの一覧(# 無しの文字列)
	 */
	public static Set<String> getHashtagSetForTwitter(String text) {
		return new HashSet<>(new Extractor().extractHashtags(text));
	}
}