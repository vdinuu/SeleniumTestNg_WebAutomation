package utils;/*
 * @created 11/03/2025 - 2:20 PM
 * @author Dinu

 * Contains constant values used throughout the test automation framework.
 * This class provides centralized storage for configuration values, timeouts,
 * and file paths used in the automation suite.
 *
 * <p>All constants are declared as public static final to ensure they cannot
 * be modified during runtime and are accessible throughout the application.
 */

public class Constants {
    public static final int MEDIUM_WAIT_SECONDS = 10;
    public static final int LARGE_WAIT_SECONDS = 20;
    public static final int SMALL_WAIT_SECONDS = 5;
    public static final String EXCEL_DATA_FILE_PATH = "src/test/resources/testData/TestData.xlsx";
    public static final String SHEET_NAME = "DataSheet";
    public static final String HUB_URL = "http://10.0.0.141:4444";
}
