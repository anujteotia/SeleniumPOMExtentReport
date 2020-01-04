package com.tomtom.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.tomtom.base.TestBase;
import com.tomtom.pages.BasePage;
import com.tomtom.pages.SignInPage;
import com.tomtom.pages.SignOutPage;

public class SignOutPageTest extends TestBase {

	private WebDriver driver;
	private SignOutPage signOutPage;
	private BasePage basePage;

	@BeforeClass
	public void setUp() {
		driver = getDriver();
	}

	@Test
	public void verifySignInFunction() throws InterruptedException {
		customLogger(SignOutPageTest.class, "Sign In process is in progress...");
		basePage = new BasePage(driver);
		basePage.clickSignInBtn();
		Assert.assertTrue(new SignInPage(driver).verifySignInPageText(), "Page text not matching");
		Assert.assertTrue(new SignInPage(driver).verifySignIn(), "Unable to sign in");
		customLogger(SignOutPageTest.class, "SignIn successful.");
	}

	@Test(dependsOnMethods = "verifySignInFunction")
	public void verifySignOutFunction() throws InterruptedException {
		customLogger(SignOutPageTest.class, "Sign Out process is in progress...");
		signOutPage = new SignOutPage(driver);
		signOutPage.goToSignOutPage();
		customLogger(SignOutPageTest.class, "Navigated to SignOut Page");
		Assert.assertTrue(signOutPage.verifySignedInPageText(), "SignIn was unsuccessful");
		signOutPage.signOut();
		Assert.assertTrue(signOutPage.verifySignOut(), "Sign Out is unsuccessful.");
		customLogger(SignOutPageTest.class, "Sign Out is successful.");
	}
}
