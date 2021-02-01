package me.lukasz.selenium.userpage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class UserPage
{

    //Create User
    @FindBy(id = "username")
    WebElement createUsername;

    @FindBy(id = "fname")
    WebElement createFName;

    @FindBy(id = "lname")
    WebElement createLName;

    @FindBy(id = "email")
    WebElement createEmail;

    @FindBy(id = "create")
    WebElement createButton;

    @FindBy(id = "newuser")
    WebElement createString;

    public void createUserFill()
    {
        createUsername.sendKeys("Jeremy");
        createFName.sendKeys("Jeremy");
        createLName.sendKeys("Smith");
        createEmail.sendKeys("JerSmith@gm.com");
    }

    //ReadAll Users
    @FindBy(id = "readall")
    WebElement readAllButton;

    @FindBy(id = "allusers")
    WebElement readAllUsers;

    //Read User
    @FindBy(id = "userid")
    WebElement readUserID;

    @FindBy(id = "read")
    WebElement readUserButton;

    @FindBy(id = "user")
    WebElement readUser;

    //Update User
    @FindBy(id = "id-update")
    WebElement updateID;

    @FindBy(id = "username-update")
    WebElement updateUsername;

    @FindBy(id = "fname-update")
    WebElement updateFName;

    @FindBy(id = "lname-update")
    WebElement updateLName;

    @FindBy(id = "email-update")
    WebElement updateEmail;

    @FindBy(id = "update")
    WebElement updateButton;

    @FindBy(id = "updateduser")
    WebElement updateString;

    public void updateUserFill()
    {
        updateID.sendKeys("1");
        updateUsername.sendKeys("Jeremy");
        updateFName.sendKeys("Jeremy");
        updateLName.sendKeys("Smith");
        updateEmail.sendKeys("JerSmith@gm.com");
    }

    //Delete User
    @FindBy(id = "userid_del")
    WebElement deleteID;

    @FindBy(id = "deleteu")
    WebElement deleteButton;

    @FindBy(id = "deleteduser")
    WebElement deleteString;


}
