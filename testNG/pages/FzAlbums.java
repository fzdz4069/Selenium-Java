package pages;

import org.openqa.selenium.*;

public class FzAlbums {
	WebDriver driver;
	By tgw = By.partialLinkText("Wazoo");
	By hr = By.partialLinkText("Hot");
 
	public FzAlbums(WebDriver driver) {
		this.driver = driver;
	}
	
	public void click1() {
		driver.findElement(tgw).click();
	}
	
	public void click2() {
		driver.findElement(hr).click();
	}
	
	public String getTextW() {
		return driver.findElement(tgw).getText();
	}
	
	public String getTextH() {
		return driver.findElement(hr).getText();
	}
}
