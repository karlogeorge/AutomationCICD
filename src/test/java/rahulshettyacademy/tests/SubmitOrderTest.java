package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;
import rashulshettyacademy.TestComponents.BaseTest;
import rashulshettyacademy.TestComponents.Retry;

public class SubmitOrderTest extends BaseTest {
//	String userEmail = "karlo@gmail.com";
//	String userPassword = "k12345678G@";
//	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" }, retryAnalyzer = Retry.class)
	public void submitOrder(HashMap<String, String> input) throws IOException {

		// TODO Auto-generated method stub

		String countrySelected = "India";

//		LandingPage landingPage = launchApplication();

		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));

		// Add product to cart
		productCatalogue.addProductToCart(input.get("productName"));

		// Got To CART
		CartPage cartPage = productCatalogue.goToCartPage();

		// Verify product in cart
		Boolean match = cartPage.displayCartProduct(input.get("productName"));
		Assert.assertTrue(match);

		// Checkout Cart
		CheckOutPage checkOutPage = cartPage.checkOutOrder();

		// CheckOut/Place Order and redirect to confirmation Page
		ConfirmationPage confirmationPage = checkOutPage.placeOrder(countrySelected);

		// Verify if order placed
//		String confirmMessage = orderPage.confirmMatch();
		String confirmationMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		System.out.println("End of Code");
	}

	// To verify ZARA COAT 3 is displaying Order Page
	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {
		String userEmail = "karlo@gmail.com";
		String userPassword = "k12345678G@";
		String productName = "ZARA COAT 3";

		ProductCatalogue productCatalogue = landingPage.loginApplication(userEmail, userPassword);
		OrderPage orderPage = productCatalogue.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));

		System.out.println("End of Code 2");

	}


	
	//Extent Reports

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonData(
				System.getProperty("user.dir") + "\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };

//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "karlo@gmail.com");
//		map.put("password", "k12345678G@");
//		map.put("productName", "ZARA COAT 3");
//
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "shetty@gmail.com");
//		map1.put("password", "Iamking@000");
//		map1.put("productName", "ADIDAS ORIGINAL");
//		return new Object[][] { { map }, { map1 } };
		// Object data types helps to include any type of data types

		// Above are HashMap method for sending data
//		return new Object[][] { { "karlo@gmail.com", "k12345678G@", "ZARA COAT 3" },
//			{ "shetty@gmail.com", "Iamking@000", "ADIDAS ORIGINAL" } };

	}

}

/*
 * String userEmail = "karlo@gmail.com"; String userPassword = "k12345678G@";
 * String productName = "ZARA COAT 3"; String countrySelected = "India";
 * 
 * WebDriver driver = new ChromeDriver(); driver.manage().window().maximize();
 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
 * 
 * // Initialize Landing Page LandingPage landingPage = new LandingPage(driver);
 * 
 * // Login to Application landingPage.goTo();
 * landingPage.loginApplication(userEmail, userPassword);
 * 
 * // Add product to cart ProductCatalogue productCatalogue = new
 * ProductCatalogue(driver); List<WebElement> products =
 * productCatalogue.getProductList();
 * productCatalogue.addProductToCart(productName);
 * 
 * 
 * //Got To CART productCatalogue.goToCartPage();
 * 
 * // Verify product in cart CartPage cartPage = new CartPage(driver);
 * Assert.assertTrue(cartPage.checkCartProduct(productName));
 * 
 * // Checkout Cart cartPage.checkOutOrder();
 * 
 */
