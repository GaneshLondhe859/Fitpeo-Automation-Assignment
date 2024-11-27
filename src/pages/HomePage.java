package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;
    private By revenueCalculatorLink = By.xpath("//div[text()='Revenue Calculator']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToRevenueCalculator() {
        driver.findElement(revenueCalculatorLink).click();
    }
}
