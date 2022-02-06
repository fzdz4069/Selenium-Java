package testing;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Testing_url {
	private static WebDriver driver = null;

    public static void main(String[] args) {
    	System.setProperty("webdriver.chrome.driver","d:\\soft\\chromedriver.exe");
        String homePage = "http://www.donlope.net/fz/lyrics/index.html";
        String url = "";
        String domain = "http://www.donlope.net/";
        HttpURLConnection huc = null;
        int respCode = 200;
        
        driver = new ChromeDriver();
        driver.manage().window().maximize();     
        driver.get(homePage);
        
        List<WebElement> links = driver.findElements(By.xpath("//*[@class='album']"));       
        Iterator<WebElement> it = links.iterator();
        
        while(it.hasNext()){            
            url = it.next().getAttribute("href");
            String title = it.next().getText();
            System.out.println(title);
            System.out.println(url);
        
            if(url == null || url.isEmpty()){
            	System.out.println("URL is either not configured or empty");
                continue;
            }            
            if(!url.startsWith(domain)){
                System.out.println("URL belongs to another domain.");
                continue;
            }            
            try {
                huc = (HttpURLConnection)(new URL(url).openConnection());
                huc.setRequestMethod("HEAD");
                huc.connect();
                respCode = huc.getResponseCode();
                
                if(respCode >= 400){
                    System.out.println("broken link");
                }
                else{
                    System.out.println("valid link");
                }      
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }        
        driver.quit();
        System.exit(0);
    }

}
