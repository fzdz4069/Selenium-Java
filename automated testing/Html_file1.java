package testing;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Html_file1 {
	public static void main(String[] args) throws IOException {
    	System.setProperty("webdriver.chrome.driver","c:\\soft\\chromedriver.exe");
    	WebDriver driver = new ChromeDriver();
    	String homePage = "http://www.donlope.net/fz/lyrics/index.html";
    	driver.manage().window().maximize();     
        driver.get(homePage);
        
        String sourceI = driver.getPageSource();
        Path path = Path.of("d:\\arch\\2\\IT");
        Path dir = Files.createDirectory(path.resolve("fz_html_1"));
        Path index = Files.createFile(dir.resolve("index.html"));
        Files.writeString(index, sourceI);

        List <WebElement> links = driver.findElements(By.xpath("//*[@class='album']"));       
        for (int i = 0; i < links.size(); i++) {            
            WebElement link = links.get(i);
            String title = link.getText();
            if (!title.contains("\\") && !title.contains("/") && !title.contains(":") && !title.contains("?") && !title.contains("\"") && !title.contains("*")) {
            	if (i < 10) {
                	Path album = Files.createFile(dir.resolve("00" + i + " " + title + ".html"));
                    Files.writeString(album, title);
                } else if (i >= 10 && i < 100 ) {
                	Path album = Files.createFile(dir.resolve("0" + i + " " + title + ".html"));
                    Files.writeString(album, title);
                } else {
                	Path album = Files.createFile(dir.resolve(i + " " + title + ".html"));
                    Files.writeString(album, title);
                }
            	
            } else {
            	Path album = Files.createFile(dir.resolve(i + ".html"));
            	Files.writeString(album, title);
            }
        }
        String htmlUrl = "file:///" + index;
        driver.get(htmlUrl);
        
        driver.quit();
        System.exit(0);
    }

}
