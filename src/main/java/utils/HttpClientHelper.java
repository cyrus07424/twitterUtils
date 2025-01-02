package utils;

import java.util.Arrays;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ServiceUnavailableRetryStrategy;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.JsonNode;

import constants.Configurations;

/**
 * Http client helper.
 *
 * @author cyrus
 */
public class HttpClientHelper {

	/**
	 * デバッグモード.
	 */
	private static final boolean DEBUG_MODE = true;

	/**
	 * Retry interval millis.
	 */
	private static final int RETRY_INTERVAL_MILLI_SEC = 60 * 1000;

	/**
	 * Max retry count.
	 */
	private static final int MAX_RETRY_COUNT = 10;

	/**
	 * Http client context.
	 */
	private static final HttpClientContext HTTP_CLIENT_CONTEXT = HttpClientContext.create();

	/**
	 * Get HTTP response.
	 *
	 * @param url
	 * @param referer
	 * @param valueType
	 * @return
	 */
	public static JsonNode getHttpResponse(String url, String referer) {
		try {
			System.out.println("Get : " + url);
			HttpGet httpGet = new HttpGet(url);
			httpGet.setHeader("User-Agent", Configurations.HTTP_CLIENT_USER_AGENT);
			httpGet.setHeader("Referer", referer);
			httpGet.setHeader("X-Requested-With", "XMLHttpRequest");
			httpGet.setHeader("Accept", "*/*");
			httpGet.setHeader("Accept-Language", "en-US,en;q=0.5");
			httpGet.setHeader("Accept-Encoding", "gzip");
			httpGet.setHeader("Pragma", "no-cache");
			httpGet.setHeader("Cache-Control", "no-cache");
			try (CloseableHttpClient client = getHttpClient();
					CloseableHttpResponse response = client.execute(httpGet, HTTP_CLIENT_CONTEXT)) {
				if (DEBUG_MODE) {
					System.out.println(response);
					System.out.println("code = " + response.getStatusLine().getStatusCode());
				}
				String responseString = EntityUtils.toString(response.getEntity());
				if (DEBUG_MODE) {
					System.out.println("response = " + responseString);
				}
				return JsonHelper.getObjectMapper().readTree(responseString);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Get HttpClient.<br>
	 * https://qiita.com/kgyamaryllis/items/531abacdc83f72239332.
	 *
	 * @return
	 */
	public static CloseableHttpClient getHttpClient() {
		return HttpClientBuilder.create()
				.setRetryHandler(new DefaultHttpRequestRetryHandler())
				.setServiceUnavailableRetryStrategy(new ServiceUnavailableRetryStrategy() {

					@Override
					public boolean retryRequest(
							final HttpResponse response, final int executionCount, final HttpContext context) {
						int statusCode = response.getStatusLine().getStatusCode();
						return Arrays.asList(HttpStatus.SC_SERVICE_UNAVAILABLE).contains(statusCode)
								&& executionCount < MAX_RETRY_COUNT;
					}

					@Override
					public long getRetryInterval() {
						return RETRY_INTERVAL_MILLI_SEC;
					}
				})
				.setDefaultCookieStore(HTTP_CLIENT_CONTEXT.getCookieStore())
				.build();
	}
}