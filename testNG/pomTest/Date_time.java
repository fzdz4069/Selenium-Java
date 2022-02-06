package pomTest;

import java.time.Duration;
import java.util.Calendar;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pageFactory.Date_time_demo;
import pageFactory.Date_time_pkp;

public class Date_time {
	WebDriver driver;
	Date_time_demo dt;
	Date_time_pkp dtp;
	
	@Test(dataProvider="DP")
	public void test(String url, String dateTime) {
		System.setProperty("webdriver.chrome.driver","d:\\soft\\chromedriver.exe");
		driver = new ChromeDriver();
		boolean b1 = url.startsWith("https://demos");
		boolean b2 = url.startsWith("https://pkp");
		if (b1) {
			dt = new Date_time_demo(driver);
		} else if (b2) {
			dtp = new Date_time_pkp(driver);
		}
		
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
		String date_dd_MM_yyyy[] = (dateTime.split(" ")[0]).split("/");
        int yearDiff = Integer.parseInt(date_dd_MM_yyyy[2])- Calendar.getInstance().get(Calendar.YEAR);
        if (b1) {
        	dt.cookies();
        	dt.selectDate().click();
        	dt.midLink().click();
        } else if (b2) {
        	dtp.cookies();
        	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        	dtp.selectDate().click();
        }       
        
        if(yearDiff != 0) {
            if(yearDiff > 0) {
                for(int i = 0; i < yearDiff; i++) {
                    dt.nextLink().click();
                }
            } else if(yearDiff < 0) {
                for(int i = 0; i < (yearDiff*(-1)); i++) {
                   dt.previousLink().click();
                }
            }
        }
        if (b1) {
        	dt.listAllMonth().get(Integer.parseInt(date_dd_MM_yyyy[1])-1).click();
            dt.listAllDate().get(Integer.parseInt(date_dd_MM_yyyy[0])-1).click();
            dt.selectTime().click();
            List<WebElement> allTime = dt.allTime();
            dateTime = dateTime.split(" ")[1] + " " + dateTime.split(" ")[2];
            
            for (WebElement webElement : allTime) {
                if (webElement.getText().equalsIgnoreCase(dateTime)) {
                    webElement.click();
                }
            }
        } else if (b2) {
        	String hmin[] = (dateTime.split(" ")[1]).split(":");
        	String h = hmin[0];
        	String min = hmin[1];
        	
        	dtp.listAllDate().get(Integer.parseInt(date_dd_MM_yyyy[0])-1).click();
            dtp.selectTime().click();
            dtp.setHour(h);
            dtp.setMin(min);
        }
        
        driver.close();
	}
	@DataProvider(name="DP")
    public Object[][] getData() {
    return new Object[][] {
    	{"https://demos.telerik.com/kendo-ui/datetimepicker/index", "12/07/2014 2:00 PM"},
    	{"https://pkp.pl/pl/home", "20/11/2021 15:00"}
    };
	}
}
