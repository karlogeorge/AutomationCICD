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

public class OrderPage extends AbstractComponent {
	WebDriver driver;

	public OrderPage(WebDriver driver) {
		// Initialization
		super(driver); // Send driver from child class LandingPage to parent class AbstractCompoenent
		this.driver = driver;
		PageFactory.initElements(driver, this); // PageFactory design
	}

//	@FindBy(xpath = "//tr[@class='ng-star-inserted']/td[2]")
	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> orderProducts;

	public Boolean verifyOrderDisplay(String productToCheck) {
		Boolean match = orderProducts.stream()
				.anyMatch(orderProduct -> orderProduct.getText().equalsIgnoreCase(productToCheck));
		return match;
//		orderProducts.stream().forEach(orderProduct -> System.out.println(orderProduct.getText()));
		
	}

//	public CheckOutPage checkOutOrder() {
//		checkOut.click();
//		CheckOutPage checkOutPage = new CheckOutPage(driver);
//		return checkOutPage;
//	}
}
