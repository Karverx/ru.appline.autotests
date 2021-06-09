package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    @FindBy(xpath = "//DIV[@class='kitt-content']/ul")
    WebElement mainMenu;

    @FindBy(xpath = "//DIV[@class='kitt-top-menu__dropdown kitt-top-menu__dropdown_icons']")
    WebElement subMenu;

    public MainPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void selectMainMenu(String menuItem){
        mainMenu.findElement(By.xpath("//a[@role][contains(text(),'"+ menuItem +"')]")).click();
    }

    public void selectSubMenu(String menuItem){
        subMenu.findElement(By.xpath("//*[contains(text(),'"+ menuItem +"')]")).click();
    }

}
