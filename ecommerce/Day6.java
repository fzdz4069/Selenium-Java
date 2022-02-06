package ecommerce;

import java.time.Duration;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import java.io.File;
import org.apache.commons.io.FileUtils;


public class Day6 {
	WebDriver driver;
	@BeforeTest
	public void start() {
		System.setProperty("webdriver.chrome.driver","d:\\soft\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://live.techpanda.org/index.php/");
	}
	@Test(priority = 0)
	public void login() {
		driver.findElement(By.xpath("//*[@id=\"header\"]/div/div[2]/div/a/span[2]")).click();
		driver.findElement(By.xpath("//*[@id=\"header-account\"]/div/ul/li[1]/a")).click();
		driver.findElement(By.id("email")).sendKeys("qabcdeq@xyz.com");
		driver.findElement(By.id("pass")).sendKeys("qwerty");
		driver.findElement(By.xpath("//*[@id=\"send2\"]/span/span")).click();
	}
	@Test(priority = 1)
	public void addCart() {
		driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[1]/div/div[2]/ul/li[8]/a")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.xpath("//*[@title='Add to Cart']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}
	@Test(priority = 2)
	public void estimate() {
		WebElement selectState = driver.findElement(By.id("region_id"));
		Select state = new Select(selectState);
		state.selectByVisibleText("New York");
		driver.findElement(By.xpath("//*[@name='estimate_postcode']")).sendKeys("542896");
		driver.findElement(By.xpath("//*[@title='Estimate']"));
		driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div/div/div[1]/ul/li/button/span/span")).click();
	}
	@Test(priority = 3)
	public void address() {
		/*driver.findElement(By.id("billing:street1")).sendKeys("ABC");
		driver.findElement(By.id("billing:city")).sendKeys("New York");
		driver.findElement(By.id("billing:postcode")).sendKeys("542896");
		driver.findElement(By.id("billing:telephone")).sendKeys("12345678");*/
		driver.findElement(By.xpath("//*[@title='Continue']")).click();
		driver.findElement(By.xpath("//*[@id=\"billing-buttons-container\"]/button/span/span")).click();
	}
	@Test(priority = 4)
	public void payment() {
		driver.findElement(By.xpath("//*[@id=\"shipping-method-buttons-container\"]/button")).click();
		driver.findElement(By.xpath("//*[@title='Check / Money order']")).click();
		driver.findElement(By.xpath("//*[@id=\"payment-buttons-container\"]/button/span/span")).click();		
	}
	@Test(priority = 5)
	public void confirmOrder() throws Exception {
		driver.findElement(By.xpath("//*[@id=\"review-buttons-container\"]/button/span/span")).click();
		String file = "d:\\temp\\day6.jpg";
		TakesScreenshot scrShot = ((TakesScreenshot)driver);
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile = new File(file);
		FileUtils.copyFile(SrcFile, DestFile);
	}
	@AfterTest
	public void close() {
		driver.close();
	}
}
