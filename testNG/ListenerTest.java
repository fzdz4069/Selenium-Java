package testNG;

import org.testng.ITestContext;		
import org.testng.ITestListener;		
import org.testng.ITestResult;
import org.testng.Reporter;

public class ListenerTest implements ITestListener {
	@Override		
    public void onFinish(ITestContext Result) {					 		
    }		

    @Override		
    public void onStart(ITestContext Result) {							
    }		

    @Override		
    public void onTestFailedButWithinSuccessPercentage(ITestResult Result) {					  		
    }		

    @Override		
    public void onTestFailure(ITestResult Result) {					
    	Reporter.log("Testcase failed: " + Result.getName());
    }		

    @Override		
    public void onTestSkipped(ITestResult Result) {					
    	Reporter.log("Testcase skipped: " + Result.getName());	        		
    }		

    @Override		
    public void onTestStart(ITestResult Result) {
    	Reporter.log(Result.getName() + " test case started.");       		
    }		

    @Override		
    public void onTestSuccess(ITestResult Result) {					
    	Reporter.log("Testcase passed: " + Result.getName());	
        		
    }
}
