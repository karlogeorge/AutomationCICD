package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		// Initialization
		super(driver); // Send driver from child class LandingPage to parent class AbstractCompoenent
		this.driver = driver;
		PageFactory.initElements(driver, this); // PageFactory design
	}

//	WebElement userEmail = driver.findElement(By.id("userEmail"));
//	WebElement userPassword = driver.findElement(By.id("userPassword"));

	// PageFactory design
	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement passwordElement;

	@FindBy(id = "login")
	WebElement submit;

	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;

	public ProductCatalogue loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		passwordElement.sendKeys(password);
		submit.click();

		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;

	}

	public String getErrorMesssage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}

}
