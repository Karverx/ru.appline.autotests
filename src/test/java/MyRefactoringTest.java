import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.MainPage;
import pages.SendAppPage;
import pages.TravelPage;

public class MyRefactoringTest extends BaseTest{

    @Test
    public void newInsurance(){
        driver.get(baseUrl + "/");
        MainPage mainPage = new MainPage(driver);
        mainPage.selectMainMenu("Страхование");
        mainPage.selectSubMenu("Путешествия");

        new TravelPage(driver).sendButton.click();

        SendAppPage sendAppPage = new SendAppPage(driver);
        sendAppPage.checkout.click();
        sendAppPage.fillField("Фамилия1", "Соколов");
        sendAppPage.fillField("Имя1", "Игорь");
        sendAppPage.fillField("Дата рождения1", "05.12.1988");
        sendAppPage.fillField("Фамилия2", "Майров");
        sendAppPage.fillField("Имя2", "Максим");
        sendAppPage.fillField("Отчество2", "Иванович");
        sendAppPage.fillField("Дата рождения2", "03.03.1991");

        sendAppPage.fillField("Серия паспорта", "4321");
        sendAppPage.fillField("Номер паспорта", "098765");
        sendAppPage.fillField("Дата выдачи", "04.04.2011");
        sendAppPage.fillField("Кем выдан", "ТП 777 Города Москвы");

        sendAppPage.sendButton.click();
        sendAppPage.checkErrorMessage("При заполнении данных произошла ошибка");




    }

}
