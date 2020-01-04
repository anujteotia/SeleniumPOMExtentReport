package com.tomtom.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.tomtom.base.TestBase;

/**
 *  This class contains all the operations related to Add to cart page.
 * @author anujteotia
 *
 */
public class AddToCartPage {

	private WebDriverWait wait;

	@FindBy(id = "q")
	WebElement searchTextBox;

	@FindBy(xpath = "(//div[@class='product-image'])[1]")
	WebElement itemToAddToCart;

	@FindBy(id = "add-to-cart")
	WebElement addToCart;

	@FindBy(xpath = "(//li[@class='selectable variation-group-value ']//a)[1]")
	WebElement selectSizeOfItem;

	@FindBy(xpath = "//span[text()='in stock']")
	WebElement itemInStock;

	@FindBy(xpath = "//div[@class='product-title-price']")
	WebElement productName;

	@FindBy(xpath = "//span[contains(text(),'shopping basket')]")
	WebElement shoppingBasketText;

	@FindBy(xpath = "//div[@class='product-info']/span[@class='title']")
	WebElement productNameInBasket;

	public AddToCartPage(WebDriver driver) {
		wait = new WebDriverWait(driver, 15, 50);
		PageFactory.initElements(driver, this);
	}

	public void searchProduct(String product) throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(searchTextBox));
		if (searchTextBox.isDisplayed()) {
			searchTextBox.clear();
			TestBase.customLogger(AddToCartPage.class, "Clearing field at location: " + searchTextBox);
			searchTextBox.sendKeys(product);
			TestBase.customLogger(AddToCartPage.class, "Entering value: " + product + " at location: " + searchTextBox);
			searchTextBox.sendKeys(Keys.ENTER);
			TestBase.customLogger(AddToCartPage.class,
					"Pressed Enter Key to perform search for: " + product + " product");
		}
	}

	public void clickOnItem() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(itemToAddToCart));
		itemToAddToCart.isDisplayed();
		itemToAddToCart.click();
		TestBase.customLogger(AddToCartPage.class, "Clicked on " + itemToAddToCart + "image");
		Thread.sleep(3000);
	}

	public String fetchProductName() {
		String productNameText = wait.until(ExpectedConditions.visibilityOf(productName)).getText();
		TestBase.customLogger(AddToCartPage.class,
				"Fetching text for " + productName + ". Text value: " + productNameText);
		return productNameText;
	}

	public void selectProductSize() {
		wait.until(ExpectedConditions.visibilityOf(selectSizeOfItem)).click();
		TestBase.customLogger(AddToCartPage.class, selectSizeOfItem + " size selected from the available size options");
	}

	public boolean verifyAddToCartOption() {
		String addToCartText = wait.until(ExpectedConditions.visibilityOf(addToCart)).getText();
		TestBase.customLogger(AddToCartPage.class, "Fetching text for " + addToCart + ". Text value: " + addToCartText);
		String expectedPageText = "add to basket";
		return addToCartText.toLowerCase().contains(expectedPageText.toLowerCase());
	}

	public void clickAddToBasket() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(addToCart));
		addToCart.click();
		TestBase.customLogger(AddToCartPage.class, "Clicked on " + addToCart + "image");
		Thread.sleep(3000);
	}

	public boolean verifyShoppingBasket() {
		wait.until(ExpectedConditions.visibilityOf(shoppingBasketText));
		String basketText = "shopping basket";
		return shoppingBasketText.getText().toLowerCase().contains(basketText.toLowerCase());
	}

	public boolean verifyItemAddedToCart() {
		TestBase.customLogger(AddToCartPage.class, "Verifying whether item is added to basket or not...");
		String productName = wait.until(ExpectedConditions.visibilityOf(productNameInBasket)).getText();
		TestBase.customLogger(AddToCartPage.class,
				"Fetching text for " + productNameInBasket + ". Text value: " + productName);
		String productNameFromDetailPage = fetchProductName();
		return productName.toLowerCase().contains(productNameFromDetailPage.toLowerCase());

	}

	public void addProductToTheCart() throws InterruptedException {
		searchProduct("jacket");
		Thread.sleep(2000);
		clickOnItem();
		boolean basketOption = verifyAddToCartOption();
		Assert.assertTrue(basketOption, "Add to basket option is not present in the page");
		Thread.sleep(2000);
		selectProductSize();
		Thread.sleep(2000);
		clickAddToBasket();
	}

}