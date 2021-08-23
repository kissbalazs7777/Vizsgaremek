package testCases;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import base.TestBase;

public class RegistrationTests extends TestBase{
	
	@Test(dataProviderClass=utils.DataProviderUtil.class, dataProvider="dataProvider")
	public void registerSuccess(String userName, String password) {
		otherActions.doLogIn(excelReader.getCellData("successfulLogin", 1, 0), excelReader.getCellData("successfulLogin", 1, 1));
		elementActions.click("adminPageRegisterButton_ID");
		elementActions.click("adminPageNewRegistrationButton_ID");
		elementActions.type("adminPageRegisterUserName_ID", userName, true);
		elementActions.type("adminPageRegisterPw_ID", password, true);
		elementActions.click("adminPageRegisterPanelSaveButton_ID");
		boolean actual = otherActions.isElementPresent("adminPageSuccesRegistrationMessage_ID");
		softAssert.assertTrue(actual);
		elementActions.click("adminPageRegisterButton_ID");
		elementActions.click("adminPageListRegisteredUsersButton_ID");
		driver.findElement(By.id(String.valueOf(elementActions.findElementsBy("adminPageNumberedButton_CLASS").size()) + "button")).click();
		extentTest.log(LogStatus.INFO, "Clicked on the last numbered button");
		List<String> usersOnLastPage = otherActions.getUsers("adminPageDisplayedUserDatas_CSS");
		actual = false;
		for(String user: usersOnLastPage) {
			if(user.equals(userName)) {
				actual = true;
				break;
			}
		}
		softAssert.assertTrue(actual);
		extentTest.log(LogStatus.INFO, "Checked if the newly registred username appeared on the last page of the registered users list: " + actual);
		driver.findElement(By.id(userName)).click();
		extentTest.log(LogStatus.INFO, "Clicked on the delete icon to delete: " + userName);
		otherActions.clickOkOnAlert();
		otherActions.doLogOut();
		softAssert.assertAll();
	}
	
	@Test(dataProviderClass=utils.DataProviderUtil.class, dataProvider="dataProvider")
	public void registerUnsuccess(String userName, String password) {
		otherActions.doLogIn(excelReader.getCellData("successfulLogin", 1, 0), excelReader.getCellData("successfulLogin", 1, 1));
		elementActions.click("adminPageRegisterButton_ID");
		elementActions.click("adminPageNewRegistrationButton_ID");
		elementActions.type("adminPageRegisterUserName_ID", userName, true);
		elementActions.type("adminPageRegisterPw_ID", password, true);
		elementActions.click("adminPageRegisterPanelSaveButton_ID");
		boolean actual = otherActions.isElementPresent("adminPageUnSuccesRegistrationMessage_ID");
		assertTrue(actual);
		otherActions.doLogOut();
	}
	

}
