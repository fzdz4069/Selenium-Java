package testNG;

import org.testng.annotations.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.*;
import java.io.IOException;
import java.lang.NullPointerException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Collections;
import java_basics.WriteXls;
import java_basics.ReadXls;

public class TestHtmlXls {
	public WebDriver driver;
	public String homePage = "file:///D:/temp/fz_html_3/main/index.html";
	public String newTab = Keys.chord(Keys.CONTROL, Keys.ENTER);
	public List <String> title;
	public int size;
	public String[] text;
	public String path = "d:\\arch\\2\\IT\\fz_html_3\\";
	WriteXls write;
	ReadXls read;
	
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
		write = new WriteXls();
        int w = 0;
        int nw = 0;
        int dt = 0;
        int un = 0;
        try {
        	for (int a = 0; a < size; a++) {
        		if (title.get(a).equalsIgnoreCase(text[a])) {
        			String[] row = {text[a], "Link works."};
        			write.writeExcel(path, "test.xlsx", "HTML test", row);
        			w++;
        		} else if (title.get(a).contains(text[a].subSequence(0, 5).toString()) || 
        				text[a].contains(title.get(a).subSequence(0, 5).toString())) {
        			String[] row = {text[a], "Link works, different title."};
        			write.writeExcel(path, "test.xlsx", "HTML test", row);
        			dt++;
        		} else if (title.get(a).startsWith("file")) {
        			String[] row = {text[a], "Link doesn't work."};
        			write.writeExcel(path, "test.xlsx", "HTML test", row);
        			nw++;
        		} else {
        			String[] row = {text[a], "Unexpected error."};
        			write.writeExcel(path, "test.xlsx", "HTML test", row);
        			un++;
        		}
        	}
        } catch (NullPointerException ex) {  
        }
        String wS = String.valueOf(w);
        String nwS = String.valueOf(nw);
        String dtS = String.valueOf(dt);
        String unS = String.valueOf(un);
        String[] lw = {"Links working:", wS};
        String[] lnw = {"Links not working:", nwS};
        String[] dif = {"Different titles:", dtS};
        String[] unx = {"Unexpected errors:", unS};
        
        write.writeExcel(path, "test.xlsx", "HTML test", lw);
        write.writeExcel(path, "test.xlsx", "HTML test", lnw);
        write.writeExcel(path, "test.xlsx", "HTML test", dif);
        write.writeExcel(path, "test.xlsx", "HTML test", unx);       
	}
	@Test(priority = 3)
	public void read() throws IOException {
		read = new ReadXls();
		read.readExcel(path, "test.xlsx", "HTML test");
	}
	@AfterTest(alwaysRun = true)
	public void close() {
		driver.quit();
	}
}
