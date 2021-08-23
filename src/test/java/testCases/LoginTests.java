package testCases;

import org.testng.annotations.Test;

import base.TestBase;

import static org.testng.Assert.assertEquals;

public class LoginTests extends TestBase {
	@Test(dataProviderClass=utils.DataProviderUtil.class, dataProvider="dataProvider")
    public void successfulLogin(String email, String password, String isLoginSuccessful) {
		otherActions.doLogIn(email, password);
        assertEquals(otherActions.isElementPresent("adminPageLogOutButton_XPATH"), Boolean.parseBoolean(isLoginSuccessful));
        otherActions.doLogOut();
    }
	
	@Test(dataProviderClass=utils.DataProviderUtil.class, dataProvider="dataProvider")
	public void unsuccessfulLogin(String email, String password, String isLoginSuccessful) throws InterruptedException {
		otherActions.doLogIn(email, password);
		assertEquals(otherActions.isElementPresent("loginErrorMessage_XPATH"), Boolean.parseBoolean(isLoginSuccessful));
    }
	
}
