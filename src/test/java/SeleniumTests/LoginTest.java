package SeleniumTests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LoginTest {
    public static WebDriver driver;
    WebElement login;

    @Before
    public void init(){
        System.setProperty("webdriver.chrome.driver","src/test/java/chromedriver1.exe");
        driver = new ChromeDriver();

        String url = "http://localhost:4200/login";

        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        login = driver.findElement(By.xpath("/html/body/app-root/app-login/div[3]/div[2]/div/button"));
    }


    @Test
    public void unsuccessfulLoginWhenMissingCredentials() {

        System.out.println("Clicking on the login element in the main page");
        login.click();

        Assert.assertEquals("http://localhost:4200/login",driver.getCurrentUrl());

    }

    @Test
    public void unsuccessfulLoginWhenWrongCredentials() {

        System.out.println("Clicking on the login element in the main page");

        WebElement username = driver.findElement(By.xpath("//*[@id=\"username\"]"));
        username.clear();
        username.sendKeys("slvp");

        WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        password.clear();
        password.sendKeys("12345");
        login.click();

        Assert.assertEquals("http://localhost:4200/login",driver.getCurrentUrl());

    }

    @Test
    public void successfulLoginAndLogout() throws InterruptedException {

        login();
        Thread.sleep(10000);

        Assert.assertNotEquals("http://localhost:4200/login",driver.getCurrentUrl());
        Assert.assertEquals("http://localhost:4200/home",driver.getCurrentUrl());

        Thread.sleep(100);
        logout();
        Assert.assertEquals("http://localhost:4200/login",driver.getCurrentUrl());

    }

    public static void login() throws InterruptedException {

        WebElement username = driver.findElement(By.xpath("//*[@id=\"username\"]"));
        username.clear();
        username.sendKeys("slvp");

        WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        password.clear();
        password.sendKeys("1234");


        //reCaptcha bypass
        /*WebDriverWait wait = new WebDriverWait(driver, 50);

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(
                By.xpath("//iframe[starts-with(@name, 'a-') and starts-with(@src, 'https://www.google.com/recaptcha')]")));


        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='recaptcha-checkbox-border']"))).click();
        System.out.println("Clicked the checkbox");

        Thread.sleep(5000);

        driver.switchTo().defaultContent();*/

        WebElement element = driver.findElement(By.id("buttonLogin"));
        element.click();

        System.out.println("Login Button Pressed");

    }

    public void logout(){

       WebElement logout = driver.findElement(By.xpath("/html/body/app-root/div/app-navigation/div/div/app-logout/p-button/button"));
       logout.click();
       System.out.println("Logout Button Pressed");
    }

    @After
    public void destroy() throws InterruptedException {
        Thread.sleep(10000);
        driver.close();
    }
}
