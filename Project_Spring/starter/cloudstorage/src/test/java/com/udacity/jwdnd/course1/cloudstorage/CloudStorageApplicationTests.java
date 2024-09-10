package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.io.File;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.firefoxdriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new FirefoxDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		assertEquals("Login", driver.getTitle());
	}

	/**
	 * PLEASE DO NOT DELETE THIS method.
	 * Helper method for Udacity-supplied sanity checks.
	 **/
	private void doMockSignUp(String firstName, String lastName, String userName, String password){
		// Create a dummy account for logging in later.

		// Visit the sign-up page.
		WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(2));
		driver.get("http://localhost:" + this.port + "/signup");
		webDriverWait.until(ExpectedConditions.titleContains("Sign Up"));
		
		// Fill out credentials
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputFirstName")));
		WebElement inputFirstName = driver.findElement(By.id("inputFirstName"));
		inputFirstName.click();
		inputFirstName.sendKeys(firstName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputLastName")));
		WebElement inputLastName = driver.findElement(By.id("inputLastName"));
		inputLastName.click();
		inputLastName.sendKeys(lastName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
		WebElement inputUsername = driver.findElement(By.id("inputUsername"));
		inputUsername.click();
		inputUsername.sendKeys(userName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
		WebElement inputPassword = driver.findElement(By.id("inputPassword"));
		inputPassword.click();
		inputPassword.sendKeys(password);

		// Attempt to sign up.
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("buttonSignUp")));
		WebElement buttonSignUp = driver.findElement(By.id("buttonSignUp"));
		buttonSignUp.click();

		/* Check that the sign up was successful. 
		// You may have to modify the element "success-msg" and the sign-up 
		// success message below depening on the rest of your code.
		*/
//		Assertions.assertTrue(driver.findElement(By.id("success-msg")).getText().contains("You successfully signed up!"));
	}

	
	
	/**
	 * PLEASE DO NOT DELETE THIS method.
	 * Helper method for Udacity-supplied sanity checks.
	 **/
	private void doLogIn(String userName, String password)
	{
		// Log in to our dummy account.
		driver.get("http://localhost:" + this.port + "/login");
		WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
		WebElement loginUserName = driver.findElement(By.id("inputUsername"));
		loginUserName.click();
		loginUserName.sendKeys(userName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
		WebElement loginPassword = driver.findElement(By.id("inputPassword"));
		loginPassword.click();
		loginPassword.sendKeys(password);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
		WebElement loginButton = driver.findElement(By.id("login-button"));
		loginButton.click();

		webDriverWait.until(ExpectedConditions.titleContains("Home"));

	}


	private void doLogOut()
	{
		// Log in to our dummy account.
		driver.get("http://localhost:" + this.port + "/home");
		WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout-button")));
		WebElement logoutButton = driver.findElement(By.id("logout-button"));
		logoutButton.click();

	}

	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the 
	 * rest of your code. 
	 * This test is provided by Udacity to perform some basic sanity testing of 
	 * your code to ensure that it meets certain rubric criteria. 
	 * 
	 * If this test is failing, please ensure that you are handling redirecting users 
	 * back to the login page after a succesful sign up.
	 * Read more about the requirement in the rubric: 
	 * https://review.udacity.com/#!/rubrics/2724/view 
	 */
	@Test
	public void testRedirection() {
		// Create a test account
		doMockSignUp("Redirection","Test","RT","123");
		
		// Check if we have been redirected to the log in page.
		assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());
	}

	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the 
	 * rest of your code. 
	 * This test is provided by Udacity to perform some basic sanity testing of 
	 * your code to ensure that it meets certain rubric criteria. 
	 * 
	 * If this test is failing, please ensure that you are handling bad URLs 
	 * gracefully, for example with a custom error page.
	 * 
	 * Read more about custom error pages at: 
	 * https://attacomsian.com/blog/spring-boot-custom-error-page#displaying-custom-error-page
	 */
	@Test
	public void testBadUrl() {
		// Create a test account
		doMockSignUp("URL","Test","UT","123");
		doLogIn("UT", "123");
		
		// Try to access a random made-up URL.
		driver.get("http://localhost:" + this.port + "/some-random-page");
		assertFalse(driver.getPageSource().contains("Whitelabel Error Page"));
	}


	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the 
	 * rest of your code. 
	 * This test is provided by Udacity to perform some basic sanity testing of 
	 * your code to ensure that it meets certain rubric criteria. 
	 * 
	 * If this test is failing, please ensure that you are handling uploading large files (>1MB),
	 * gracefully in your code. 
	 * 
	 * Read more about file size limits here: 
	 * https://spring.io/guides/gs/uploading-files/ under the "Tuning File Upload Limits" section.
	 */
	@Test
	public void testLargeUpload() {
		// Create a test account
		doMockSignUp("Large File","Test","LFT","123");
		doLogIn("LFT", "123");

		// Try to upload an arbitrary large file
		WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(2));
		String fileName = "upload5m.zip";

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fileUpload")));
		WebElement fileSelectButton = driver.findElement(By.id("fileUpload"));
		fileSelectButton.sendKeys(new File(fileName).getAbsolutePath());

		WebElement uploadButton = driver.findElement(By.id("uploadButton"));
		uploadButton.click();
		try {
			webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("success")));
		} catch (org.openqa.selenium.TimeoutException e) {
			System.out.println("Large File upload failed");
		}
		assertFalse(driver.getPageSource().contains("HTTP Status 403 – Forbidden"));

	}
