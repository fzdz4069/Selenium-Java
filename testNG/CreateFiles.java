package testNG;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.FileAlreadyExistsException;
import java.io.IOException;
import java.util.List;
import org.testng.annotations.*;
import org.testng.Assert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.*;

public class CreateFiles {
	public WebDriver driver;
	public String homePage = "http://www.donlope.net/fz/lyrics/index.html";
	public String hp = "http://www.donlope.net/fz/lyrics/";
	public String hpAlt = "http://www.donlope.net/fz/btb/";
	public String sourceI;
	public Path path = Path.of("d:\\arch\\2\\IT\\fz_html_3\\");
	public Path main;
	public Path index;
	public Path btb;
	public List <WebElement> links;
	
	@BeforeTest(alwaysRun = true)
	public void start() throws IOException {
		System.setProperty("webdriver.chrome.driver","d:\\soft\\chromedriver.exe");
    	driver = new ChromeDriver();
    	driver.manage().window().maximize();     
        driver.get(homePage);
        main = Files.createDirectory(path.resolve("main"));
        btb = Files.createDirectory(path.resolve("btb"));
		index = Files.createFile(main.resolve("index.html"));
	}
	@Test(priority = 0)
	public void hpSource() throws IOException {
		sourceI = driver.getPageSource();		
		boolean ex = Files.exists(index);
		Assert.assertTrue(ex);
        Files.writeString(index, sourceI);
	}
	@Test(priority = 1)
	public void createFiles() throws IOException {
		links = driver.findElements(By.xpath("//*[@class='album']"));       
        for (int i = 0; i < links.size(); i++) {            
            WebElement link = links.get(i);
            String fullLink = link.getAttribute("href");
            
            if (fullLink.startsWith(hp)) {
            	try {
            		String fileName = fullLink.replace(hp, "");
            		Path album = Files.createFile(main.resolve(fileName));
                	Files.writeString(album, fullLink);
                } catch (FileAlreadyExistsException ex) {
                }
            } else if (fullLink.startsWith(hpAlt)) {
            	try {
            		String fileName = fullLink.replace(hpAlt, "");
            		Path album = Files.createFile(btb.resolve(fileName));
                	Files.writeString(album, fullLink);
                } catch (FileAlreadyExistsException ex) {
                }
            }
        }
	}	
	@AfterTest(alwaysRun = true)
	public void close() {
		driver.quit();
	}	
}
