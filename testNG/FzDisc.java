package testNG;

import org.testng.annotations.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import org.openqa.selenium.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.APPEND;

public class FzDisc {
	public WebDriver driver;
	public String hp = "http://www.donlope.net/fz/lyrics/index.html";
	public List <WebElement> links;
	public Path path = Path.of("b:\\IT\\");
	public String start = "<html>\n"
			+ "<head>\n"
			+ "<title>FZ Discography</title>\n"
			+ "</head>\n"
			+ "<body>\n"
			+ "<h1>FZ Discography</h1>\n"
			+ "<table border=\"1\">\n"
			+ "\t<tbody>";
	public String end = "\t</tbody>\n"
			+ "</table>\n"
			+ "</body>\n"
			+ "</html>";
	public Path file;
	
	@BeforeTest(alwaysRun = true)
	public void launch() throws IOException  {
		System.setProperty("webdriver.chrome.driver","d:\\soft\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(hp);
		links = driver.findElements(By.xpath("//*[@class='album']"));
		file = Files.createFile(path.resolve("fz_disc.html"));
		Files.writeString(file, start);		
	}	
	@Test(priority = 0)
	public void test0() throws IOException {
		for (int i = 0; i < links.size(); i++) {
			String title = links.get(i).getText();
			String link = links.get(i).getAttribute("href");
			int a = i + 1;
			Files.writeString(file, "\t\t<tr>\n"
					+ "\t\t\t<td>"+a+"</td>\n"
					+ "\t\t\t<td>"+title+"</td>\n"
					+ "\t\t\t<td>"+link+"</td>\n"
							+ "\t\t</tr>\n", APPEND);
		}			
	}
	@AfterTest(alwaysRun = true)
	public void terminate() throws IOException {
		Files.writeString(file, end, APPEND);
		driver.close();
	}
}
