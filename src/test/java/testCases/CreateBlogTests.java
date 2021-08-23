package testCases;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import base.TestBase;

public class CreateBlogTests extends TestBase {
	@Test(dataProviderClass=utils.DataProviderUtil.class, dataProvider="dataProvider")
    public void createBlogSuccess(String title, String description, String url, String filePath, String text) {
		otherActions.doLogIn(excelReader.getCellData("successfulLogin", 1, 0), excelReader.getCellData("successfulLogin", 1, 1));
		otherActions.fillFieldsToCreateBlog(title, description, url, filePath, text);
		softAssert.assertTrue(otherActions.isElementPresent("creBlogCreatedSuccMsg_ID"));
		elementActions.scrollDown("adminPageContinueReadArrow_XPATH");
		softAssert.assertTrue(otherActions.isElementPresent(By.xpath("//p[@id='" + url + "blogtitle'][text()='" + title + "']")));
		softAssert.assertTrue(otherActions.isElementPresent(By.xpath("//p[@id='" + url + "blogdesc'][text()='" + description + "']")));
		softAssert.assertTrue(otherActions.isElementPresent(By.xpath("//a[@id='" + url + "blogurl'][@href='blog/" + url + ".html']")));
		elementActions.click("adminPageContinueReadArrow_XPATH");
		softAssert.assertTrue(otherActions.isElementPresent(By.xpath("//p[text()='" + text + "']")));
		otherActions.goBack();
		otherActions.deleteFirstBlog();
		otherActions.doLogOut();
		softAssert.assertAll();
    }
	
	@Test(dataProviderClass=utils.DataProviderUtil.class, dataProvider="dataProvider")
    public void createBlogLeavingBlankField(String title, String description, String url, String filePath, String text) {
		otherActions.doLogIn(excelReader.getCellData("successfulLogin", 1, 0), excelReader.getCellData("successfulLogin", 1, 1));
		otherActions.fillFieldsToCreateBlog(title, description, url, filePath, text);
		assertTrue(elementActions.findElementBy("bcwThereAreBlankFieldsError_ID").isDisplayed());
		extentTest.log(LogStatus.INFO, "Checked if the error message is displayed");
		elementActions.click("bcwCloseBlogEditorButton_ID");
		otherActions.doLogOut();
    }
	
	@Test(dataProviderClass=utils.DataProviderUtil.class, dataProvider="dataProvider")
    public void createBlogUrlAlreadyTaken(String title, String description, String url, String filePath, String text) {
		otherActions.doLogIn(excelReader.getCellData("successfulLogin", 1, 0), excelReader.getCellData("successfulLogin", 1, 1));
		otherActions.fillFieldsToCreateBlog(title, description, url, filePath, text);
		assertTrue(elementActions.findElementBy("bcwUrlTakenMsg_ID").isDisplayed());
		extentTest.log(LogStatus.INFO, "Checked if the error message is displayed");
		elementActions.click("bcwCloseBlogEditorButton_ID");
		otherActions.doLogOut();
    }
	
}
