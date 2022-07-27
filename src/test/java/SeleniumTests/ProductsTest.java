package SeleniumTests;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProductsTest {

    public WebDriver driver;

    @Before
    public void init(){
        System.setProperty("webdriver.chrome.driver","src/test/java/chromedriver1.exe");
        driver = new ChromeDriver();

        String url = "http://localhost:4200/login";

        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    @Test
    public void addProductSuccessfully() throws InterruptedException {
        goToProductsTable();

        Assert.assertEquals("http://localhost:4200/products",driver.getCurrentUrl());

        WebElement newProductButton = driver.findElement(By.xpath("/html/body/app-root/app-products/div/p-toolbar/div/div/button[1]/span[1]"));
        newProductButton.click();

        WebElement name = driver.findElement(By.xpath("//*[@id=\"name\"]"));
        name.clear();
        String generatedString = RandomStringUtils.randomAlphabetic(10);
        name.sendKeys(generatedString);
        Thread.sleep(1000);

        WebElement description = driver.findElement(By.xpath("//*[@id=\"description\"]"));
        description.clear();
        description.sendKeys("Test Item");
        Thread.sleep(1000);

        WebElement dropdown = driver.findElement(By.id("status"));
        dropdown.click();

        WebElement option=driver.findElement(By.xpath("//*[@id=\"pr_id_10_list\"]/p-dropdownitem[1]/li"));
        option.click();

        WebElement saveButton=driver.findElement(By.xpath("/html/body/app-root/app-products/p-dialog[1]/div/div/div[3]/button[2]/span[1]"));
        saveButton.click();

        Thread.sleep(1000);

        WebElement search =driver.findElement(By.xpath("//*[@id=\"pr_id_5-table\"]/thead/tr[2]/th[2]/p-columnfilter/div/p-columnfilterformelement/input"));
        search.sendKeys(generatedString);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"pr_id_5-table\"]/thead/tr[2]/th[2]/p-columnfilter/div/p-columnfilterformelement/input")).sendKeys(Keys.ENTER);

        WebElement viewButton = driver.findElement((By.xpath("//*[@id=\"pr_id_5-table\"]/tbody/tr[1]/td[5]/button[1]/span[1]")));
        viewButton.click();

        WebElement viewName=driver.findElement(By.xpath("//*[@id=\"nameV\"]"));
        Assert.assertEquals(generatedString,viewName.getAttribute("value"));

    }

    @Test
    public void editProductSuccessfully() throws InterruptedException {
        goToProductsTable();

        Thread.sleep(1000);
        WebElement editButton = driver.findElement((By.xpath("//*[@id=\"pr_id_5-table\"]/tbody/tr[1]/td[5]/button[2]/span[1]")));
        editButton.click();

        WebElement name = driver.findElement(By.xpath("//*[@id=\"name\"]"));
        name.clear();
        String generatedString = RandomStringUtils.randomAlphabetic(10);
        name.sendKeys(generatedString);
        Thread.sleep(1000);

        WebElement save=driver.findElement(By.xpath("/html/body/app-root/app-products/p-dialog[1]/div/div/div[3]/button[2]/span[1]"));
        save.click();
        Thread.sleep(1000);

        WebElement search =driver.findElement(By.xpath("//*[@id=\"pr_id_5-table\"]/thead/tr[2]/th[2]/p-columnfilter/div/p-columnfilterformelement/input"));
        search.sendKeys(generatedString);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"pr_id_5-table\"]/thead/tr[2]/th[2]/p-columnfilter/div/p-columnfilterformelement/input")).sendKeys(Keys.ENTER);

        WebElement viewButton = driver.findElement((By.xpath("//*[@id=\"pr_id_5-table\"]/tbody/tr[1]/td[5]/button[1]/span[1]")));
        viewButton.click();

        WebElement viewName=driver.findElement(By.xpath("//*[@id=\"nameV\"]"));
        Assert.assertEquals(generatedString,viewName.getAttribute("value"));
    }


    public void goToProductsTable() throws InterruptedException {
        login();
        Thread.sleep(1000);
        WebElement productsButton = driver.findElement(By.xpath("/html/body/app-root/div/app-navigation/div/a[2]"));
        productsButton.click();

        Assert.assertEquals("http://localhost:4200/products",driver.getCurrentUrl());
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
