package testCases;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import base.TestBase;

public class DeleteBlogTests extends TestBase {
	
	@Test
    public void deleteBlog_CheckIfVanishedFromAdminPage() {
		otherActions.doLogIn(excelReader.getCellData("successfulLogin", 1, 0), excelReader.getCellData("successfulLogin", 1, 1));
		otherActions.fillFieldsToCreateBlog(excelReader.getCellData("createBlogSuccess", 1, 0), excelReader.getCellData("createBlogSuccess", 1, 1), excelReader.getCellData("createBlogSuccess", 1, 2), excelReader.getCellData("createBlogSuccess", 1, 3), excelReader.getCellData("createBlogSuccess", 1, 4));
		otherActions.deleteFirstBlog();
		assertFalse(otherActions.isElementPresent(By.xpath("//a[@id='" + excelReader.getCellData("createBlogSuccess", 1, 2) + "blogurl'][@href='blog/" + excelReader.getCellData("createBlogSuccess", 1, 2) + ".html']")));
		otherActions.doLogOut();
    }
	
	@Test
    public void deleteBlog_CheckIfBlog_s_PageDeleted() throws IOException {
		otherActions.doLogIn(excelReader.getCellData("successfulLogin", 1, 0), excelReader.getCellData("successfulLogin", 1, 1));
		otherActions.fillFieldsToCreateBlog(excelReader.getCellData("createBlogSuccess", 1, 0), excelReader.getCellData("createBlogSuccess", 1, 1), excelReader.getCellData("createBlogSuccess", 1, 2), excelReader.getCellData("createBlogSuccess", 1, 3), excelReader.getCellData("createBlogSuccess", 1, 4));
		otherActions.deleteFirstBlog();
		assertTrue(otherActions.getPageResponse(config.getProperty("pageUrl") + "/" + excelReader.getCellData("createBlogSuccess", 1, 2)) >= 400);
		otherActions.doLogOut();
    }
}
