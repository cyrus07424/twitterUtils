package mains.examples;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import twitter4j.AccountSettings;
import twitter4j.TimeZone;
import twitter4j.Tweet;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;
import twitter4j.User2;
import twitter4j.UsersResponse;
import twitter4j.api.UsersResources;

/**
 * AbstractExample.
 *
 * @author cyrus
 */
public class AbstractExample {

	/**
	 * 認証されたユーザー情報を出力.
	 * 
	 * @param twitter
	 * @throws TwitterException
	 */
	protected static void dumpTwitterUsers(Twitter twitter) throws TwitterException {
		UsersResources usersResources = twitter.users();
		if (usersResources != null) {
			System.out.println("■users: " + usersResources);
			User user = usersResources.verifyCredentials();
			if (user != null) {
				System.out.println("■id: " + user.getId());
				System.out.println("■screenName: " + user.getScreenName());
			}
			AccountSettings accountSettings = usersResources.getAccountSettings();
			if (accountSettings != null) {
				System.out.println("■accountSettings: " + accountSettings);
				TimeZone timeZone = accountSettings.getTimeZone();
				if (timeZone != null) {
					System.out.println("■timezone: " + timeZone);
					String tzInfoName = timeZone.tzinfoName();
					if (StringUtils.isNotBlank(tzInfoName)) {
						System.out.println("■tzinfoName: " + tzInfoName);
					}
				}
			}
		}
	}

	/**
	 * ユーザー情報を出力.
	 * 
	 * @param twitter
	 * @throws TwitterException
	 */
	protected static void dumpUsersResponse(UsersResponse usersResponse) throws TwitterException {
		List<User2> users = usersResponse.getUsers();
		if (users != null) {
			System.out.println("■users: " + users);
			for (User2 user2 : users) {
				if (user2 != null) {
					System.out.println("■id: " + user2.getId());
					System.out.println("■screenName: " + user2.getScreenName());
				}
			}
		}

		// ピン留めポストが存在する場合
		for (Map.Entry<Long, Tweet> tweet : usersResponse.getTweetsMap().entrySet()) {
			System.out.println("■tweet: " + tweet.getValue().getText());
		}
	}
}