package testNG;

import org.testng.annotations.*;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class FzDisc_scr {
	@Test(dataProvider="Provider")
	public static void screen(String file, String url) throws Exception {
		System.setProperty("webdriver.chrome.driver","d:\\soft\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.donlope.net/fz/lyrics/" + url);

		TakesScreenshot scrShot = ((TakesScreenshot)driver);
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile = new File("d:\\arch\\2\\IT\\fz_html_3\\" + file);
		FileUtils.copyFile(SrcFile, DestFile);
		
		driver.close();
	}
	@DataProvider(name="Provider")
    public Object[][] getData() {
    return new Object[][] {
    	{"Freak_Out.jpg", "Freak_Out.html"},
    	{"Absolutely_Free.jpg", "Absolutely_Free.html"},
    	{"Uncle_Meat.jpg", "Uncle_Meat.html"},
    	{"Hot_Rats.html", "Hot_Rats.html"},
    	{"Chunga's_Revenge.jpg", "Chunga's_Revenge.html"}
    };    	
	}
}
