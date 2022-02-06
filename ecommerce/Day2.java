package ecommerce;

import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class Day2 {
	WebDriver driver;
	@BeforeTest
	public void start() {
		System.setProperty("webdriver.chrome.driver","d:\\soft\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://live.techpanda.org/index.php/");
	}
	@Test(priority = 0)
	public void price() {
		WebElement mobile = driver.findElement(By.xpath("//*[@id=\"nav\"]/ol/li[1]/a"));
		mobile.click();
		String price = driver.findElement(By.id("product-price-1")).getText();
		Assert.assertEquals(price, "$100.00");
	}
	@Test(priority = 1)
	public void detailsPage() {
		driver.findElement(By.linkText("SONY XPERIA")).click();
		String price = driver.findElement(By.id("product-price-1")).getText();
		Assert.assertEquals(price, "$100.00");
	}
	@AfterTest
	public void close() {
		driver.close();
	}
}
