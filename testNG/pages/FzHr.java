package pages;

import java.util.List;
import org.openqa.selenium.*;

public class FzHr {
	WebDriver driver;
	By links = By.xpath("/html/body/ol//descendant::a");
	
	public FzHr(WebDriver driver) {
		this.driver = driver;
	}
	
	public List<WebElement> list() {
		return driver.findElements(links);
	}

}
