package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.RevenueCalculatorPage;

public class RevenueCalculatorTest extends BaseTest {

	@Test(priority = 1)
	public void jobAlertTest() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		// Navigate to Revenue Calculator
		homePage.navigateToRevenueCalculator();
		// Adjust the slider

	}
	@Test(priority = 2)
	public void validateScrollToSlider() throws InterruptedException {
		RevenueCalculatorPage calculatorPage = new RevenueCalculatorPage(driver);
		calculatorPage.validateScrollToSlider();
	}
	
	@Test(priority = 3)
	public void adjustSliderToTargetValue() throws InterruptedException {
		RevenueCalculatorPage calculatorPage = new RevenueCalculatorPage(driver);
		// Navigate to Revenue Calculator
		// Adjust the slider
		calculatorPage.adjustSliderToTargetValue(820);
	}

	@Test(priority = 4)
	public void updateSliderValue() throws InterruptedException {
		RevenueCalculatorPage calculatorPage = new RevenueCalculatorPage(driver);
		calculatorPage.updateSliderValue(560);
	}

	@Test(priority = 5)
	public void validateReimbursementAmount() {
		RevenueCalculatorPage calculatorPage = new RevenueCalculatorPage(driver);

		// Select checkBoxes and validate
		calculatorPage.selectCheckbox("CPT-99453");
		calculatorPage.selectCheckbox("CPT-99091");
		calculatorPage.selectCheckbox("CPT-99454");
		calculatorPage.selectCheckbox("CPT-99474");

		// Validate reimbursement amount
		calculatorPage.validateReimbursementAmount("$10746.40");
	}
}
