package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

import base.TestBase;

public class OtherActions extends TestBase {
	
	public void doLogIn(String email, String password) {
		elementActions.click("loginPageEmailInput_XPATH");
		elementActions.type("loginPageEmailInput_XPATH", email, true);
		elementActions.click("loginPagePasswordInput_XPATH");
		elementActions.type("loginPagePasswordInput_XPATH", password, true);
		elementActions.click("loginPageLogInButton_XPATH");
	}
	
	public void doLogOut() {
		driver.get(config.getProperty("pageUrl") + "/admin.php");
		elementActions.click("adminPageLogOutButton_XPATH");
	}
	
	public void fillFieldsToCreateBlog(String title, String description, String url, String filePath, String text) {
		elementActions.click("adminPageCreateNewBlogButton_XPATH");
		elementActions.click("bcwBlogTitleInput_ID");
		elementActions.type("bcwBlogTitleInput_ID", title, true);
		elementActions.click("bcwBlogDescriptionInput_ID");
		elementActions.type("bcwBlogDescriptionInput_ID", description, true);
		elementActions.click("bcwBlogUrlInput_ID");
		elementActions.type("bcwBlogUrlInput_ID", url, true);
		elementActions.type("bcwBlogUploadImageInput_ID", System.getProperty("user.dir") + filePath, false);
		elementActions.switchToIframe("bcwBlogTextEditorIFrame_XPATH");
		elementActions.click("bcwBlogTextEditorInput_XPATH");
		elementActions.type("bcwBlogTextEditorInput_XPATH", text, true);
		elementActions.switchBackFromIFrame();
		elementActions.click("bcwSaveBlogButton_ID");
	}
	
	public void deleteFirstBlog() {
		elementActions.scrollDown("adminPageContinueReadArrow_XPATH");
		elementActions.click("adminPageFirstBlogDeleteButton_CLASS");
		clickOkOnAlert();
	}
	
	public void goBack() {
		driver.navigate().back();
		extentTest.log(LogStatus.INFO, "Went back to previous page: " + driver.getCurrentUrl());
	}
	
	public void clickOkOnAlert() {
		driver.switchTo().alert().accept();
		extentTest.log(LogStatus.INFO, "Clicked \"OK\" on the alert");
	}

	public boolean isElementPresent(String locator) {
		boolean result = false;
		try {
			elementActions.findElementBy(locator);
			result = true;
		} catch (NoSuchElementException e) {
			result = false;
		}
		extentTest.log(LogStatus.INFO, "Checked if an element was present: " + locator + ". Was it present? " + result);
		return result;
	}
	
	public boolean isElementPresent(By by) {
		boolean result = false;
		try {
			driver.findElement(by);
			result = true;
		} catch (NoSuchElementException e) {
			result = false;
		}
		extentTest.log(LogStatus.INFO, "Checked if an element was present: " + by + ". Was it present? " + result);
		return result;
	}

	public String getCurrentUrl() {
		extentTest.log(LogStatus.INFO, "Checked current url: " + driver.getCurrentUrl());
		return driver.getCurrentUrl();
	}
	
	public int getPageResponse(String pageUrl) throws IOException {
		URL url = new URL(pageUrl);
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.setRequestMethod("GET");
		connection.connect();
		extentTest.log(LogStatus.INFO, "Got page http response code: " + connection.getResponseCode());
		return connection.getResponseCode();
	}
	
	public int getPageNumber(String locator) {
		extentTest.log(LogStatus.INFO, "Got the total pages of the list: " + elementActions.findElementsBy(locator).size());
		return elementActions.findElementsBy(locator).size();
	}
	
	public void navigateTo(String url) {
		driver.get(url);
		extentTest.log(LogStatus.INFO, "Navigated to: " + url);
	}
	
	public void writeToFile(String path, String text, boolean append) {
		try {
		    FileWriter myWriter = new FileWriter(path, append);
		    myWriter.write(text);
		    extentTest.log(LogStatus.INFO, "Wrote to " + path + " : " + text);
		    myWriter.close();
		} catch (IOException e) {
			extentTest.log(LogStatus.INFO, "Writing to file failed: " + path);
		    e.printStackTrace();
		}
	}
	
	public String readFile(String path) {
		String data = "";
		try {
			File myObj = new File(path);
		    Scanner myReader = new Scanner(myObj);
		    while (myReader.hasNextLine()) {
		    	data += myReader.nextLine() + "\r\n";
		    }
		    extentTest.log(LogStatus.INFO, "Read data from file: " + path);
		    myReader.close();
		} catch (FileNotFoundException e) {
			extentTest.log(LogStatus.INFO, "Reading from file failed: " + path);
			e.printStackTrace();
		}
		return data;
	}
	
	public String getTextOFAllElements() {
		List<WebElement> allElements = driver.findElements(By.className("text-costum"));
		String result = "";
		for(WebElement element: allElements) {
			result += element.getText();
		}
		return result;
	}
	
	public void emptyFile(String path) {
		try {
			PrintWriter writer = new PrintWriter(new File(path));
			writer.print("");
			extentTest.log(LogStatus.INFO, "Emptied test file: " + path);
			writer.close();
		} catch (FileNotFoundException e) {
			extentTest.log(LogStatus.INFO, "Failed to empty test file: " + path);
			e.printStackTrace();
		}

	}
	
	public List<String> getUsers(String locator){
		List<WebElement> elementsList = elementActions.findElementsBy(locator);
		List<String> result = new ArrayList<>();
		for(int i = 0; i < elementsList.size(); i++) {
			result.add(elementsList.get(i).getText());
		}
		extentTest.log(LogStatus.INFO, "Saved listed registered users to a list: " + result);
		return result;
	}

}
