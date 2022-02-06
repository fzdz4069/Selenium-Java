package testing;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;
import java.nio.file.FileAlreadyExistsException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Html_file2 {
	public static void main(String[] args) throws IOException, InterruptedException {
    	System.setProperty("webdriver.chrome.driver","c:\\soft\\chromedriver.exe");
    	WebDriver driver = new ChromeDriver();
    	String homePage = "http://www.donlope.net/fz/lyrics/index.html";
    	String hp = "http://www.donlope.net/fz/lyrics/";
    	driver.manage().window().maximize();     
        driver.get(homePage);
        
        String sourceI = driver.getPageSource();
        Path path = Path.of("d:\\arch\\2\\IT");
        Path dir = Files.createDirectory(path.resolve("fz_html_2"));
        Path ndir = Path.of("d:\\arch\\2\\IT\\fz_html_2");
        Path index = Files.createFile(dir.resolve("index.html"));
        Files.writeString(index, sourceI);

        List <WebElement> links = driver.findElements(By.xpath("//*[@class='album']"));
        
        for (int i = 0; i < links.size(); i++) {            
            WebElement link = links.get(i);
            String fullLink = link.getAttribute("href");
            
            if (fullLink.startsWith(hp)) {
            	try {
            		String fileName = fullLink.replace(hp, "");
            		Path album = Files.createFile(dir.resolve(fileName));
                	Files.writeString(album, fullLink);
                } catch (FileAlreadyExistsException ex) {
                }
            } 
        }
        Stream <Path> fileList = Files.list(ndir);
        List <String> files = new ArrayList<String>();
        Iterator <Path> it = fileList.iterator();
        while (it.hasNext()) {
        	String file = it.next().toString();
        	files.add(file);
        }
        fileList.close();
        String ndirS = ndir.toString();
        for (int a = 0; a < files.size(); a++) {
        	String fileName = files.get(a).replace(ndirS + "\\", "");
        	driver.get(hp + fileName);
        	String sourceA = driver.getPageSource();
        	Files.writeString(ndir.resolve(fileName), sourceA);
    		}
        
        driver.quit();
        System.exit(0);
    }

}
