package testNG;

import org.testng.annotations.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.*;
import java.io.IOException;
import java.lang.NullPointerException;
import java.util.ArrayList;
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.CREATE_NEW;
import static java.nio.file.StandardOpenOption.APPEND;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Collections;

public class TestHtml {
	public WebDriver driver;
	public String homePage = "file:///D:/arch/2/IT/fz_html_3/main/index.html";
	public String newTab = Keys.chord(Keys.CONTROL, Keys.ENTER);
	public List <String> title;
	public int size;
	public String [] text;
	
	@BeforeTest(alwaysRun = true)
	public void start() {
		System.setProperty("webdriver.chrome.driver","d:\\soft\\chromedriver.exe");
    	driver = new ChromeDriver();
    	driver.manage().window().maximize();     
        driver.get(homePage);
	}
	@Test(priority = 0)
	public void openTabs() {
		List <WebElement> links = driver.findElements(By.xpath("//*[@class='album']"));
        size = links.size();
        text = new String [size];
        title = new ArrayList<String>();
        
        for (int i = 0; i < size; i++) {
        	text[i] = links.get(i).getText();
        	links.get(i).sendKeys(newTab);    	    
        }
	}
	@Test(priority = 1)
	public void getTitles() {
		Set <String> windows = driver.getWindowHandles();
        Iterator <String> it = windows.iterator();
        while (it.hasNext()) {		
	    	String ChildWindow = it.next();		
	    	driver.switchTo().window(ChildWindow);
	    	String t = driver.getTitle();
	    	title.add(t); 	    		        
        }
        Collections.reverse(title);
	}
	@Test(priority = 2)
	public void report() throws IOException {
		Path file = Path.of("d:\\arch\\2\\IT\\fz_html_3\\test.xlsx");
        Files.writeString(file, "Link\tTest result\n", CREATE_NEW);
        int w = 0;
        int nw = 0;
        int dt = 0;
        int un = 0;
        try {
        	for (int a = 0; a < size; a++) {
        		if (title.get(a).equalsIgnoreCase(text[a])) {
        			Files.writeString(file, text[a] + "\tLink works.\n", APPEND);
        			w++;
        		} else if (title.get(a).contains(text[a].subSequence(0, 5).toString()) || 
        				text[a].contains(title.get(a).subSequence(0, 5).toString())) {
        			Files.writeString(file, text[a] + "\tLink works, different title.\n", APPEND);
        			dt++;
        		} else if (title.get(a).startsWith("file")) {
        			Files.writeString(file, text[a] + "\tLink doesn't work.\n", APPEND);
        			nw++;
        		} else {
        			Files.writeString(file, text[a] + "\tUnexpected error.\n", APPEND);
        			un++;
        		}
        	}
        } catch (NullPointerException ex) {  
        }
        Files.writeString(file, "\nLinks working: " + w + "\n"
        		 + "Links not working: " + nw + "\n" 
        		 + "Different titles: " + dt + "\n"
        		  + "Unexpected errors: " + un + "\n", APPEND);
	}
	@AfterTest(alwaysRun = true)
	public void close() {
		driver.quit();
	}
}
