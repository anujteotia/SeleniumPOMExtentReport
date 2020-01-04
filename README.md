# Selenium - Page Object model Test Automation Framework
Selenium tests using Page-Object-Model running on TestNG framework. It shows how it can be used in selenium to automate any application. testNG is used as test Framework.

### Dependencies
	Java and Maven

### Libraries
	Selenium, TestNG, log4j2 and Extent Reports

### Steps to clone and execute the tests
```
git clone https://github.com/anujteotia/SeleniumPOMExtentReport.git
cd into/project
Right Click on testng.xml -> Run As -> TestNG Suite. 
```

### Test Case:
* Test cases are present under `src/test/java/com.tomtom.tests`


### Highlights

* This framework supports chrome and firefox browsers on windows and macOS operating systems.
* It creates two html reports i.e. Extent Report (TestReport/Test-Automation-Report.html) and TestNG report(test-output/emailable-report.html)
* Implements 4 types of customized logging 
	* creation of log file (Log/execution.log)
	* Console logger
	* Ingest logs to TestNG report
	* Ingest logs to Extent Report
	
* Captures screenshot in case of test case failure:
	* Screesnshot is added to the extent report along with the logs of failure.
	* screenshots are stored locally as well(TestReport/screeshots/testFileName/screenshot_timestamp.png)
	
		
		
### Important locations 

* WebDrivers: WebDriver Folder
* log4j properties file: src/main/resources/log4j2.xml
* Failure Screenshots: TestReport/screeshots Folder
* Log file : Log Folder
* Extent html Report: TestReport/Test-Automation-Report.html
* TestNG html report: test-output/emailable-report.html
