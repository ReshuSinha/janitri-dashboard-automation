package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    protected WebDriver driver;
    private final WebDriverWait wait;

    // Locators
    private final By userIdInput = By.xpath("//input[@id='formEmail']");
    private final By passwordInput = By.xpath("//input[@id='formPassword']");
    private final By loginButton = By.xpath("//button[@class='login-button']");
    private final By eyeToggle = By.xpath("//img[@class='passowrd-visible']");
    private final By errorMessage = By.xpath("//p[text()='Invalid Credentials']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterUserId(String userId) {
        driver.findElement(userIdInput).clear();
        driver.findElement(userIdInput).sendKeys(userId);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickLogin() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(loginButton));
        driver.findElement(loginButton).click();
    }

    public boolean isPasswordMasked() {
        return driver.findElement(passwordInput).getAttribute("type").equals("password");
    }

    public void togglePasswordVisibility() {
        driver.findElement(eyeToggle).click();
    }

    public String getErrorMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            return driver.findElement(errorMessage).getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean areElementsVisible() {
        return driver.findElement(userIdInput).isDisplayed()
                && driver.findElement(passwordInput).isDisplayed()
                && driver.findElement(loginButton).isDisplayed()
                && driver.findElement(eyeToggle).isDisplayed();
    }

    public boolean verifyAndPrintError(String context) {
        String error = getErrorMessage();
        System.out.println("[" + context + "] Error displayed: " + error);
        return error != null && !error.isEmpty();
    }
}
