package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.Assert;

public class LoginSteps {
    WebDriver driver;

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Atif\\Desktop\\Chrome\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("https://the-internet.herokuapp.com/login");
    }

    @When("the user enters a valid username and password")
    public void the_user_enters_a_valid_username_and_password() {
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        usernameField.sendKeys("tomsmith");
        passwordField.sendKeys("SuperSecretPassword!");
    }

    @And("the user clicks the login button")
    public void the_user_clicks_the_login_button() {
        WebElement loginButton = driver.findElement(By.cssSelector(".radius"));
        loginButton.click();
    }

    @Then("the user should be redirected to the homepage")
    public void the_user_should_be_redirected_to_the_homepage() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/secure"));
    }

    @And("a welcome message should be displayed")
    public void a_welcome_message_should_be_displayed() {
        WebElement welcomeMessage = driver.findElement(By.cssSelector(".flash.success"));
        Assert.assertTrue(welcomeMessage.getText().contains("Welcome to the Secure Area"));
        driver.quit();
    }
}
