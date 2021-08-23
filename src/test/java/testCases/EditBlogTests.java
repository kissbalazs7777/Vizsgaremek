package testCases;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import base.TestBase;

public class EditBlogTests extends TestBase {
	
	@Test(dataProviderClass=utils.DataProviderUtil.class, dataProvider="dataProvider")
    public void editBlogSuccess(String urlToEdit) {
		otherActions.doLogIn(excelReader.getCellData("successfulLogin", 1, 0), excelReader.getCellData("successfulLogin", 1, 1));
		otherActions.fillFieldsToCreateBlog(excelReader.getCellData("createBlogSuccess", 1, 0), excelReader.getCellData("createBlogSuccess", 1, 1), excelReader.getCellData("createBlogSuccess", 1, 2), excelReader.getCellData("createBlogSuccess", 1, 3), excelReader.getCellData("createBlogSuccess", 1, 4));
		elementActions.scrollDown("adminPageContinueReadArrow_XPATH");
		elementActions.click("adminPageEditBlogButton_ID");
		elementActions.clearInput("bewBlogUrlInput_ID");
		elementActions.type("bewBlogUrlInput_ID", urlToEdit, true);
		elementActions.type("bewBlogUploadImageInput_ID", System.getProperty("user.dir") + excelReader.getCellData("createBlogSuccess", 1, 3), false);
		elementActions.click("bewSaveBlogButton_ID");
		softAssert.assertTrue(otherActions.isElementPresent(By.xpath("//a[@id='" + urlToEdit + "blogurl'][@href='blog/" + urlToEdit + ".html']")));
		elementActions.click("adminPageContinueReadArrow_XPATH");
		softAssert.assertEquals(otherActions.getCurrentUrl(), config.getProperty("pageUrl") + "/blog/" + urlToEdit + ".html");
		extentTest.log(LogStatus.INFO, "Compared actual (see above) URL to expected URL: " + config.getProperty("pageUrl") + "/blog/" + urlToEdit + ".html");
		otherActions.goBack();
		otherActions.deleteFirstBlog();
		otherActions.doLogOut();
		softAssert.assertAll();
    }
	
	@Test(dataProviderClass=utils.DataProviderUtil.class, dataProvider="dataProvider")
    public void editBlogUnSuccessTakenUrl(String urlToEdit) {
		otherActions.doLogIn(excelReader.getCellData("successfulLogin", 1, 0), excelReader.getCellData("successfulLogin", 1, 1));
		otherActions.fillFieldsToCreateBlog(excelReader.getCellData("createBlogSuccess", 1, 0), excelReader.getCellData("createBlogSuccess", 1, 1), excelReader.getCellData("createBlogSuccess", 1, 2), excelReader.getCellData("createBlogSuccess", 1, 3), excelReader.getCellData("createBlogSuccess", 1, 4));
		elementActions.scrollDown("adminPageContinueReadArrow_XPATH");
		elementActions.click("adminPageEditBlogButton_ID");
		elementActions.clearInput("bewBlogUrlInput_ID");
		elementActions.type("bewBlogUrlInput_ID", urlToEdit, true);
		elementActions.type("bewBlogUploadImageInput_ID", System.getProperty("user.dir") + excelReader.getCellData("createBlogSuccess", 1, 3), false);
		elementActions.click("bewSaveBlogButton_ID");
		assertTrue(elementActions.findElementBy("bewUrlTakenMsg_ID").isDisplayed());
		extentTest.log(LogStatus.INFO, "Checked if the error message is displayed");
		elementActions.click("bewCloseBlogEditorButton_ID");
		otherActions.deleteFirstBlog();
		otherActions.doLogOut();
    }
	
	@Test
    public void editBlogUnSuccessBlankField() {
		otherActions.doLogIn(excelReader.getCellData("successfulLogin", 1, 0), excelReader.getCellData("successfulLogin", 1, 1));
		otherActions.fillFieldsToCreateBlog(excelReader.getCellData("createBlogSuccess", 1, 0), excelReader.getCellData("createBlogSuccess", 1, 1), excelReader.getCellData("createBlogSuccess", 1, 2), excelReader.getCellData("createBlogSuccess", 1, 3), excelReader.getCellData("createBlogSuccess", 1, 4));
		elementActions.scrollDown("adminPageContinueReadArrow_XPATH");
		elementActions.click("adminPageEditBlogButton_ID");
		elementActions.type("bewBlogUploadImageInput_ID", System.getProperty("user.dir") + excelReader.getCellData("createBlogSuccess", 1, 3), false);
		elementActions.clearInput("bewBlogUrlInput_ID");
		elementActions.click("bewSaveBlogButton_ID");
		assertTrue(elementActions.findElementBy("bewThereAreBlankFieldsError_ID").isDisplayed());
		extentTest.log(LogStatus.INFO, "Checked if the error message is displayed");
		elementActions.click("bewCloseBlogEditorButton_ID");
		otherActions.deleteFirstBlog();
		otherActions.doLogOut();
    }
}
