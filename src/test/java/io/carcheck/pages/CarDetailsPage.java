package io.carcheck.pages;

import io.carcheck.modal.Car;
import io.cucumber.java8.Ca;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.sound.midi.Soundbank;
import java.io.ByteArrayInputStream;

@Component
public class CarDetailsPage extends Page {


    @FindBy(xpath = "//dt[contains(text(),'Registration')]/parent::dl/dd")
    WebElement txtRegistration;

    @FindBy(xpath = "//dt[contains(text(),'Make')]/parent::dl/dd")
    WebElement txtMake;

    @FindBy(xpath = "//dt[contains(text(),'Model')]/parent::dl/dd")
    WebElement txtModel;

    @FindBy(xpath = "//dt[contains(text(),'Colour')]/parent::dl/dd")
    WebElement txtColor;

    @FindBy(xpath = "//dt[contains(text(),'Year')]/parent::dl/dd")
    WebElement txtYear;

    @FindBy(partialLinkText = "")
    WebElement btnTryAgain;

    public void openURL(String url) {
        driver.navigate().to(url);
        new WebDriverWait(driver, 10).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

    }

    public Car getCarDetails() {
        if (!vehicleFound()) {
            Allure.addAttachment("Not found", new ByteArrayInputStream(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)));
            return null;
        }

        return new Car(txtRegistration.getText(),
                txtMake.getText(),
                txtModel.getText(),
                txtColor.getText(),
                txtYear.getText());
    }

    private boolean vehicleFound() {
        boolean found = true;
        for (int count = 0; count < 10; count++) {
            try {
                driver.findElement(By.partialLinkText("Try Again"));
                found = false;
                break;
            } catch (StaleElementReferenceException ex) {

            } catch (NoSuchElementException e) {
                break;
            }
        }
        return found;
    }

}
