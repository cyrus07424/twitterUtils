package mains.examples;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.v1.Status;
import utils.Twitter4jHelper;

/**
 * ユーザーのタイムラインを取得.
 *
 * @author cyrus
 */
public class GetUserTimeline {

	/**
	 * main.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// Twitter4jを取得
			Twitter twitter = Twitter4jHelper.getTwitter4j();

			System.out.println("■start: " + twitter.v1().users().verifyCredentials().getScreenName());
			System.out.println("■timezone: " + twitter.v1().users().getAccountSettings().getTimeZone().tzinfoName());

			// ユーザーのタイムラインを取得
			List<Status> statusList = twitter.v1().timelines().getUserTimeline();
			// 全てのツイートに対して実行
			for (Status status : statusList) {
				System.out.println(status);
				System.out.println(localDateTimeToDate(status.getCreatedAt()));
			}
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}

	/**
	 * LocalDateTimeからDateへ変換.
	 *
	 * @param localDateTime
	 * @return
	 */
	public static Date localDateTimeToDate(LocalDateTime localDateTime) {
		return Date.from(ZonedDateTime.of(localDateTime, ZoneOffset.UTC).toInstant());
	}
}