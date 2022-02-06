package pageFactory;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class FzAlbums {
	WebDriver driver;
	@FindBy(partialLinkText="Wazoo")
	WebElement tgw;
	@FindBy(partialLinkText="Hot")
	WebElement hr;
	
	public FzAlbums(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public void click1() {
		tgw.click();
	}
	
	public void click2() {
		hr.click();
	}
	
	public String getTextW() {
		return tgw.getText();
	}
	
	public String getTextH() {
		return hr.getText();
	}
}
