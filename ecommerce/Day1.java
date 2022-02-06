package ecommerce;

import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import org.apache.commons.io.FileUtils;

public class Day1 {
	WebDriver driver;
	
	@BeforeTest
	public void start() {
		System.setProperty("webdriver.chrome.driver","d:\\soft\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://live.techpanda.org/index.php/");
	}
	@Test(priority = 0)
	public void titleMain() {
		String title = driver.getTitle();
		System.out.println(title);
		String text = driver.findElement(By.xpath("//*[@id='top']/body/div/div/div[2]/div/div[1]/div/div/h2")).getText();
		System.out.println(text);
	}
	@Test(priority = 1)
	public void clickMobile() {
		WebElement mobile = driver.findElement(By.xpath("//*[@id=\"nav\"]/ol/li[1]/a"));
		boolean disp = mobile.isDisplayed();
		Assert.assertTrue(disp);
		mobile.click();
	}
	@Test(priority = 2)
	public void titleMobile() {
		String title = driver.getTitle();
		System.out.println(title);
	}
	@Test(priority = 3)
	public void sortByName() {
		Select sortBy = new Select(driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div[1]/div[3]/div[1]/div[1]/div/select")));
		sortBy.selectByVisibleText("Name");
	}
	@Test(priority = 4)
	public void order() {
		String text1 = driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[1]/div/h2/a")).getText();
		String text2 = driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[3]/div/h2/a")).getText();
		Assert.assertEquals(text1, "IPHONE");
		Assert.assertEquals(text2, "SONY XPERIA");
	}
	@Test(priority = 4)
	public void screen() throws Exception {
		String file = "d:\\temp\\day1.jpg";
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
