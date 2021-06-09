package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TravelPage{

    @FindBy(xpath = "//SPAN[@class='kitt-button__text'][text()='Оформить онлайн']")
    public WebElement sendButton;

    public TravelPage(WebDriver driver){
        PageFactory.initElements(driver, this);

        Wait<WebDriver> wait = new WebDriverWait(driver, 5, 1000);

        WebElement title = driver.findElement(By.xpath("//H1[@class='kitt-heading  page-teaser-dict__header kitt-heading_size_l']"));
        wait.until(ExpectedConditions.visibilityOf(title));

    }


}
