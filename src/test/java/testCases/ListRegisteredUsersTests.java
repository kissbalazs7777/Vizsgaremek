package testCases;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import base.TestBase;

public class ListRegisteredUsersTests extends TestBase {

	@Test(dataProviderClass=utils.DataProviderUtil.class, dataProvider="dataProviderList")
	public void listUsers(List<String> expected) {
		otherActions.doLogIn(excelReader.getCellData("successfulLogin", 1, 0), excelReader.getCellData("successfulLogin", 1, 1));
		elementActions.click("adminPageRegisterButton_ID");
		elementActions.click("adminPageListRegisteredUsersButton_ID");
		List<String> actual = otherActions.getUsers("adminPageUserDatas_CLASS");
		assertEquals(actual, expected);
		extentTest.log(LogStatus.INFO, "Compared to expected list: " + expected);
		elementActions.click("adminPageCloseListedUsersWindow_ID");
		otherActions.doLogOut();
	}
	
	@Test(dataProviderClass=utils.DataProviderUtil.class, dataProvider="dataProviderList")
	public void traversingList(List<String> expected) {
		otherActions.doLogIn(excelReader.getCellData("successfulLogin", 1, 0), excelReader.getCellData("successfulLogin", 1, 1));
		elementActions.click("adminPageRegisterButton_ID");
		elementActions.click("adminPageListRegisteredUsersButton_ID");
		List<String> actual = otherActions.getUsers("adminPageDisplayedUserDatas_CSS");
		int totalPageOfList = otherActions.getPageNumber("adminPageNumberedButton_CLASS");
		for(int i = 1; i < totalPageOfList; i++) {
			elementActions.click("adminPageNextButton_ID");
			actual.addAll(otherActions.getUsers("adminPageDisplayedUserDatas_CSS"));
			extentTest.log(LogStatus.INFO, "The above items have been added to the main list, elements of the main list: " + actual);
		}
		assertEquals(actual, expected);
		extentTest.log(LogStatus.INFO, "Compared to expected list: " + expected);
		elementActions.click("adminPageCloseListedUsersWindow_ID");
		otherActions.doLogOut();
	}
	
}
