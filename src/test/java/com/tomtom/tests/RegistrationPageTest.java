package com.tomtom.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.tomtom.base.TestBase;
import com.tomtom.pages.BasePage;
import com.tomtom.pages.RegistrationPage;
import com.tomtom.pages.SignInPage;

public class RegistrationPageTest extends TestBase {
	private WebDriver driver;
	private SignInPage signInPage;
	private BasePage basePage;
	private RegistrationPage registrationPage;

	@BeforeClass
	public void setUp() {
		driver = getDriver();
	}

	@Test
	public void OpenRegistrationPage() throws InterruptedException {
		customLogger(RegistrationPageTest.class, "Registration Page test...");
		basePage = new BasePage(driver);
		basePage.clickSignInBtn();

		signInPage = new SignInPage(driver);
		Assert.assertTrue(signInPage.verifyNewToHemaTxt(), "New to HEMA text is not available on SignIn page");
		customLogger(SignInPage.class, "New to HEMA text is available on SignIn page");

		Assert.assertTrue(signInPage.verifyContinueForRegister(), "Unable to redirect to registration page");
		customLogger(SignInPage.class, "Continuing to registration page...");
		
		registrationPage = new RegistrationPage(driver);
		Assert.assertTrue(registrationPage.verifyRegPageTitle(), "Page Title not matching");
		Assert.assertTrue(registrationPage.verifyRegPageText(), "Page text not maching");
		customLogger(RegistrationPageTest.class, "Registration page opened successfully");
		Thread.sleep(5000);
	}
	
	@Test (dependsOnMethods = "OpenRegistrationPage")
	public void registrationToHema() {
		customLogger(RegistrationPageTest.class, "Registration started...");
		registrationPage.registratiOnHema();
		Assert.assertTrue(registrationPage.verifySuccessfulRegistration(), "Registration is unsuccessful");
		
	}

}