package SeleniumTests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class InternationalizationTest {

    public WebDriver driver;

    @Before
    public void init() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","src/test/java/chromedriver1.exe");
        driver = new ChromeDriver();

        String url = "http://localhost:4200/login";

        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        login();
    }

    @Test
    public void defaultLanguage() throws InterruptedException {

        Thread.sleep(100);

        WebElement productsButton= driver.findElement(By.xpath("/html/body/app-root/div/app-navigation/div/a[2]"));
        Assert.assertEquals("Products",productsButton.getText());
        productsButton.click();

        Thread.sleep(100);

        Assert.assertEquals("Manage Items",driver.findElement(By.xpath("//*[@id=\"pr_id_5\"]/div[1]/div/h2")).getText());
        Assert.assertEquals("Description",driver.findElement(By.xpath("//*[@id=\"pr_id_5-table\"]/thead/tr[1]/th[3]")).getText());


    }

    @Test
    public void changeLanguageToRo() throws InterruptedException {

        Thread.sleep(100);

        defaultLanguage();

        Thread.sleep(100);

        WebElement productsButton= driver.findElement(By.xpath("/html/body/app-root/div/app-navigation/div/a[1]"));
        productsButton.click();

        WebElement changeLangButton = driver.findElement(By.xpath("/html/body/app-root/div/app-navigation/div/div/app-change-language/p-button/button/span[1]"));
        changeLangButton.click();
        WebElement changeToRo = driver.findElement(By.xpath("//*[@id=\"pr_id_10-table\"]/tbody/tr[2]"));
        changeToRo.click();

        WebElement goToProductsButton= driver.findElement(By.xpath("/html/body/app-root/div/app-navigation/div/a[2]"));
        Assert.assertEquals("Produse",goToProductsButton.getText());
        goToProductsButton.click();

        Thread.sleep(1000);

        Assert.assertEquals("Nume",driver.findElement(By.xpath("//*[@id=\"pr_id_12-table\"]/thead/tr[1]/th[2]")).getText());
        Assert.assertEquals("Manipulare produse",driver.findElement(By.xpath("//*[@id=\"pr_id_12\"]/div[1]/div/h2")).getText());

    }

    public void login() throws InterruptedException {

        String url = "http://localhost:4200/login";

        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        WebElement username = driver.findElement(By.xpath("//*[@id=\"username\"]"));
        username.clear();
        username.sendKeys("slvp");

        WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        password.clear();
        password.sendKeys("1234");

        WebElement element = driver.findElement(By.id("buttonLogin"));
        element.click();

        System.out.println("Login Button Pressed");

    }

    @After
    public void destroy() throws InterruptedException {
        Thread.sleep(10000);
        driver.close();
    }
}
