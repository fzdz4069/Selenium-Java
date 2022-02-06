package pomTest;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.*;

import pageFactory.FzAlbums;
import pageFactory.FzHr;
/*
import pages.FzAlbums;
import pages.FzHr;*/

public class FzDisc2 {
	WebDriver driver;
	FzAlbums fzA;
	FzHr fzH;
	
	@BeforeTest
	public void start() {
		System.setProperty("webdriver.chrome.driver","d:\\soft\\chromedriver.exe");
		driver = new ChromeDriver();
		fzA = new FzAlbums(driver);
		fzH = new FzHr(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("http://www.donlope.net/fz/lyrics/index.html");
	}
	@Test(priority = 0)
	public void clickLnk() {
		String text = fzA.getTextH();
		Reporter.log(text);
		fzA.click2();
	}
	@Test
	public void getText() {
		for (int i = 0; i < 6; i++) {
			Reporter.log(fzH.list().get(i).getText());
		}
	}
	@AfterTest
	public void close() {
		driver.close();
	}
}
