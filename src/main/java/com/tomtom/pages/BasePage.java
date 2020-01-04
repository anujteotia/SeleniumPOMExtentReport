package com.tomtom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.tomtom.base.ExtentTestManager;
import com.tomtom.base.TestBase;

/**
 * Home page setup 
 * @author anujteotia
 *
 */
public class BasePage {
	private WebDriver driver;
	private WebDriverWait wait;

	@FindBy(linkText = "sign in")
	WebElement signInButton;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 15, 50);
		PageFactory.initElements(driver, this);
	}

	public void clickSignInBtn() throws InterruptedException {
		TestBase.customLogger(BasePage.class, "clicking on sign in button");
		wait.until(ExpectedConditions.visibilityOf(signInButton)).click();
		TestBase.customLogger(BasePage.class, "Performing click on signing button...");
		Thread.sleep(3000);
	}

	public String getBasePageTitle() {
		String title = driver.getTitle();
		ExtentTestManager.getTest().log(Status.INFO, "Base Page Title is : " + title);
		TestBase.customLogger(BasePage.class, "Page Title is: " + title);
		return title;
	}

	public boolean verifyBasePageTitle() {
		String expectedPageTitle = "HEMA";
		return getBasePageTitle().toLowerCase().contains(expectedPageTitle.toLowerCase());
	}
}