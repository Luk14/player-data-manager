package me.lukasz.selenium.carpage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CarPage
{

    //Create Car
    @FindBy(id = "reg")
    WebElement createReg;

    @FindBy(id = "pdate")
    WebElement createPDate;

    @FindBy(id = "color")
    WebElement createColor;

    @FindBy(id = "topspeed")
    WebElement createTopSpeed;

    @FindBy(id = "zts")
    WebElement createZTS;

    @FindBy(id = "userid")
    WebElement createUserID;

    @FindBy(id = "create")
    WebElement createButton;

    @FindBy(id = "newcar")
    WebElement createString;

    public void createCarFill()
    {
        createReg.sendKeys("EX20OPL");
        createPDate.sendKeys("2020");
        createColor.sendKeys("Black");
        createTopSpeed.sendKeys("200");
        createZTS.sendKeys("1.2");
        createUserID.sendKeys("1");
    }

    //ReadAll Cars
    @FindBy(id = "readall")
    WebElement readAllButton;

    @FindBy(id = "allcars")
    WebElement readAllCars;

    //Read Car
    @FindBy(id = "carid")
    WebElement readCarID;

    @FindBy(id = "read")
    WebElement readCarButton;

    @FindBy(id = "car")
    WebElement readCar;

    //Update Car
    @FindBy(id = "carid-update")
    WebElement updateCarID;

    @FindBy(id = "reg-update")
    WebElement updateReg;

    @FindBy(id = "pdate-update")
    WebElement updatePDate;

    @FindBy(id = "color-update")
    WebElement updateColor;

    @FindBy(id = "topspeed-update")
    WebElement updateTopSpeed;

    @FindBy(id = "zts-update")
    WebElement updateZTS;

    @FindBy(id = "userid-update")
    WebElement updateUserID;

    @FindBy(id = "update")
    WebElement updateButton;

    @FindBy(id = "updatedcar")
    WebElement updateString;

    public void updateCarFill()
    {
        updateCarID.sendKeys("1");
        updateReg.sendKeys("HZ10WPO");
        updatePDate.sendKeys("2020");
        updateColor.sendKeys("Green");
        updateTopSpeed.sendKeys("120");
        updateUserID.sendKeys("1");
        updateZTS.sendKeys("8.2");
    }

    //Delete Car
    @FindBy(id = "carid_del")
    WebElement deleteID;

    @FindBy(id = "deleteu")
    WebElement deleteButton;

    @FindBy(id = "deletedcar")
    WebElement deleteString;
    
}
