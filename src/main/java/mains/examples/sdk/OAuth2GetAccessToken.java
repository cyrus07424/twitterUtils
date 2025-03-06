package mains.examples.sdk;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.pkce.PKCE;
import com.github.scribejava.core.pkce.PKCECodeChallengeMethod;
import com.twitter.clientlib.ApiException;
import com.twitter.clientlib.TwitterCredentialsOAuth2;
import com.twitter.clientlib.api.TwitterApi;
import com.twitter.clientlib.auth.TwitterOAuth20Service;
import com.twitter.clientlib.model.Get2TweetsIdResponse;
import com.twitter.clientlib.model.ResourceUnauthorizedProblem;

import constants.Configurations;
import mains.examples.AbstractExample;

/**
 * OAuth2GetAccessToken.
 *
 * @author cyrus
 */
public class OAuth2GetAccessToken extends AbstractExample {

	/**
	 * main.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		TwitterCredentialsOAuth2 credentials = new TwitterCredentialsOAuth2(Configurations.TWITTER_CLIENT_ID,
				Configurations.TWITTER_CLIENT_SECRET,
				System.getenv("TWITTER_OAUTH2_ACCESS_TOKEN"),
				System.getenv("TWITTER_OAUTH2_REFRESH_TOKEN"));

		OAuth2AccessToken accessToken = getAccessToken(credentials);
		if (accessToken == null) {
			return;
		}

		// Setting the access & refresh tokens into TwitterCredentialsOAuth2
		credentials.setTwitterOauth2AccessToken(accessToken.getAccessToken());
		credentials.setTwitterOauth2RefreshToken(accessToken.getRefreshToken());
		callApi(credentials);
	}

	/**
	 * getAccessToken.
	 * 
	 * @param credentials
	 * @return
	 */
	public static OAuth2AccessToken getAccessToken(TwitterCredentialsOAuth2 credentials) {
		TwitterOAuth20Service service = new TwitterOAuth20Service(
				credentials.getTwitterOauth2ClientId(),
				credentials.getTwitterOAuth2ClientSecret(),
				"https://twitter.com",
				"offline.access tweet.read users.read");

		OAuth2AccessToken accessToken = null;
		try {
			final Scanner in = new Scanner(System.in, "UTF-8");
			System.out.println("Fetching the Authorization URL...");

			final String secretState = "state";
			PKCE pkce = new PKCE();
			pkce.setCodeChallenge("challenge");
			pkce.setCodeChallengeMethod(PKCECodeChallengeMethod.PLAIN);
			pkce.setCodeVerifier("challenge");
			String authorizationUrl = service.getAuthorizationUrl(pkce, secretState);

			System.out.println("Go to the Authorization URL and authorize your App:\n" +
					authorizationUrl + "\nAfter that paste the authorization code here\n>>");
			final String code = in.nextLine();
			System.out.println("\nTrading the Authorization Code for an Access Token...");
			accessToken = service.getAccessToken(pkce, code);

			System.out.println("Access token: " + accessToken.getAccessToken());
			System.out.println("Refresh token: " + accessToken.getRefreshToken());
		} catch (Exception e) {
			System.err.println("Error while getting the access token:\n " + e);
			e.printStackTrace();
		}
		return accessToken;
	}

	/**
	 * callApi.
	 * 
	 * @param credentials
	 */
	public static void callApi(TwitterCredentialsOAuth2 credentials) {
		TwitterApi apiInstance = new TwitterApi(credentials);

		Set<String> tweetFields = new HashSet<>();
		tweetFields.add("author_id");
		tweetFields.add("id");
		tweetFields.add("created_at");

		try {
			// findTweetById
			Get2TweetsIdResponse result = apiInstance.tweets().findTweetById("20")
					.tweetFields(tweetFields)
					.execute();
			if (result.getErrors() != null && result.getErrors().size() > 0) {
				System.out.println("Error:");
				result.getErrors().forEach(e -> {
					System.out.println(e.toString());
					if (e instanceof ResourceUnauthorizedProblem) {
						System.out.println(
								((ResourceUnauthorizedProblem) e).getTitle() + " "
										+ ((ResourceUnauthorizedProblem) e).getDetail());
					}
				});
			} else {
				System.out.println("findTweetById - Tweet Text: " + result.toString());
			}
		} catch (ApiException e) {
			System.err.println("Status code: " + e.getCode());
			System.err.println("Reason: " + e.getResponseBody());
			System.err.println("Response headers: " + e.getResponseHeaders());
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}