package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.List;
import org.testng.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		String countrySelected = "India";

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
//		LandingPage landingPage = new LandingPage(driver);
		
		
		driver.findElement(By.id("userEmail")).sendKeys("karlo@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("k12345678G@");
		driver.findElement(By.id("login")).click();

//		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".toast-title.ng-star-inserted")));
		// Wait for Login Successfull message
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".mb-3"))));

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
//		List<WebElement> products = driver.findElements(By.xpath("//h5/b"));
//		products.stream().forEach(product -> System.out.println(product.findElement(By.cssSelector("b")).getText()));
//		products.stream().filter(product -> product.findElement(By.xpath("//h5/b")).getText().equals("ZARA COAT 3"));

		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);

		prod.findElement(By.cssSelector(".fa-shopping-cart")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

//		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		// This command is causing performance issue

		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		// Css Selector

//		List<WebElement> cartProducts =  driver.findElements(By.cssSelector("//*[@class='cartSection']/h3"));
//		//Xpath Selector

		Boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));

		Assert.assertTrue(match);

		driver.findElement(By.cssSelector(".subtotal button")).click();

//		//*********** Sending data as input **************
//		driver.findElement(By.xpath("//*[@placeholder='Select Country']")).sendKeys("ind");
//		List<WebElement> listOfCountry = driver.findElements(By.xpath("//section['.ta-results']/button"));

		// *********** Sending data as input via Action **************
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.xpath("//*[@placeholder='Select Country']")), countrySelected).build()
				.perform();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//section['.ta-results']"))));
		List<WebElement> listOfCountry = driver.findElements(By.xpath("//section['.ta-results']/button"));

//		listOfCountry.stream().forEach(country -> System.out.println(country.getText()));

//		 *********** Stream Method for selecting DropDown **************
		WebElement countryToSelect = listOfCountry.stream()
				.filter(country -> country.getText().equalsIgnoreCase(countrySelected)).findFirst().orElse(null);
		countryToSelect.click();

//////		 *********** For Loop method for selecting DropDown **************
//		for (WebElement countryToSelect : listOfCountry) {
//			if (countryToSelect.getText().equalsIgnoreCase(countrySelected)) {
//				countryToSelect.click();
//				break;
//			}
//		}

		driver.findElement(By.cssSelector(".action__submit ")).click();

		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
//		System.out.println(match);
		System.out.println("End of Code");
		driver.quit();

	}

}
