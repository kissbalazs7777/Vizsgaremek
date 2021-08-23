package testCases;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import base.TestBase;

public class LogoutTests extends TestBase {
	
	@Test
    public void logOut() {
		otherActions.doLogIn(excelReader.getCellData("successfulLogin", 1, 0), excelReader.getCellData("successfulLogin", 1, 1));
		otherActions.doLogOut();
		assertEquals(otherActions.getCurrentUrl(), config.getProperty("pageUrl") + "/login.php");
		extentTest.log(LogStatus.INFO, "Compared actual (see above) URL to expected URL: " + config.getProperty("pageUrl") + "/login.php");
    }
    
    @Test
    public void afterLogoutTryGoBackToPreviusPage() {
    	otherActions.doLogIn(excelReader.getCellData("successfulLogin", 1, 0), excelReader.getCellData("successfulLogin", 1, 1));
    	otherActions.doLogOut();
    	otherActions.goBack();
		assertEquals(otherActions.getCurrentUrl(), config.getProperty("pageUrl")  + "/login.php");
		extentTest.log(LogStatus.INFO, "Compared actual (see above) URL to expected URL: " + config.getProperty("pageUrl") + "/login.php");
    }
    
}
