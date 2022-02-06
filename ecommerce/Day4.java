package ecommerce;

import org.testng.annotations.*;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import org.apache.commons.io.FileUtils;

public class Day4 {
	WebDriver driver;
	String MainWindow;
	@BeforeTest
	public void start() {
		System.setProperty("webdriver.chrome.driver","d:\\soft\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://live.techpanda.org/index.php/");
	}
	@Test(priority = 0)
	public void compare() {
		WebElement mobile = driver.findElement(By.xpath("//*[@id=\"nav\"]/ol/li[1]/a"));
		mobile.click();
		driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[1]/div/div[3]/ul/li[2]/a")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[3]/div/div[3]/ul/li[2]/a")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		//driver.switchTo().parentFrame();
		driver.findElement(By.xpath("//*[@title='Compare']")).click();
	}
	@Test(priority = 1)
	public void popup() {
		MainWindow = driver.getWindowHandle();
		Set <String> s1 = driver.getWindowHandles();		
	    Iterator <String> i1 = s1.iterator();
	    while(i1.hasNext())	{		
	    	String ChildWindow = i1.next();		
	    	if(!MainWindow.equalsIgnoreCase(ChildWindow)) {
	            driver.switchTo().window(ChildWindow);	                                                                                                           
	        }
	    }
	    String title = driver.getTitle();
        System.out.println(title);
	}
	@Test(priority = 2)
	public void screenOpen() throws Exception {
		String file = "d:\\temp\\day4-1.jpg";
		TakesScreenshot scrShot = ((TakesScreenshot)driver);
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile = new File(file);
		FileUtils.copyFile(SrcFile, DestFile);
		driver.close();
	}
	@Test(priority = 3)
	public void screenClose() throws Exception {
		driver.switchTo().window(MainWindow);
		String file = "d:\\temp\\day4-2.jpg";
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
