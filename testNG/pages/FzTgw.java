package pages;

import org.openqa.selenium.*;

public class FzTgw {
	WebDriver driver;
	By tgw = By.partialLinkText("Wazoo");
	By fc = By.partialLinkText("Calvin");
	By caa = By.partialLinkText("Cletus");
	By etq = By.partialLinkText("Eat");
	By br = By.partialLinkText("Relief");

	public FzTgw(WebDriver driver) {
		this.driver = driver;
	}
	
	public void click() {
		driver.findElement(etq).click();
	}
	
	public String getText1() {
		return driver.findElement(tgw).getText();
	}
	
	public String getText2() {
		return driver.findElement(fc).getText();
	}
	
	public String getText3() {
		return driver.findElement(caa).getText();
	}
	
	public String getText4() {
		return driver.findElement(etq).getText();
	}
	
	public String getText5() {
		return driver.findElement(br).getText();
	}
}
