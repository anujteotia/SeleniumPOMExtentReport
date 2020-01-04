package com.tomtom.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.tomtom.base.TestBase;
import com.tomtom.pages.BasePage;
import com.tomtom.pages.SignInPage;

public class SignInPageTest extends TestBase {

	private WebDriver driver;
	private BasePage basePage;

	@BeforeClass
	public void setUp() {
		driver = getDriver();
	}

	@Test
	public void verifySignInFunction() throws InterruptedException {
		customLogger(SignInPageTest.class, "Sign In functionality details...");
		basePage = new BasePage(driver);
		basePage.clickSignInBtn();
		Assert.assertTrue(new SignInPage(driver).verifySignInPageTitle(), "Sign In page title doesn't match");
		Assert.assertTrue(new SignInPage(driver).verifySignInPageText(), "Page text not matching");
		Assert.assertTrue(new SignInPage(driver).verifySignIn(), "Unable to sign in");
		customLogger(SignInPageTest.class, "Sign In is successful.");
	}

	@Test
	public void signInFailure() throws InterruptedException {
		customLogger(SignInPageTest.class, "Sign In failure test case...");
		basePage = new BasePage(driver);
		basePage.clickSignInBtn();
		Assert.assertTrue(new SignInPage(driver).verifySignInFailure(), "SignIn failed!");
	}
}