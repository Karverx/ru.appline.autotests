import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class InsuranceTest {
    WebDriver driver;
    String baseUrl;

    @Before
    public void beforeTest(){
        System.setProperty("webdriver.chrome.driver", "drv/chromedriver.exe");
        baseUrl = "http://www.sberbank.ru/ru/person";
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        //Перейти на страницу http://www.sberbank.ru/ru/person
        driver.get(baseUrl);
    }

    @Test
    public void testInsurance(){
        //Нажать на – Страхование
        driver.findElement(By.xpath("(//DIV[@class='kitt-top-menu__icon-img'])[9]")).click();

        // Нажать на - Путешественников
        driver.findElement(By.xpath("//A[@class='kitt-top-menu__link kitt-top-menu__link_second'][text()='Путешествия']")).click();

        Wait<WebDriver> wait = new WebDriverWait(driver, 5, 1000);
        WebElement title = driver.findElement(By.xpath("//H1[@class='kitt-heading  page-teaser-dict__header kitt-heading_size_l']"));
        wait.until(ExpectedConditions.visibilityOf(title));

        //Проверить наличие на странице заголовка – H1 Страхование путешественников
        Assert.assertEquals("Страхование путешественников", title.getText());

        //Нажать на – Оформить Онлайн
        driver.findElement(By.xpath("//span[@class='kitt-button__text']")).click();

        //Подождать пока не появится заголовок H2 Страхование путешественников
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//H2[text()='Страхование путешественников']"))));

        ///Нажать кнопку оформить
        driver.findElement(By.xpath("//BUTTON[@class='btn btn-primary btn-large'][text()='Оформить']")).click();


        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("(//LEGEND)[1]"))));

        fillField(By.id("surname_vzr_ins_0"), "Иванов");
        fillField(By.id("name_vzr_ins_0"), "Иван");

        //Ввод дня рождения через fillField
        fillField(By.id("birthDate_vzr_ins_0"), "05.12.1989");


        //Ввод дня рождения через select
        /*

        driver.findElement(By.xpath("(//I[@class='tick'])[1]")).click();

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//SELECT[@class='pika-select pika-select-year']"))));
        new Select(driver.findElement(By.xpath("//SELECT[@class='pika-select pika-select-year']"))).selectByValue("1989");

        driver.findElement(By.xpath("(//I[@class='tick'])[1]")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//SELECT[@class='pika-select pika-select-month']"))));
        new Select(driver.findElement(By.xpath("//SELECT[@class='pika-select pika-select-month']"))).selectByValue("11");

        driver.findElement(By.xpath("(//I[@class='tick'])[1]")).click();
        driver.findElement(By.xpath("//BUTTON[@class='pika-button pika-day'][text()='5']")).click();

         */

        //Страхователь
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//LEGEND[text()='Страхователь']"))));

        fillField(By.id("person_lastName"), "Иванов");
        fillField(By.id("person_firstName"), "Иван");
        fillField(By.id("person_middleName"), "Иванович");
        fillField(By.id("person_birthDate"), "01.01.1990");

        //Ввод паспортных данных
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//LEGEND[text()='Паспортные данные']"))));

        fillField(By.id("passportSeries"), "1234");
        fillField(By.id("passportNumber"), "567890");
        fillField(By.id("documentDate"), "02.02.2010");


        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//INPUT[@id='documentIssue']"))));
        fillField(By.id("documentIssue"), "ТП 007 Города Москвы");

        //Контакты
        fillField(By.id("phone"), "9091234567");

        //Проверки
        Assert.assertEquals("Иванов", driver.findElement(By.id("person_lastName")).getAttribute("value"));
        Assert.assertEquals("Иван", driver.findElement(By.id("person_firstName")).getAttribute("value"));
        Assert.assertEquals("Иванович", driver.findElement(By.id("person_middleName")).getAttribute("value"));
        Assert.assertEquals("ТП 007 Города Москвы", driver.findElement(By.id("documentIssue")).getAttribute("value"));




    }

    @After
    public void afterTest(){
       //driver.quit();

    }

    public void fillField(By locator, String value){
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(value);
    }

}
