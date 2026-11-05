package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

@FindBy(name = "email")
private WebElement un;

@FindBy (name = "password")
private WebElement pwd;
@FindBy (xpath ="//button[@type='submit']")
private WebElement continuelogin;


public LoginPage(WebDriver driver) {
	PageFactory.initElements(driver,this);
}



public WebElement getUsername() {
	return un;
}


public WebElement getPassword() {
	return pwd;
}


public WebElement getSubmit() {
	return continuelogin;
}



}
