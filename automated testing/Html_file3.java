package testing;

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

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Keys;


public class Html_file3 {
	public static void main(String[] args) throws IOException {
    	System.setProperty("webdriver.chrome.driver","c:\\soft\\chromedriver.exe");
    	WebDriver driver = new ChromeDriver();
    	String homePage = "file:///D:/arch/2/IT/fz_html_2/index.html";
    	String newTab = Keys.chord(Keys.CONTROL, Keys.ENTER);
    	
    	driver.manage().window().maximize();     
        driver.get(homePage);       
        List <WebElement> links = driver.findElements(By.xpath("//*[@class='album']"));
        int size = links.size();
        String [] text = new String [size];
        List <String> title = new ArrayList<String>();
        
        for (int i = 0; i < size; i++) {
        	text[i] = links.get(i).getText();
        	links.get(i).sendKeys(newTab);
    	    
        }
        Set <String> windows = driver.getWindowHandles();
        Iterator <String> it = windows.iterator();
        while (it.hasNext()) {		
	    	String ChildWindow = it.next();		
	    	driver.switchTo().window(ChildWindow);
	    	String t = driver.getTitle();
	    	title.add(t); 	    		        
        }
        Collections.reverse(title);

        Path file = Path.of("d:\\arch\\2\\IT\\fz_html_2\\test.xlsx");
        Files.writeString(file, "Test results\n", CREATE_NEW);
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
        driver.quit();
        System.exit(0);
	}
}
