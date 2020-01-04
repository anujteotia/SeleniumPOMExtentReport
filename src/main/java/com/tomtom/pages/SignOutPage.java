package com.tomtom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tomtom.base.TestBase;

public class SignOutPage {

	private WebDriverWait wait;

	@FindBy(xpath = "//a[@title='sign out']")
	WebElement signOutText;
	
	@FindBy(xpath = "//span[text()='my HEMA']")
	WebElement clickMyHemaToGoToSignOutPage; 
	
	@FindBy(linkText = "my HEMA")
	WebElement signedInPageText;

	@FindBy(xpath = "//h1[text()='sign in']")
	WebElement checkSignInTxtAfterSignOut;

	public SignOutPage(WebDriver driver) {
		wait = new WebDriverWait(driver, 15, 50);
		PageFactory.initElements(driver, this);
	}
	
	public void goToSignOutPage() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(clickMyHemaToGoToSignOutPage));
		clickMyHemaToGoToSignOutPage.click();
		TestBase.customLogger(SignOutPage.class, "clicked on "+ clickMyHemaToGoToSignOutPage + "button");
		Thread.sleep(3000);
	}
	
	public boolean verifySignedInPageText() {
		String pageText = wait.until(ExpectedConditions.visibilityOf(signedInPageText)).getText();
		String expectedPageText = "my HEMA";
		return pageText.toLowerCase().contains(expectedPageText.toLowerCase());
	}

	public void signOut() {
		wait.until(ExpectedConditions.visibilityOf(signOutText));
		if (signOutText.isDisplayed()) {
			signOutText.click();
			TestBase.customLogger(SignOutPage.class, "clicked on "+ signOutText + "button");
		}
	}

	public boolean verifySignOut() {
		wait.until(ExpectedConditions.visibilityOf(checkSignInTxtAfterSignOut));
		return checkSignInTxtAfterSignOut.isDisplayed();
	}

}