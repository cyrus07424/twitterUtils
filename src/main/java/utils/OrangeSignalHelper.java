package utils;

import com.orangesignal.csv.CsvConfig;

/**
 * OrangeSignalヘルパー.
 *
 * @author cyrus
 */
public class OrangeSignalHelper {

	/**
	 * CsvConfigを取得.
	 *
	 * @return
	 */
	public static CsvConfig getCsvConfig() {
		CsvConfig csvConfig = new CsvConfig();
		csvConfig.setQuoteDisabled(false);
		csvConfig.setEscapeDisabled(false);
		csvConfig.setIgnoreEmptyLines(true);
		return csvConfig;
	}
}