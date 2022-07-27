package SeleniumTests;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class RegisterTest {

    public static WebDriver driver;
    WebElement login;

    @Before
    public void init() {
        System.setProperty("webdriver.chrome.driver", "src/test/java/chromedriver1.exe");
        driver = new ChromeDriver();

        String url = "http://localhost:4200/login";

        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        login = driver.findElement(By.xpath("/html/body/app-root/app-login/div[3]/div[2]/div/button"));
    }

    @Test
    public void successfulRegistration() throws InterruptedException {
        redirectToRegister();
        Thread.sleep(500);

        String generatedCredentials = RandomStringUtils.randomAlphabetic(10);
        fillRegistrationFields(generatedCredentials);

        WebElement chooseFile = driver.findElement(By.xpath("/html/body/app-root/app-login/div[2]/div[2]/div[1]/input"));
        chooseFile.sendKeys("T:\\50_WORKSPACES\\Backend\\src\\main\\resources\\static\\images.jfif");
        Thread.sleep(500);

        WebElement registerButton = driver.findElement(By.xpath("/html/body/app-root/app-login/div[2]/div[2]/div[2]/button"));
        registerButton.click();
        Thread.sleep(1000);

        login(generatedCredentials, generatedCredentials);
        Thread.sleep(1000);

        Assert.assertEquals("http://localhost:4200/home", driver.getCurrentUrl());
        Assert.assertEquals("Welcome, " + generatedCredentials + " !", driver.findElement(By.xpath("//*[@id=\"dimScreen\"]/div/h2")).getText());


    }

    @Test
    public void failedRegistrationWhenMissingFields() throws InterruptedException {
        redirectToRegister();
        Thread.sleep(500);


        WebElement username = driver.findElement(By.xpath("//*[@id=\"username\"]"));
        username.clear();
        String generatedString = RandomStringUtils.randomAlphabetic(10);
        username.sendKeys(generatedString);
        Thread.sleep(500);

        WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        password.clear();
        password.sendKeys(generatedString);
        Thread.sleep(500);

        WebElement chooseFile = driver.findElement(By.xpath("/html/body/app-root/app-login/div[2]/div[2]/div[1]/input"));
        chooseFile.sendKeys("T:\\50_WORKSPACES\\Backend\\src\\main\\resources\\static\\images.jfif");
        Thread.sleep(500);

        WebElement registerButton = driver.findElement(By.xpath("/html/body/app-root/app-login/div[2]/div[2]/div[2]/button"));
        registerButton.click();
        Thread.sleep(1000);


        Assert.assertEquals("http://localhost:4200/register", driver.getCurrentUrl());
    }

    @Test
    public void failedRegistrationWhenNoProfilePictureChosen() throws InterruptedException {
        redirectToRegister();
        Thread.sleep(500);

        String generatedCredentials = RandomStringUtils.randomAlphabetic(10);
        fillRegistrationFields(generatedCredentials);

        WebElement registerButton = driver.findElement(By.xpath("/html/body/app-root/app-login/div[2]/div[2]/div[2]/button"));
        registerButton.click();
        Thread.sleep(1000);

        Assert.assertEquals("http://localhost:4200/register", driver.getCurrentUrl());

    }


    @Test
    public void redirectToRegister() {

        WebElement registerRedirect = driver.findElement(By.xpath("/html/body/app-root/app-login/div[3]/div[2]/button/span"));
        registerRedirect.click();
        Assert.assertEquals("http://localhost:4200/register", driver.getCurrentUrl());

    }

    public static void login(String userName, String pass) throws InterruptedException {

        WebElement username = driver.findElement(By.xpath("//*[@id=\"username\"]"));
        username.clear();
        username.sendKeys(userName);
        Thread.sleep(500);

        WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        password.clear();
        password.sendKeys(pass);
        Thread.sleep(500);

        WebElement element = driver.findElement(By.id("buttonLogin"));
        element.click();
        Thread.sleep(500);

        System.out.println("Login Button Pressed");

    }

    public static void fillRegistrationFields(String credentials) throws InterruptedException {
        WebElement firstName = driver.findElement(By.xpath("//*[@id=\"firstName\"]"));
        firstName.clear();
        firstName.sendKeys("Jane");
        Thread.sleep(500);

        WebElement lastName = driver.findElement(By.xpath("//*[@id=\"lastName\"]"));
        lastName.clear();
        lastName.sendKeys("Doe");
        Thread.sleep(500);

        WebElement username = driver.findElement(By.xpath("//*[@id=\"username\"]"));
        username.clear();
        username.sendKeys(credentials);
        Thread.sleep(500);

        WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        password.clear();
        password.sendKeys(credentials);
        Thread.sleep(500);
    }


    @After
    public void destroy() throws InterruptedException {
        Thread.sleep(10000);
        driver.close();
    }

}
