package testNG;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class WriteFiles {
	public WebDriver driver;
	public String hp = "http://www.donlope.net/fz/lyrics/";
	public String hpAlt = "http://www.donlope.net/fz/btb/";
	public Path main = Path.of("d:\\temp\\fz_html_3\\main\\");
	public Path btb = Path.of("d:\\temp\\fz_html_3\\btb\\");

	@BeforeTest(alwaysRun = true)
	public void start() throws IOException {
		System.setProperty("webdriver.chrome.driver","d:\\soft\\chromedriver.exe");
    	driver = new ChromeDriver();
    	driver.manage().window().maximize();
	}
	@Test(priority = 0)
	public void writeFiles() throws IOException {
		Stream <Path> fileList = Files.list(main);
        List <String> files = new ArrayList<String>();
        Iterator <Path> it = fileList.iterator();
        while (it.hasNext()) {
        	String file = it.next().toString();
        	files.add(file);
        }
        fileList.close();
        String ndirS = main.toString();
        for (int a = 0; a < files.size(); a++) {
        	String fileName = files.get(a).replace(ndirS + "\\", "");
        	driver.get(hp + fileName);
        	String sourceA = driver.getPageSource();
        	Files.writeString(main.resolve(fileName), sourceA);
    	}
	}
	@Test(priority = 1)
	public void writeFilesAlt() throws IOException {
		Stream <Path> fileList = Files.list(btb);
        List <String> files = new ArrayList<String>();
        Iterator <Path> it = fileList.iterator();
        while (it.hasNext()) {
        	String file = it.next().toString();
        	files.add(file);
        }
        fileList.close();
        String ndirS = btb.toString();
        for (int a = 0; a < files.size(); a++) {
        	String fileName = files.get(a).replace(ndirS + "\\", "");
        	driver.get(hpAlt + fileName);
        	String sourceA = driver.getPageSource();
        	Files.writeString(btb.resolve(fileName), sourceA);
    	}
	}	
	@AfterTest(alwaysRun = true)
	public void close() {
		driver.quit();
	}
}
