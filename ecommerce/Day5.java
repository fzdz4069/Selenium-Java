package ecommerce;

import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.time.Duration;
import org.apache.commons.io.FileUtils;

public class Day5 {
	WebDriver driver;
	@BeforeTest
	public void start() {
		System.setProperty("webdriver.chrome.driver","d:\\soft\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://live.techpanda.org/index.php/");
	}
	@Test(priority = 0)
	public void account() {
		driver.findElement(By.xpath("//*[@id=\"header\"]/div/div[2]/div/a/span[2]")).click();
		driver.findElement(By.linkText("Register")).click();
	}
	@Test(priority = 1)
	public void register() {
		driver.findElement(By.id("firstname")).sendKeys("aa");
		driver.findElement(By.id("lastname")).sendKeys("bb");
		driver.findElement(By.id("email_address")).sendKeys("qabcdeq@xyz.com");
		driver.findElement(By.id("password")).sendKeys("qwerty");
		driver.findElement(By.id("confirmation")).sendKeys("qwerty");
		driver.findElement(By.xpath("//*[@class='button' and @title='Register']")).click();
	}
	@Test(priority = 2)
	public void regDone() throws Exception {
		String text = driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div/div/ul/li/ul/li")).getText();
		System.out.println(text);
		String file = "d:\\temp\\day5.jpg";
		TakesScreenshot scrShot = ((TakesScreenshot)driver);
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile = new File(file);
		FileUtils.copyFile(SrcFile, DestFile);
	}
	@Test(priority = 3)
	public void wishlist() {
		driver.findElement(By.linkText("TV")).click();
		driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div[1]/div[2]/ul/li[1]/div/div[3]/ul/li[1]/a")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.xpath("//*[@title='Share Wishlist']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}
	@Test(priority = 4)
	public void shareWishlist() {
		driver.findElement(By.id("email_address")).sendKeys("abc@xyz.com,qwe@asd.com");
		driver.findElement(By.id("message")).sendKeys("Share Wishlist");
		driver.findElement(By.xpath("//*[@class='button' and @title='Share Wishlist']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		String text = driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div/div[1]/ul/li/ul/li")).getText();
		System.out.println(text);
	}
	@AfterTest
	public void close() {
		driver.close();
	}
}
