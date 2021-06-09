
import org.junit.Assert;

import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;



public class InsuranceTest extends BaseTest{


    @Test
    @Ignore
    public void testInsurance(){
        driver.get(baseUrl);
        Wait<WebDriver> wait = new WebDriverWait(driver, 5, 1000);

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("(//DIV[@class='kitt-top-menu__icon-img'])[9]"))));

        //Нажать на – Страхование
        driver.findElement(By.xpath("(//DIV[@class='kitt-top-menu__icon-img'])[9]")).click();

        // Нажать на - Путешествия
        driver.findElement(By.xpath("//A[@class='kitt-top-menu__link kitt-top-menu__link_second'][text()='Путешествия']")).click();


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

        fillField(By.id("surname_vzr_ins_0"), "Петров");
        fillField(By.id("name_vzr_ins_0"), "Петр");
        //Ввод дня рождения через fillField
        fillField(By.id("birthDate_vzr_ins_0"), "05.12.1989");

        //Страхователь
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("person_lastName")))).click();
        fillField(By.id("person_lastName"), "Иванов");
        fillField(By.id("person_firstName"), "Иван");
        fillField(By.id("person_middleName"), "Иванович");
        fillField(By.id("person_birthDate"), "01.01.1990");

        //Ввод паспортных данных
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("passportSeries")))).click();
        fillField(By.id("passportSeries"), "1234");
        fillField(By.id("passportNumber"), "567890");
        fillField(By.id("documentDate"), "02.02.2010");

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("documentIssue")))).click();
        fillField(By.id("documentIssue"), "ТП 007 Города Москвы");

        //Проверки
        Assert.assertEquals("Петров", driver.findElement(By.id("surname_vzr_ins_0")).getAttribute("value"));
        Assert.assertEquals("Петр", driver.findElement(By.id("name_vzr_ins_0")).getAttribute("value"));
        Assert.assertEquals("05.12.1989", driver.findElement(By.id("birthDate_vzr_ins_0")).getAttribute("value"));


        Assert.assertEquals("Иванов", driver.findElement(By.id("person_lastName")).getAttribute("value"));
        Assert.assertEquals("Иван", driver.findElement(By.id("person_firstName")).getAttribute("value"));
        Assert.assertEquals("Иванович", driver.findElement(By.id("person_middleName")).getAttribute("value"));
        Assert.assertEquals("01.01.1990", driver.findElement(By.id("person_birthDate")).getAttribute("value"));

        driver.findElement(By.xpath("//LABEL[contains(@class, 'active')][text()='Мужской']")).click();

        Assert.assertEquals("1234", driver.findElement(By.id("passportSeries")).getAttribute("value"));
        Assert.assertEquals("567890", driver.findElement(By.id("passportNumber")).getAttribute("value"));
        Assert.assertEquals("02.02.2010", driver.findElement(By.id("documentDate")).getAttribute("value"));
        Assert.assertEquals("ТП 007 Города Москвы", driver.findElement(By.id("documentIssue")).getAttribute("value"));

        driver.findElement(By.xpath("//BUTTON[@class='btn btn-primary page__btn']")).click();
        Assert.assertEquals("При заполнении данных произошла ошибка", driver.findElement(By.xpath("//div[@class=\"alert-form alert-form-error\"]")).getText());

    }


}
