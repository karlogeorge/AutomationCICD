package rahulshettyacademy.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;
import rashulshettyacademy.TestComponents.BaseTest;
import rashulshettyacademy.TestComponents.Retry;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups = { "ErrorHandling" }, retryAnalyzer = Retry.class)
	public void LoginErrorValidation() {
		String userEmail = "karlo@gmail.com";
		String userPassword = "k123456sG@";

		landingPage.loginApplication(userEmail, userPassword);
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMesssage());

//		System.out.println(landingPage.getErrorMesssage());
	}

	@Test(retryAnalyzer = Retry.class)
	public void ProductErrorValidation() {
		String userEmail = "karlo@gmail.com";
		String userPassword = "k12345678G@";
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication(userEmail, userPassword);

		// Add product to cart
		productCatalogue.addProductToCart(productName);

//		System.out.println("Test 1");

		// Got To CART
		CartPage cartPage = productCatalogue.goToCartPage();

//		System.out.println("Test 2");

		// Verify product in cart
		Boolean match = cartPage.displayCartProduct(productName + "sssss");
		Assert.assertFalse(match);
	}

}
