package testCases;

import static org.testng.Assert.assertEquals;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import base.TestBase;

public class PrivacyPolicyTests extends TestBase {
	
	@Test
	public void validatePrivacyPolicyText() throws IOException {
		otherActions.navigateTo(config.getProperty("pageUrl") + "/index.html");
		elementActions.scrollDown("indexPagePrivacyPolicyLink_CLASS");
		elementActions.click("indexPagePrivacyPolicyLink_CLASS");
		String filePathActual = System.getProperty("user.dir") + "\\src\\test\\resources\\testfiles\\PrivacyPolicyText.txt";
		otherActions.writeToFile(filePathActual, otherActions.getTextOFAllElements(), false);
		Path actualFile = Paths.get(filePathActual);
		Path expextedFile = Paths.get(System.getProperty("user.dir") + "\\src\\test\\resources\\testfiles\\ExpectedPrivacyPolicyText.txt");
		byte[] actual = Files.readAllBytes(actualFile);
        byte[] expected = Files.readAllBytes(expextedFile);
		assertEquals(actual.length, expected.length);
		extentTest.log(LogStatus.INFO, "Checked if the expected file (path: " + System.getProperty("user.dir") + "\\src\\test\\resources\\testfiles\\ExpectedPrivacyPolicyText.txt"  + ") is the same as the file where we wrote the datas previously");
		otherActions.emptyFile(filePathActual);
	}

}
