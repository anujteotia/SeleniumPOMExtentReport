package com.tomtom.pages;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tomtom.base.TestBase;

public class SignInPage {
	private WebDriver driver;
	private WebDriverWait wait;
	String generatedString = RandomStringUtils.randomAlphabetic(5);
	String emailToRegister = generatedString+"@gmail.com";

	@FindBy(xpath = "//h1[contains(text(),'sign in')]")
	WebElement signInPageText;

	@FindBy(name = "dwfrm_login_username")
	WebElement emailTextBox;

	@FindBy(name = "dwfrm_login_password")
	WebElement passwordTextBox;

	@FindBy(name = "dwfrm_login_login")
	WebElement loginBtn;

	@FindBy(xpath = "//strong[contains(text(),'Sorry')]")
	WebElement signInErrorMsgTxt;
	
	@FindBy(xpath = "//span[text()='my HEMA']")
	WebElement SignedInPageText;

	@FindBy(xpath = "//h2[contains(text(), 'new to HEMA')]")
	WebElement newToHema;

	@FindBy(name = "dwfrm_preregister_username")
	WebElement registerEmailTextBox;

	@FindBy(name = "dwfrm_preregister_register")
	WebElement continueBtnToRegister;
	
	@FindBy(id = "dwfrm_preregister_username_default-error")
	WebElement errorMsgTxtToRegister;

	public SignInPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 15, 50);
		PageFactory.initElements(driver, this);
	}

	public String getSignInPageTitle() {
		String pageTitle = driver.getTitle();
		return pageTitle;
	}

	public boolean verifySignInPageTitle() {
		String pageTitle = "my HEMA";
		return getSignInPageTitle().toLowerCase().contains(pageTitle.toLowerCase());
	}

	public boolean verifySignInPageText() {
		String pageText = wait.until(ExpectedConditions.visibilityOf(signInPageText)).getText();
		TestBase.customLogger(SignInPage.class, "Fetching text for "+ signInPageText + ". Text value: "+ pageText);
		String expectedPageText = "Sign in";
		return pageText.toLowerCase().contains(expectedPageText.toLowerCase());
	}

	public boolean verifySignIn() throws InterruptedException {
		enterUserName("dummy@gmail.com");
		enterPassword("Demo@1234");
		clickOnSignIn();
		Thread.sleep(3000);
		return verifySignedInPage();
	}
	
	public boolean verifySignInFailure() throws InterruptedException {
		enterUserName(emailToRegister);
		enterPassword("Demo@1234");
		clickOnSignIn();
		Thread.sleep(3000);
		return verifySignedInPage();
	}

	public void enterUserName(String userName) {
		wait.until(ExpectedConditions.visibilityOf(emailTextBox));
		if (emailTextBox.isDisplayed()) {
			emailTextBox.clear();
			TestBase.customLogger(SignInPage.class, "Clearing field at location: "+ emailTextBox);
			emailTextBox.sendKeys(userName);
			TestBase.customLogger(SignInPage.class, "Entering value: "+ userName + "at location: "+ emailTextBox);
		}
	}

	public void enterPassword(String password) {
		wait.until(ExpectedConditions.visibilityOf(passwordTextBox));
		if (passwordTextBox.isDisplayed()) {
			passwordTextBox.clear();
			TestBase.customLogger(SignInPage.class, "Clearing field at location: "+ passwordTextBox);
			passwordTextBox.sendKeys(password);
			TestBase.customLogger(SignInPage.class, "Entering value: "+ password + "at location: "+ passwordTextBox);
		}
	}

	public void clickOnSignIn() {
		wait.until(ExpectedConditions.visibilityOf(loginBtn));
		if (loginBtn.isDisplayed()) {
			loginBtn.click();
			TestBase.customLogger(SignOutPage.class, "clicked on "+ loginBtn + "button");
		}
	}

	public boolean verifySignedInPage() {
		wait.until(ExpectedConditions.visibilityOf(SignedInPageText));
		return SignedInPageText.isDisplayed();
	}

	public boolean verifyNewToHemaTxt() {
		String registerText = wait.until(ExpectedConditions.visibilityOf(newToHema)).getText();
		TestBase.customLogger(SignInPage.class, "Fetching text for "+ newToHema + ". Text value: "+ registerText);
		String expectedPageText = "new to HEMA";
		return registerText.toLowerCase().contains(expectedPageText.toLowerCase());
	}

	public void enterRegisterEmailId(String emailId) {
		wait.until(ExpectedConditions.visibilityOf(registerEmailTextBox));
		if (registerEmailTextBox.isDisplayed()) {
			registerEmailTextBox.clear();
			TestBase.customLogger(SignInPage.class, "Clearing field at location: "+ registerEmailTextBox);
			registerEmailTextBox.sendKeys(emailId);
			TestBase.customLogger(SignInPage.class, "Entering value: "+ emailId + "at location: "+ registerEmailTextBox);
		}
	}

	public void clickOnContinueBtn() {
		wait.until(ExpectedConditions.visibilityOf(continueBtnToRegister));
		if (continueBtnToRegister.isDisplayed()) {
			continueBtnToRegister.click();
			TestBase.customLogger(SignOutPage.class, "clicked on "+ continueBtnToRegister + "button");
		}
	}
	
	public String getRegisterErrorMessage() {
		String strErrorMsg = null;
		if (errorMsgTxtToRegister.isDisplayed() && errorMsgTxtToRegister.isEnabled())
			strErrorMsg = errorMsgTxtToRegister.getText();
		return strErrorMsg;
	}
	
	public boolean verifyContinueForRegister() {
		enterRegisterEmailId(emailToRegister);
		clickOnContinueBtn();
		RegistrationPage registrationPage = new RegistrationPage(driver);
		return registrationPage.verifyRegPageTitle();
	}

}