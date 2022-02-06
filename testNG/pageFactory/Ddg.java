package pageFactory;

import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class Ddg {
	WebDriver driver;
	@FindBy(xpath="//*[contains(@id, 'search_form_input')]")
	WebElement search;
	@FindBy(xpath="//*[contains(@id, 'search_button')]")
	WebElement submit;
	@FindBy(xpath="//*[@id='links']//descendant::a")
	List<WebElement> links;
	
	public Ddg(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public WebElement search() {
		return search;
	}
	public WebElement submit() {
		return submit;
	}
	public List<WebElement> links() {
		return links;
	}
	public void setQuery(String query) {
		search.sendKeys(query);
	}
	public void clickSubmit() {
		submit.click();
	}
	public void enterQuery(String query) {
		this.setQuery(query);
		this.clickSubmit();
	}
}
