package com.tomtom.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.tomtom.base.TestBase;
import com.tomtom.pages.BasePage;
import com.tomtom.pages.SignInPage;

public class BasePageTest extends TestBase {

	private WebDriver driver;
	private BasePage basePage;

	@BeforeClass
	public void setUp() {
		driver = getDriver();
	}

	@Test
	public void verifyHomePage() {
		customLogger(BasePageTest.class, "Home page test...");
		BasePage basePage = new BasePage(driver);
		Assert.assertTrue(basePage.verifyBasePageTitle(), "Home page title doesn't match");
	}

	@Test
	public void clickSignInButton() throws InterruptedException {
		basePage = new BasePage(driver);
		basePage.clickSignInBtn();
		Assert.assertTrue(new SignInPage(driver).verifySignInPageTitle(),
				"Click is not performed successfully on signin button");
		customLogger(BasePageTest.class, "SignIn button clicked successfully...");

	}

}