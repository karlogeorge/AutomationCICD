package rahulshettyacademy.pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		// Initialization
		super(driver); // Send driver from child class LandingPage to parent class AbstractCompoenent
		this.driver = driver;
		PageFactory.initElements(driver, this); // PageFactory design
	}

	@FindBy(css = ".subtotal button")
	WebElement checkOut;

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProducts;

	public Boolean displayCartProduct(String productToCheck) {
		Boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productToCheck));
		return match;
	}

	public CheckOutPage checkOutOrder() {
		checkOut.click();
		CheckOutPage checkOutPage = new CheckOutPage(driver);
		return checkOutPage;
	}
}
