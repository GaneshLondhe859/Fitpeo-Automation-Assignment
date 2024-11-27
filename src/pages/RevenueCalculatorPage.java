package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class RevenueCalculatorPage {
    private WebDriver driver;

    // Locators
    private By slider = By.xpath("//input[@type='range']");
    private By sliderTextField = By.xpath("//input[@type='number']");
    private By reimbursementAmount = By.xpath("(//*[contains(@class,'MuiTypography-root MuiTypography-body2 inter css-1xroguk')])[2]//p");

 // Validate scrolling to slider
    public void validateScrollToSlider() {
        WebElement sliderElement = driver.findElement(slider);
        
        // Scroll into view using JavascriptExecutor
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sliderElement);

        // Verify if the slider is displayed and in view
        Assert.assertTrue(sliderElement.isDisplayed(), "Slider is not visible after scrolling.");
        
    }

    public RevenueCalculatorPage(WebDriver driver) {
        this.driver = driver;
    }

    // Adjust slider to target value
    public void adjustSliderToTargetValue(int targetValue) throws InterruptedException {
        WebElement sliderElement = driver.findElement(slider);
  
        // Get the current slider value and max value
        int currentValue = Integer.parseInt(sliderElement.getAttribute("value"));
        int max = Integer.parseInt(sliderElement.getAttribute("max"));

        // Calculate the offset
        int sliderWidth = 300; // Slider's visual width
        double scale = (double) sliderWidth / max;
        int offsetX = (int) ((targetValue - currentValue) * scale);

        // Move the slider using Actions
        Actions actions = new Actions(driver);
        actions.dragAndDropBy(sliderElement, offsetX, 0).perform();
        Thread.sleep(2000);

        // Fine-tune using keyboard keys if necessary
        int remainingSteps = targetValue - Integer.parseInt(sliderElement.getAttribute("value"));
        for (int i = 0; i < remainingSteps; i++) {
            actions.sendKeys(Keys.ARROW_RIGHT).perform();
            Thread.sleep(500);
        }

        // Assert final value
        int finalValue = Integer.parseInt(sliderElement.getAttribute("value"));
        Assert.assertEquals(finalValue, targetValue, "Slider value does not match the target value.");
    }

    // Update text field and validate slider value
    public void updateSliderValue(int updatedValue) throws InterruptedException {
        WebElement sliderText = driver.findElement(sliderTextField);
        Actions actions = new Actions(driver);

        // Click the input field to focus
        sliderText.click();

        // Clear the field by sending multiple BACK_SPACE keys
        String currentText = sliderText.getAttribute("value");
        for (int i = 0; i < currentText.length(); i++) {
            actions.sendKeys(Keys.BACK_SPACE).perform();
        }

        // Input the new slider value
        sliderText.sendKeys(String.valueOf(updatedValue));
        Thread.sleep(2000);

        // Validate the updated slider value
        int newSliderValue = Integer.parseInt(driver.findElement(slider).getAttribute("value"));
        Assert.assertEquals(newSliderValue, updatedValue, "Updated slider value does not match.");
    }


    // Select checkBoxes dynamically
    public void selectCheckbox(String checkboxText) {
        String dynamicXPath = "//p[text()='" + checkboxText + "']/parent::div//input[@type='checkbox']";
        WebElement checkbox = driver.findElement(By.xpath(dynamicXPath));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
        Assert.assertTrue(checkbox.isSelected(), "Checkbox " + checkboxText + " is not selected.");
    }

    // Validate reimbursement amount
    public void validateReimbursementAmount(String expectedAmount) {
        WebElement amountElement = driver.findElement(reimbursementAmount);
        String actualAmount = amountElement.getText().trim();
        Assert.assertEquals(actualAmount, expectedAmount, "Reimbursement amount does not match.");
    }
}
