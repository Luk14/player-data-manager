package me.lukasz.selenium.userpage;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserPageTest
{

    public static WebDriver webDriver;

    @BeforeAll
    public static void init()
    {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void testCreate()
    {
        webDriver.get("http://localhost:8085/html/userman.html");
        UserPage userPage = PageFactory.initElements(webDriver, UserPage.class);
        userPage.createUserFill();
        userPage.createButton.click();
        assertEquals("#4 | Jeremy | Jeremy | JerSmith@gm.com", new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(userPage.createString)).getText());
    }

    @Test
    public void testReadAll()
    {
        webDriver.get("http://localhost:8085/html/userman.html");
        UserPage userPage = PageFactory.initElements(webDriver, UserPage.class);
        userPage.readAllButton.click();
        final String expected = "#1 | 1 | YeetGod3 | Smith | test@gmail.com | Car: 1,\n" +
                "#2 | 2 | SpanishGod11 | Blazer | yes@gm.co.gamerz | Car: 2,\n" +
                "#3 | 3 | JimBoii | Jimmy | jimmy@hotm.co | Car: 3,";
        assertEquals(expected, new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(userPage.readAllUsers)).getText());
    }

    @Test
    public void testRead()
    {
        webDriver.get("http://localhost:8085/html/userman.html");
        UserPage userPage = PageFactory.initElements(webDriver, UserPage.class);
        userPage.readUserID.sendKeys("1");
        userPage.readUserButton.click();
        assertEquals("#1 | YeetGod3 | Smith | test@gmail.com", new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(userPage.readUser)).getText());
    }

    @Test
    public void testUpdate()
    {
        webDriver.get("http://localhost:8085/html/userman.html");
        UserPage userPage = PageFactory.initElements(webDriver, UserPage.class);
        userPage.updateUserFill();
        userPage.updateButton.click();
        assertEquals("#1 | Jeremy | Jeremy | JerSmith@gm.com", new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(userPage.updateString)).getText());
    }

    @Test
    public void deleteTest()
    {
        webDriver.get("http://localhost:8085/html/userman.html");
        UserPage userPage = PageFactory.initElements(webDriver, UserPage.class);
        userPage.deleteID.sendKeys("2");
        userPage.deleteButton.click();
        assertEquals("User has been Removed", new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(userPage.deleteString)).getText());
    }



    @AfterAll
    public static void down()
    {
        webDriver.close();
    }


}
