package testNG;

import org.testng.annotations.*;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class Screen {	
	@Test(dataProvider="Provider")
	public static void screen(String fileWithPath) throws Exception {
		System.setProperty("webdriver.chrome.driver","d:\\soft\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://thepreaching.com/");

		TakesScreenshot scrShot = ((TakesScreenshot)driver);
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile = new File(fileWithPath);
		FileUtils.copyFile(SrcFile, DestFile);
		
		driver.close();
	}
	@DataProvider(name="Provider")
    public Object[] getData() {
    return new Object[] 
    	{"d:\\arch\\2\\IT\\fwbc.jpg"};
	}
}
