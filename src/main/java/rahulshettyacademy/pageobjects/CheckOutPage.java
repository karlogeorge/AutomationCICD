package rahulshettyacademy.pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		// Initialization
		super(driver); // Send driver from child class LandingPage to parent class AbstractCompoenent
		this.driver = driver;
		PageFactory.initElements(driver, this); // PageFactory design
	}

	@FindBy(xpath = "//*[@placeholder='Select Country']")
	WebElement selectedCountry;

	@FindBy(xpath = "//section['.ta-results']")
	WebElement countryToLoad;

	// List<WebElement> listOfCountry =
	// driver.findElements(By.xpath("//section['.ta-results']/button"));
	@FindBy(xpath = "//section['.ta-results']/button")
	List<WebElement> countries;

	// driver.findElement(By.cssSelector(".action__submit ")).click();
	@FindBy(css=".action__submit")
	WebElement submit;
	
	
	public void selectCountryName(String country) {
		Actions a = new Actions(driver);
		a.sendKeys(selectedCountry, country).build().perform();
		waitForWebElementToAppear(countryToLoad);
		WebElement countryToSelect = countries.stream().filter(country1 -> country1.getText().equalsIgnoreCase(country))
				.findFirst().orElse(null);
		countryToSelect.click();

	}
	
	public ConfirmationPage placeOrder(String country) {
		selectCountryName(country);
		submit.click();
		return new ConfirmationPage(driver);
//		return confirmationPage;
		
	}


}
