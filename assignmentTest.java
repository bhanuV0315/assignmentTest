package day4;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class assignmentTest {

	@SuppressWarnings({ "deprecation" })
	public static void main(String[] args) {
		WebElement displayedEle, readonlyInput1, readonlyInput2, colourDropdown1, colourDropdown2, userField, submitButton, passwordInput, successMessage;
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
		driver.manage().window().maximize();
	
		try {
			driver.get("https://d3pv22lioo8876.cloudfront.net/tiptop/");
			Thread.sleep(3000);
			
			//1. Verify that the text input element with xpath .//input[@name='my-disabled'] is disabled in the form
			displayedEle = driver.findElement(By.xpath("//input[@name='my-disabled']"));
			if(displayedEle.isDisplayed()) {
				System.out.println("Element displayed");
			}
			else {
				System.out.println("Element not displayed");
			}
			
			//2. Verify that the text input with value 'Readonly input' is in readonly state by using 2 xpaths			
			readonlyInput1 = driver.findElement(By.xpath("//input[@value='Readonly input']"));
			readonlyInput2 = driver.findElement(By.name("my-readonly"));
			if(readonlyInput1.getAttribute("readonly").equals("true")) {
				System.out.println("Test True : Readonly input xpath-1 is readonly");
			}else {
				System.out.println("Test Failed : Readonly input xpath-1 is not readonly");
			}
			Thread.sleep(2000);
			if(readonlyInput2!=null && readonlyInput2.getAttribute("readonly")!=null && readonlyInput2.getAttribute("readonly").equals("true")) {
				System.out.println("Test True : Readonly input xpath-2 is readonly");
			}else {
				System.out.println("Test Failed : Readonly input xpath-2 is not readonly");
			}
			
			//3. Verify that the dropdown field to select color is having 8 elements using 2 xpaths
			colourDropdown1 = driver.findElement(By.xpath("//select[@name='my-select']"));
			colourDropdown2 = driver.findElement(By.xpath("//select[@class='form-select']"));
			int dropdownSize1 = colourDropdown1.findElements(By.tagName("option")).size();
			int dropdownSize2 = colourDropdown2.findElements(By.tagName("option")).size();
			if(dropdownSize1 == 8) {
				System.out.println("test pass : dropdown field-1 have 8 options");
			}else {
				System.out.println("test fail : dropdown field-1 dosen't have 8 options");
			}
			if(dropdownSize2 == 8) {
				System.out.println("test pass : dropdown field-2 have 8 options");
			}else {
				System.out.println("test fail : dropdown field-2 dosen't have 8 options");
			}
			
			//4. Verify that the submit button is disabled when no data is entered in Name field
			userField = driver.findElement(By.name("my-name"));
			submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
			userField.clear();
			if(submitButton.isEnabled()) {
				System.out.println("test pass : Submit button enabled");
			}else {
				System.out.println("test failed : Submit button is desabled");
			}
			
			//5. Verify that the submit button enabled when both Name and Password field is entered
			passwordInput = driver.findElement(By.name("my-password"));
			userField.sendKeys("BhanuPrakash");
			passwordInput.sendKeys("Password123");
			if(submitButton.isEnabled()) {
				System.out.println("test pass : Submit button enabled");
			}else {
				System.out.println("test failed : Submit button is desabled");
			}
			
			//6. Verify that on submit of 'Submit' button the page shows 'Received' text
			submitButton.click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Received!']")));
			successMessage = driver.findElement(By.xpath("//p[text()='Received!']"));
			if(successMessage.isDisplayed()) {
				System.out.println("test pass : Received message is displayed");
			}else {
				System.out.println("test failed : Received message is not displayed");
			}
			
			//7. Verify that on submit of form all the data passed to the URL
			Thread.sleep(7000);
			String currentURL = driver.getCurrentUrl();
			if(currentURL.contains("name=BhanuPrakash") && currentURL.contains("password=Password123")) {
				System.out.println("Test pass : Form data is passed in the URL");
			}else {
				System.out.println("Test failed : Form data is not passed in the URL");
			}
			//Close the browser
			driver.quit();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error in try block");
		}
	}

}
