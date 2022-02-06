package pomTest;

import java.io.File;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

//import pageFactory.Bitchute;
import pages.Bitchute;

public class Bitchute_search {
	public static WebDriver driver;
	Bitchute b;
	public String hp = "https://www.bitchute.com/";
	public String enter = Keys.chord(Keys.ENTER);
	public String newTab = Keys.chord(Keys.CONTROL, Keys.ENTER);
	public String query = "new ifb";
	public WebElement search;
	public WebElement link;
	public Set <String> windows;
	public Iterator <String> it;
	
	@BeforeTest(alwaysRun = true)
	public void start() {
		System.setProperty("webdriver.chrome.driver","d:\\soft\\chromedriver.exe");
		driver = new ChromeDriver();
		b = new Bitchute(driver);
		driver.manage().window().maximize();
		driver.get(hp);
	}
	@Test(priority = 0)
	public void search() {
		search = b.search();
		boolean searchDisp = search.isDisplayed();
		Assert.assertTrue(searchDisp);
	}
	@Test(priority = 1)
	public void query() {
		b.enterQuery(query, enter);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		link = b.link();
		boolean linkDisp = link.isDisplayed();
		Assert.assertTrue(linkDisp);
	}
	@Test(priority = 2)
	public void newTab() {
		b.newTab(newTab);
		windows = driver.getWindowHandles();
		it = windows.iterator();
		Assert.assertTrue(it.hasNext());
	}
	@Test(priority = 3)
	public void title() {
		windows = driver.getWindowHandles();
		String MainWindow = driver.getWindowHandle();
		it = windows.iterator();
		while (it.hasNext()) {
			String ChildWindow = it.next();
			if (!MainWindow.equalsIgnoreCase(ChildWindow)) {
				driver.switchTo().window(ChildWindow);
			}
		}
		String acTitle = driver.getTitle();
		String exTitle = "Why we need another MASA Conference (BANNED PREACHING) - Steven Anderson";
		Assert.assertEquals(acTitle, exTitle);
	}
	@Test(priority = 4, dataProvider="Provider")
	public static void screen(String file) throws Exception {
		TakesScreenshot scrShot = ((TakesScreenshot)driver);
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile = new File(file);
		FileUtils.copyFile(SrcFile, DestFile);
	}
	@AfterTest(alwaysRun = true)
	public void terminate() {
		driver.quit();
	}
	@DataProvider(name="Provider")
    public Object[] getData() {
    return new Object[] 
    	{"d:\\arch\\2\\IT\\masa.jpg"};
	}
}
