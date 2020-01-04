package com.tomtom.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.tomtom.base.TestBase;
import com.tomtom.pages.AddToCartPage;
import com.tomtom.pages.BasePage;
import com.tomtom.pages.SignInPage;

public class AddToCartPageTest extends TestBase {

	private WebDriver driver;
	private BasePage basePage;
	private AddToCartPage addToCartPage;

	@BeforeClass
	public void setUp() {
		driver = getDriver();
	}

	@Test
	public void verifySignIn() throws InterruptedException {
		customLogger(AddToCartPageTest.class, "SignIn is in progress...");
		basePage = new BasePage(driver);
		basePage.clickSignInBtn();
		Assert.assertTrue(new SignInPage(driver).verifySignInPageText(), "Page text not matching");
		Assert.assertTrue(new SignInPage(driver).verifySignIn(), "Unable to sign in");
		customLogger(AddToCartPageTest.class, "SignIn successful.");
	}

	@Test(dependsOnMethods = "verifySignIn")
	public void addItemsToCart() throws InterruptedException {
		customLogger(AddToCartPageTest.class, "Adding item to cart is in progress...");
		addToCartPage = new AddToCartPage(driver);
		addToCartPage.addProductToTheCart();
		Assert.assertTrue(addToCartPage.verifyShoppingBasket(), "Shopping Cart is not open");
		customLogger(AddToCartPageTest.class, "Shopping cart is opened successfully.");
		Assert.assertTrue(addToCartPage.verifyItemAddedToCart(), "Item is not added in cart");
		customLogger(AddToCartPageTest.class, "Item is added to shopping cart successfully");
	}
}
