package com.tomtom.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 *  Extent Manager - This class implements customisation logic for extent report
 * @author anujteotia
 *
 */
public class ExtentManager {
	private static ExtentReports extent;
	private static String reportFileName = "Test-Automaton-Report" + ".html";
	private static String fileSeperator = System.getProperty("file.separator");
	private static String reportFilepath = System.getProperty("user.dir") + fileSeperator + "TestReport";
	private static String reportFileLocation = reportFilepath + fileSeperator + reportFileName;
	static final Logger logger = LogManager.getLogger(ExtentManager.class);

	public static ExtentReports getInstance() {
		if (extent == null)
			createInstance();
		return extent;
	}

	/**
	 * Create an extent report instance
	 * @return
	 */
	public static ExtentReports createInstance() {
		String fileName = getReportPath(reportFilepath);

		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setDocumentTitle(reportFileName);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName(reportFileName);
		htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		// Set environment details
		extent.setSystemInfo("Username",System.getProperty("user.name"));
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("OS Version",System.getProperty("os.version"));
		extent.setSystemInfo("AUT", "QA");
		extent.setSystemInfo("Java", System.getProperty("java.version"));

		return extent;
	}

	/**
	 * Create the report path
	 * @param path - path to store the extent report
	 * @return
	 */
	private static String getReportPath(String path) {
		File testDirectory = new File(path);
		if (!testDirectory.exists()) {
			if (testDirectory.mkdir()) {
				logger.info("Directory: " + path + " is created!");
				return reportFileLocation;
			} else {
				logger.info("Failed to create directory: " + path);
				return System.getProperty("user.dir");
			}
		} else {
			logger.info( "Directory already exists: " + path);
		}
		return reportFileLocation;
	}

}