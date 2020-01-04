package com.tomtom.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tomtom.base.TestBase;

public class RegistrationPage {
	private WebDriver driver;
	private WebDriverWait wait;

	@FindBy(xpath = "//h1[contains(text(), 'register')]")
	WebElement regTxtOnRegPage;

	@FindBy(xpath = "(//button[@type='button']//i)[5]")
	WebElement subscribePopUp;

	@FindBy(xpath = "//label[text()='Mr. ']")
	WebElement nameSalutation;

	@FindBy(xpath = "//input[@placeholder='first name']")
	WebElement firstName;

	@FindBy(xpath = "//input[@placeholder='last name']")
	WebElement lastName;

	@FindBy(name = "dwfrm_profile_customer_birthday")
	WebElement dateOfBirth;

	@FindBy(name = "dwfrm_profile_address_address1")
	WebElement address;

	@FindBy(name = "dwfrm_profile_address_postal")
	WebElement postalCode;

	@FindBy(name = "dwfrm_profile_address_city")
	WebElement addressCity;

	@FindBy(name = "dwfrm_profile_login_password")
	WebElement password;

	@FindBy(name = "dwfrm_profile_login_passwordconfirm")
	WebElement confirmPassword;

	@FindBy(xpath = "//label[@for='dwfrm_profile_customer_addtoemaillist_GB']")
	WebElement acceptCondition;

	@FindBy(name = "dwfrm_profile_confirm")
	WebElement continueToRegister;

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 15, 50);
		PageFactory.initElements(driver, this);
	}

	public String getRegPageTitle() {
		String title = driver.getTitle();
		return title;
	}

	public boolean verifyRegPageTitle() {
		String pageTitle = "register";
		return getRegPageTitle().toLowerCase().contains(pageTitle.toLowerCase());
	}

	public boolean verifyRegPageText() {
		String pageText = "register";
		if (subscribePopUp.isDisplayed()) {
			subscribePopUp.click();
		}
		return wait.until(ExpectedConditions.visibilityOf(regTxtOnRegPage)).getText().toLowerCase()
				.contains(pageText.toLowerCase());
	}

	public void selectNameSalutation() {
		wait.until(ExpectedConditions.visibilityOf(nameSalutation)).click();
	}

	public void setFirstName(String firstname) {
		wait.until(ExpectedConditions.visibilityOf(firstName)).clear();
		firstName.sendKeys(firstname);
	}

	public void setLastName(String lastname) {
		wait.until(ExpectedConditions.visibilityOf(lastName)).clear();
		lastName.sendKeys(lastname);
	}

	public void setDateOfBirth(String dob) {
		wait.until(ExpectedConditions.visibilityOf(dateOfBirth)).clear();
		dateOfBirth.sendKeys(dob);
	}

	public void setAddress(String address1) {
		if (!address.isDisplayed() || !address.isEnabled()) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", address);
		}
		wait.until(ExpectedConditions.visibilityOf(address)).clear();
		address.sendKeys(address1);
	}

	public void setPostalCode(String pin) {
		if (!postalCode.isDisplayed() || !postalCode.isEnabled()) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", postalCode);
		}
		wait.until(ExpectedConditions.visibilityOf(postalCode)).clear();
		postalCode.sendKeys(pin);
	}

	public void setAddressCity(String city) {
		wait.until(ExpectedConditions.visibilityOf(addressCity)).clear();
		addressCity.sendKeys(city);
	}

	public void setPassword(String pass) {
		if (!password.isDisplayed() || !password.isEnabled()) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", password);
		}
		wait.until(ExpectedConditions.visibilityOf(password)).clear();
		password.sendKeys(pass);
	}

	public void setConfirmPassword(String cpass) {
		if (!confirmPassword.isDisplayed() || !confirmPassword.isEnabled()) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", confirmPassword);
		}
		wait.until(ExpectedConditions.visibilityOf(confirmPassword)).clear();
		confirmPassword.sendKeys(cpass);
	}

	public void setAcceptCondition() {
		if (!acceptCondition.isDisplayed() || !acceptCondition.isEnabled()) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", acceptCondition);
		}
		boolean is_selected = wait.until(ExpectedConditions.visibilityOf(acceptCondition)).isSelected();
		if (!is_selected)
			acceptCondition.click();
		else
			TestBase.customLogger(RegistrationPage.class, acceptCondition + " Element is already selected");
	}

	public void clickContinueToRegister() {
		if (!continueToRegister.isDisplayed() || !continueToRegister.isEnabled()) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", continueToRegister);
		}
		wait.until(ExpectedConditions.visibilityOf(continueToRegister)).click();
	}

	public String getRegisteredPageTitle() {
		String title = driver.getTitle();
		TestBase.customLogger(BasePage.class, "Page Title is: " + title);
		return title;
	}

	public void registratiOnHema() {
		try {
			selectNameSalutation();
			Thread.sleep(3000);
			setFirstName("test");
			Thread.sleep(3000);
			setLastName("er");
			Thread.sleep(3000);
			setAddress("Haarlem");
			Thread.sleep(3000);
			setPostalCode("1119PE");
			Thread.sleep(3000);
			setAddressCity("Amsterdam");
			Thread.sleep(3000);
			setPassword("Demo@1234");
			Thread.sleep(3000);
			setConfirmPassword("Demo@1234");
			Thread.sleep(3000);
			setAcceptCondition();
			Thread.sleep(3000);
			clickContinueToRegister();
			Thread.sleep(3000);
		} catch (Exception e) {
			TestBase.customLogger(RegistrationPage.class, "Registration is unsuccessful.");
		}
	}

	public boolean verifySuccessfulRegistration() {
		String expectedPageTitle = "Sites-Hema";
		return getRegisteredPageTitle().toLowerCase().contains(expectedPageTitle.toLowerCase());
	}

}