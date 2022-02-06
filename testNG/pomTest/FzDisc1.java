package pomTest;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.*;

import pageFactory.FzAlbums;
import pageFactory.FzTgw;
/*
import pages.FzAlbums;
import pages.FzTgw;*/

public class FzDisc1 {
	WebDriver driver;
	FzAlbums fzA;
	FzTgw fzT;
	
	@BeforeTest
	public void start() {
		System.setProperty("webdriver.chrome.driver","d:\\soft\\chromedriver.exe");
		driver = new ChromeDriver();
		fzA = new FzAlbums(driver);
		fzT = new FzTgw(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("http://www.donlope.net/fz/lyrics/index.html");
	}
	@Test(priority = 0)
	public void clickLnk() {
		String text = fzA.getTextW();
		Reporter.log(text);
		fzA.click1();
	}
	@Test(priority = 1)
	public void getText() {
		String [] songs = new String [5];
		songs [0] = fzT.getText1();
		songs [1] = fzT.getText2();
		songs [2] = fzT.getText3();
		songs [3] = fzT.getText4();
		songs [4] = fzT.getText5();
		for (int i = 0; i < 5; i++) {
			Reporter.log(songs[i]);
		}
	}
	@Test(priority = 2)
	public void etqUrl() {
		fzT.click();
		String url = driver.getCurrentUrl();
		Reporter.log(url);
	}
	
	@AfterTest
	public void close() {
		driver.close();
	}
}
