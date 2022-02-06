package testing;

import java.util.List;
import java.util.Iterator;		
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Keys;

public class Testing_new_tab {
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver","c:\\soft\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("https://duckduckgo.com/");
		WebElement search = driver.findElement(By.xpath("//*[@id=\"search_form_input_homepage\"]"));
		WebElement submit = driver.findElement(By.xpath("//*[@id=\"search_button_homepage\"]"));
		search.sendKeys("automated testing");
		submit.submit();
		
		List <WebElement> results = driver.findElements(By.partialLinkText("testing"));
		int resNum = results.size();
		System.out.println(resNum);
		for (int lnk = 0; lnk < resNum; lnk++) {
			String link = results.get(lnk).getText();
			System.out.println(link);
			String clicklnk = Keys.chord(Keys.CONTROL, Keys.ENTER);
			results.get(lnk).sendKeys(clicklnk);
		}
			
		String MainWindow = driver.getWindowHandle();
		Set <String> s1 = driver.getWindowHandles();		
	    Iterator <String> i1 = s1.iterator();
	    while(i1.hasNext())	{		
	    	String ChildWindow = i1.next();		
	    	if(!MainWindow.equalsIgnoreCase(ChildWindow)) {
	            driver.switchTo().window(ChildWindow);	                                                                                                           
	        }
	        String url = driver.getCurrentUrl();
	        System.out.println(url);
	    }

		//driver.close();
		System.exit(0);

	}
}
