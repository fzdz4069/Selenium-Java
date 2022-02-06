package testing;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Chomikuj_selection {
	public static void main(String [] args) {
		System.setProperty("webdriver.chrome.driver","c:\\soft\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("http://chomikuj.pl/");
		driver.findElement(By.name("Login")).sendKeys("fzdz4069");
		driver.findElement(By.name("Password")).sendKeys("ffzzddzz44006699");
		driver.findElement(By.id("topBar_LoginBtn")).click();
		
		@SuppressWarnings("deprecation")
		WebDriverWait chFileType = new WebDriverWait(driver, 15);
		chFileType.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='FileName']")));
		
		driver.findElement(By.xpath("//input[@name='submitSearchFiles']")).click();
		driver.findElement(By.xpath("//button[text()='Rozumiem']")).click();
		Select chomik = new Select(driver.findElement(By.xpath("//select[@name='FileType']")));
		chomik.selectByValue("video");
		driver.findElement(By.xpath("//input[@id='FileName']")).sendKeys("Zappa");
		driver.findElement(By.xpath("//input[@id='Search']")).click();
	}
}