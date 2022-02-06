package pageFactory;

import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class FzHr {
	WebDriver driver;
	@FindBy(xpath="/html/body/ol//descendant::a")
	List<WebElement> links;
	
	public FzHr(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public List<WebElement> list() {
		return links;
	}
}
