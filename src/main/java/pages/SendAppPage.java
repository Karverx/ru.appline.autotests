package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class SendAppPage {
    WebDriver driver;

    @FindBy(xpath = "//BUTTON[@class='btn btn-primary btn-large'][text()='Оформить']")
    public WebElement checkout;

    @FindBy(id = "surname_vzr_ins_0")
    public WebElement surname;

    @FindBy(id = "name_vzr_ins_0")
    public WebElement name;

    @FindBy(id = "birthDate_vzr_ins_0")
    public WebElement birthDate;


    @FindBy(id = "person_lastName")
    public WebElement person_lastName;

    @FindBy(id = "person_firstName")
    public WebElement person_firstName;

    @FindBy(id = "person_middleName")
    public WebElement person_middleName;

    @FindBy(id = "person_birthDate")
    public WebElement person_birthDate;


    @FindBy(id = "passportSeries")
    public WebElement passportSeries;

    @FindBy(id = "passportNumber")
    public WebElement passportNumber;

    @FindBy(id = "documentDate")
    public WebElement documentDate;

    @FindBy(id = "documentIssue")
    public WebElement documentIssue;

    @FindBy(xpath = "//BUTTON[contains(@class,'btn btn-primary page__btn')]")
    public WebElement sendButton;



    public SendAppPage(WebDriver driver){
        PageFactory.initElements(driver, this);

        Wait<WebDriver> wait = new WebDriverWait(driver, 5, 1000);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//H2[text()='Страхование путешественников']"))));

        this.driver = driver;
    }


    public void fillField(String fieldName, String value){
        switch (fieldName) {
            case "Фамилия1" -> fillField(surname, value);
            case "Имя1" -> fillField(name, value);
            case "Дата рождения1" -> fillField(birthDate, value);
            case "Фамилия2" -> fillField(person_lastName, value);
            case "Имя2" -> fillField(person_firstName, value);
            case "Отчество2" -> fillField(person_middleName, value);
            case "Дата рождения2" -> fillField(person_birthDate, value);
            case "Серия паспорта" -> fillField(passportSeries, value);
            case "Номер паспорта" -> fillField(passportNumber, value);
            case "Дата выдачи" -> fillField(documentDate, value);
            case "Кем выдан" -> fillField(documentIssue, value);
            default -> throw new AssertionError("Поле '" + fieldName + "' не объявлено на странице");
        }
    }

    protected void fillField(WebElement element, String value) {
        element.click();
        element.clear();
        element.sendKeys(value);
        assertEquals(value, element.getAttribute("value"));
    }

    public void checkErrorMessage(String errorMessage){
        String error = "//div[contains(@class,'alert-form alert-form-error')]";
        String actualValue = driver.findElement(By.xpath(error)).getText();
        org.junit.Assert.assertTrue(String.format("Получено значение [%s]. Ожидалось [%s]", actualValue, errorMessage),
                actualValue.contains(errorMessage));
    }

}
