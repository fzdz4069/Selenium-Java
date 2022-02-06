package ecommerce;

import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class Day3 {
	WebDriver driver;
	@BeforeTest
	public void start() {
		System.setProperty("webdriver.chrome.driver","d:\\soft\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://live.techpanda.org/index.php/");
	}
	@Test(priority = 0)
	public void add() {
		WebElement mobile = driver.findElement(By.xpath("//*[@id=\"nav\"]/ol/li[1]/a"));
		mobile.click();
		driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[3]/div/div[3]/button/span/span")).click();
	}
	@Test(priority = 1)
	public void qty() {
		WebElement qty = driver.findElement(By.xpath("//*[@id=\"shopping-cart-table\"]/tbody/tr/td[4]/input"));
		qty.clear();
		qty.sendKeys("1000");
		driver.findElement(By.xpath("//*[@id=\"shopping-cart-table\"]/tbody/tr/td[4]/button/span/span")).click();
		String error = driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div/div/ul/li/ul/li")).getText();
		System.out.println(error);
	}
	@Test(priority = 2)
	public void empty() {
		driver.findElement(By.xpath("//*[@id=\"empty_cart_button\"]/span/span")).click();
		String empty = driver.findElement(By.cssSelector("h1")).getText();
		System.out.println(empty);
		Assert.assertEquals(empty, "SHOPPING CART IS EMPTY");
	}
	@AfterTest
	public void close() {
		driver.close();
	}
}
