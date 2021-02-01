package me.lukasz.selenium.carpage;

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

public class CarPageTest
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
        webDriver.get("http://localhost:8085/html/carman.html");
        CarPage carPage = PageFactory.initElements(webDriver, CarPage.class);
        carPage.createCarFill();
        carPage.createButton.click();
        assertEquals("#4 | EX20OPL | 2020 | Black | 200 | 1.2", new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(carPage.createString)).getText());
    }

    @Test
    public void testReadAll()
    {
        webDriver.get("http://localhost:8085/html/carman.html");
        CarPage carPage = PageFactory.initElements(webDriver, CarPage.class);
        carPage.readAllButton.click();
        final String expected = "#1 | 1 | EX20POL | 2020 | White | 132 | 2.3\n" +
                "#2 | 2 | FS15SFE | 2015 | Blue | 100 | 15.6\n" +
                "#3 | 3 | EF19DSF | 2019 | Purple | 82 | 35.1";
        assertEquals(expected, new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(carPage.readAllCars)).getText());
    }

    @Test
    public void testRead()
    {
        webDriver.get("http://localhost:8085/html/carman.html");
        CarPage carPage = PageFactory.initElements(webDriver, CarPage.class);
        carPage.readCarID.sendKeys("1");
        carPage.readCarButton.click();
        assertEquals("#1 | EX20POL | 2020 | White | 132 | 2.3", new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(carPage.readCar)).getText());
    }

    @Test
    public void testUpdate()
    {
        webDriver.get("http://localhost:8085/html/carman.html");
        CarPage carPage = PageFactory.initElements(webDriver, CarPage.class);
        carPage.updateCarFill();
        carPage.updateButton.click();
        assertEquals("#1 | HZ10WPO | 2020 | Green | 120 | 8.2", new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(carPage.updateString)).getText());
    }

    @Test
    public void deleteTest()
    {
        webDriver.get("http://localhost:8085/html/carman.html");
        CarPage carPage = PageFactory.initElements(webDriver, CarPage.class);
        carPage.deleteID.sendKeys("2");
        carPage.deleteButton.click();
        assertEquals("Car has been Removed", new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(carPage.deleteString)).getText());
    }


    @AfterAll
    public static void down()
    {
        webDriver.close();
    }


}
