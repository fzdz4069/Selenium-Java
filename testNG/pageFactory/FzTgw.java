package pageFactory;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class FzTgw {
	WebDriver driver;
	@FindBy(partialLinkText="Wazoo")
	WebElement tgw;
	@FindBy(partialLinkText="Calvin")
	WebElement fc;
	@FindBy(partialLinkText="Cletus")
	WebElement caa;
	@FindBy(partialLinkText="Eat")
	WebElement etq;
	@FindBy(partialLinkText="Relief")
	WebElement br;
	
	public FzTgw(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public void click() {
		etq.click();
	}
	
	public String getText1() {
		return tgw.getText();
	}
	
	public String getText2() {
		return fc.getText();
	}
	
	public String getText3() {
		return caa.getText();
	}
	
	public String getText4() {
		return etq.getText();
	}
	
	public String getText5() {
		return br.getText();
	}

}