/*
Write a Selenium test that verifies that the home page is not accessible without logging in.
*/
	@Test
	public void accessHomePage() {

		// Try to access home  URL.
		driver.get("http://localhost:" + this.port + "/home");
		assertTrue(driver.getPageSource().contains("login"));
	}

	/*
    Write a Selenium test that signs up a new user,
    logs that user in,
    verifies that they can access the home page,
    then logs out and verifies that the home page is no longer accessible.
    */
	@Test
	public void fullLogCycle() {
		doMockSignUp("URL","Test","UT","123");
		doLogIn("UT", "123");

		driver.get("http://localhost:" + this.port + "/home");
		doLogOut();
		accessHomePage();
	}
	void createnote ()
	{
		// Go to Notes tab
		WebElement notesTab = driver.findElement(By.id("nav-notes-tab"));
		notesTab.click();

		// Click the 'Add a New Note' button
		WebElement addNoteButton = driver.findElement(By.cssSelector("button.btn-info"));
		addNoteButton.click();

		// Fill the note form
		WebElement noteTitleField = driver.findElement(By.id("note-title"));
		WebElement noteDescriptionField = driver.findElement(By.id("note-description"));

		noteTitleField.sendKeys("Test Note Title");
		noteDescriptionField.sendKeys("Test Note Description");

		// Save the new note
		WebElement saveChangesButton = driver.findElement(By.cssSelector("button.btn-primary"));
		saveChangesButton.click();
		// Now wait for the success page to load and check for the success message
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Check for the success message container (with id="containerForMessage")
		WebElement successMessageContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("containerForMessage")));
		assertTrue(successMessageContainer.isDisplayed(), "Success message container is not displayed.");

		// Check that the success message is displayed
		WebElement successMessage = driver.findElement(By.id("resultMessage"));
		assertTrue(successMessage.getText().contains("Your changes were successfully saved."), "Success message is not displayed as expected.");


	}
	/*
Write a Selenium test that logs in an existing user,
creates a note and verifies that the note details are visible in the note list.
*/
	@Test
	public void logthenCreatNote() {
		doMockSignUp("URL","Test","UT","123");
		doLogIn("UT", "123");
		driver.get("http://localhost:" + this.port + "/home");
		createnote();

	}
	/*
Write a Selenium test that logs in an existing user with existing notes,
clicks the edit note button on an existing note
,changes the note data, saves the changes,
and verifies that the changes appear in the note list.
*/
	@Test
	public void editNotes() {
		logthenCreatNote();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// Click the 'here' link to return to the home page
		WebElement homeLink = driver.findElement(By.id("resultHome"));
		homeLink.click();

		// Wait for the home page to load and go to the Notes tab
		WebElement notesTab = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-notes-tab")));
		notesTab.click();

		// Edit the newly created note
		WebElement editNoteButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn-success")));
		editNoteButton.click();

		// Fill in the new details for the note
		WebElement noteTitleField = driver.findElement(By.id("note-title"));
		WebElement noteDescriptionField = driver.findElement(By.id("note-description"));

		noteTitleField.clear();
		noteTitleField.sendKeys("Updated Note Title");

		noteDescriptionField.clear();
		noteDescriptionField.sendKeys("Updated Note Description");

		// Save the updated note
		WebElement saveChangesButton = driver.findElement(By.cssSelector("button.btn-primary"));
		saveChangesButton.click();

		// Now wait for the success page to load again
		WebElement successMessageContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("containerForMessage")));
		assertTrue(successMessageContainer.isDisplayed(), "Success message container is not displayed after editing the note.");

		// Verify the success message is displayed after the edit
		WebElement successMessage = driver.findElement(By.id("resultMessage"));
		assertTrue(successMessage.getText().contains("Your changes were successfully saved."), "Success message is not displayed as expected after editing the note.");
	}
	/*
	Write a Selenium test that logs in an existing user with existing notes,
	clicks the delete note button on an existing note
	, and verifies that the note no longer appears in the note list.
	*/
	@Test
	public void deleteNotes() {
		logthenCreatNote();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// Click the 'here' link to return to the home page
		WebElement homeLink = driver.findElement(By.id("resultHome"));
		homeLink.click();


		// Wait for the home page to load and go to the Notes tab
		WebElement notesTab = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-notes-tab")));
		notesTab.click();
		WebElement deleteButton = notesTab.findElement(By.xpath("//a[@class='btn btn-danger' and contains(@href, 'delete-note')]"));

		// Click the delete button
		deleteButton.click();


		// Wait for the home page to load and go to the Notes tab
		notesTab = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-notes-tab")));
		notesTab.click();

		// Wait for the Notes table to load
		WebElement notesTable = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userTable")));

		// Find all rows in the Notes table
		List<WebElement> noteRows = notesTable.findElements(By.tagName("tr"));

		boolean noteTitleExists = false;

		// Loop through the rows and check for "Test Note Title"
		for (WebElement row : noteRows) {
			String noteTitle = row.findElement(By.tagName("th")).getText();
			System.out.println(row.findElement(By.tagName("th")).getText());
			if (noteTitle.equals("Test Note Title")) {
				noteTitleExists = true;
				break;
			}
		}

		// Assert that "Test Note Title" is not present
		assertFalse(noteTitleExists, "'Test Note Title' should not exist in the notes list after deletion.");
	}
	void createcred ()
	{
		// Go to Cred tab
		WebElement credTab = driver.findElement(By.id("nav-credentials-tab"));
		credTab.click();

		// Click the 'Add a New Cred' button
		WebElement addNCredButton = driver.findElement(By.id("add-credential"));
		addNCredButton.click();

		// Fill the Cred form
		WebElement CredUrlField = driver.findElement(By.id("credential-url"));
		WebElement credUsernameField = driver.findElement(By.id("credential-username"));
		WebElement credPassField = driver.findElement(By.id("credential-password"));

		CredUrlField.sendKeys("TestURL");
		credUsernameField.sendKeys("TestUserName");
		credPassField.sendKeys("Test_Password");

		// Save the new Cred
		WebElement saveChangesButton = driver.findElement(By.id("save-cred"));
		saveChangesButton.click();
		// Now wait for the success page to load and check for the success message
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Check for the success message container (with id="containerForMessage")
		WebElement successMessageContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("containerForMessage")));
		assertTrue(successMessageContainer.isDisplayed(), "Success message container is not displayed.");

		// Check that the success message is displayed
		WebElement successMessage = driver.findElement(By.id("resultMessage"));
		assertTrue(successMessage.getText().contains("Your changes were successfully saved."), "Success message is not displayed as expected.");

	}
	/*
	Write a Selenium test that logs in an existing user, creates a credential and verifies
	that the credential details are visible in the credential list.
	*/
	@Test
	public void createCreden() {
		doMockSignUp("URL","Test","UT","123");
		doLogIn("UT", "123");
		driver.get("http://localhost:" + this.port + "/home");
		createcred();
	}
	/*
Write a Selenium test that logs in an existing user with existing credentials,
clicks the edit credential button on an existing credential, changes the credential data,
 saves the changes, and verifies that the changes appear in the credential list.
*/
	@Test
	public void editCreden() {
		createCreden();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// Click the 'here' link to return to the home page
		WebElement homeLink = driver.findElement(By.id("resultHome"));
		homeLink.click();

		// Wait for the home page to load and go to the Notes tab
		WebElement credTab = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-credentials-tab")));
		credTab.click();

		// Edit the newly created cred
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement editCredButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("edit-cred")));
		editCredButton.click();

		// Fill in the new details for the note
		WebElement credUrlField = driver.findElement(By.id("credential-url"));
		WebElement credUsenameField = driver.findElement(By.id("credential-username"));

		credUrlField.clear();
		credUrlField.sendKeys("Updated Credential URL");

		credUsenameField.clear();
		credUsenameField.sendKeys("Updated Credential Username");

		// Save the updated cred
		WebElement saveChangesButton = driver.findElement(By.id("save-cred"));
		saveChangesButton.click();

		// Now wait for the success page to load again
		WebElement successMessageContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("containerForMessage")));
		assertTrue(successMessageContainer.isDisplayed(), "Success message container is not displayed after editing the note.");

		// Verify the success message is displayed after the edit
		WebElement successMessage = driver.findElement(By.id("resultMessage"));
		assertTrue(successMessage.getText().contains("Your changes were successfully saved."), "Success message is not displayed as expected after editing the note.");

	}
	/*
Write a Selenium test that logs in an existing user with existing credentials,
clicks the delete credential button on an existing credential,
 and verifies that the credential no longer appears in the credential list.
*/
	@Test
	public void deleteCred() {
		createCreden();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// Click the 'here' link to return to the home page
		WebElement homeLink = driver.findElement(By.id("resultHome"));
		homeLink.click();


		// Wait for the home page to load and go to the Notes tab
		WebElement credTab = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-credentials-tab")));
		credTab.click();
		WebElement deleteButton = credTab.findElement(By.xpath("//a[@class='btn btn-danger' and contains(@href, 'delete-credential')]"));

		// Click the delete button
		deleteButton.click();


		// Wait for the home page to load and go to the Cred tab
		credTab = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-credentials-tab")));
		credTab.click();

		// Wait for the Notes table to load
		WebElement notesTable = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credentialTable")));

		// Find all rows in the Cred table
		List<WebElement> noteRows = notesTable.findElements(By.tagName("tr"));

		boolean credUrlExists = false;

		// Loop through the rows and check for "TestURL"
		for (WebElement row : noteRows) {
			String credUrl = row.findElement(By.tagName("th")).getText();
			System.out.println(row.findElement(By.tagName("th")).getText());
			if (credUrl.equals("TestURL")) {
				credUrlExists = true;
				break;
			}
		}

		// Assert that "Test Cred URL" is not present
		assertFalse(credUrlExists, "'TestURL' should not exist in the notes list after deletion.");
	}
}
