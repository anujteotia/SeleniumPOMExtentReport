package com.tomtom.base;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.Status;

/**
 * TestBase - Instantiates the web driver and implements a custom logger for
 * testNG, Extent Report and log4j2.
 * 
 * @author anujteotia
 *
 */
public class TestBase {
	private WebDriver driver;
	private static ChromeOptions chromeOptions = null;
	private static FirefoxOptions firefoxOptions = null;
	static final Logger logger = LogManager.getLogger(TestBase.class);
	static String fileSeperator = System.getProperty("file.separator");
	static String osName = System.getProperty("os.name");
	private static String driverPath = System.getProperty("user.dir") + fileSeperator + "WebDriver" + fileSeperator;

	/**
	 * 
	 * @return driver
	 */
	public WebDriver getDriver() {
		return driver;
	}

	/**
	 * set the web driver
	 */
	private void setDriver(String browserType, String appURL) {
		switch (browserType) {
		case "chrome":
			driver = initChromeDriver(appURL);
			break;
		case "firefox":
			driver = initFirefoxDriver(appURL);
			break;
		default:
			logger.info("browser : " + browserType + " is invalid, Launching Chrome as browser of choice..");
			driver = initChromeDriver(appURL);
		}
	}

	/**
	 * Sets up chrome driver
	 * 
	 * @param appURL - url to open
	 * @return chrome driver instance
	 */
	private static WebDriver initChromeDriver(String appURL) {
		logger.info("Launching Google chrome browser..");
		if (osName.toLowerCase().contains("window"))
			System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
		chromeOptions = new ChromeOptions();
		chromeOptions.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		chromeOptions.setExperimentalOption("prefs", prefs);
		chromeOptions.addArguments("--disable-extensions");
		WebDriver driver = new ChromeDriver(chromeOptions);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.navigate().to(appURL);
		return driver;
	}

	/**
	 * 
	 * Sets up firefox driver
	 * 
	 * @param appURL - url to open
	 * @return firefox driver instance
	 */
	private static WebDriver initFirefoxDriver(String appURL) {
		logger.info("Launching Firefox browser..");
		if (osName.toLowerCase().contains("window"))
			System.setProperty("webdriver.gecko.driver", driverPath + "geckodriver.exe");
		firefoxOptions = new FirefoxOptions();
		WebDriver driver = new FirefoxDriver(firefoxOptions);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(appURL);
		return driver;
	}

	/**
	 * initialize TestBase Setup
	 * 
	 * @param browserType - browser name i.e. chrome/firefox.
	 * @param appURL      - url to open.
	 */
	@Parameters({ "browserType", "appURL" })
	@BeforeClass
	public void initializeTestBaseSetup(String browserType, String appURL) {
		try {
			setDriver(browserType, appURL);

		} catch (Exception e) {
			logger.info("Error....." + e.getMessage());
		}
	}

	/**
	 * customLogger
	 * 
	 * @param className - name of the class for logging.
	 * @param message   - custom message to ingest in reports.
	 */
	public static void customLogger(Class<?> className, String message) {
		Logger logger = LogManager.getLogger(className);
		ExtentTestManager.getTest().log(Status.INFO, message);
		Reporter.log(message);
		logger.info(message);
	}

	/**
	 * tear down - quit the driver instance
	 */
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
