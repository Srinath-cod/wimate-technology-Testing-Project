package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Dreamboard {

    @FindBy(xpath = "//iframe[@id='dreamboardIframe']")
    private WebElement iframe;

    @FindBy(xpath = "//button[@id='addButton']")
    private WebElement addButton;

    @FindBy(xpath ="//input[contains(@id,'text')]")
    private WebElement userName;

    @FindBy(xpath = "//input[contains(@id,'number')]")
    private WebElement phoneNumber;

    @FindBy(xpath = "//input[@type='email']")
    private WebElement email;

    @FindBy(xpath = "//select[contains(@name,'single-select')]")
    private WebElement selectState;

    @FindBy(xpath = "(//textarea[contains(@id,'textarea')])[1]")
    private WebElement address;

    @FindBy(xpath = "//input[@type='date']")
    private WebElement date;

    @FindBy(xpath = "(//input[contains(@id,'radio')])[1]")
    private WebElement male;

    @FindBy(xpath = "(//input[contains(@id,'radio')])[2]")
    private WebElement female;

    @FindBy(xpath = "//button[text()='Submit']")
    private WebElement submit;
   
    @FindBy(xpath = "//div[@id='swal2-html-container']")
    private WebElement messageText;

  
    @FindBy(xpath = "//button[contains(text(),'Ok')]")
    private WebElement passButton;

    
    @FindBy(xpath = "//button[contains(text(),'OK')]")
    private WebElement warningOkButton;

  
    @FindBy(id = "kt_modal_add_customer_cancel")
    private WebElement discardButton;



    @FindBy(xpath = "//button[text()='Yes, cancel it!']")
    private WebElement confirmCancelButton;
    

    public Dreamboard(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement getIframe() {
        return iframe;
    }

    public WebElement getAddButton() {
        return addButton;
    }

    public WebElement getUserName() {
        return userName;
    }

    public WebElement getPhoneNumber() {
        return phoneNumber;
    }

    public WebElement getEmail() {
        return email;
    }


    public void selectState(String state) {
        Select sel = new Select(selectState);
        sel.selectByValue(state);
    }

    public WebElement getAddress() {
        return address;
    }

    public WebElement getDate() {
        return date;
    }

    public void selectGender(String gender) {

        if(gender.equalsIgnoreCase("Male")) {
            male.click();
        }
        else {
            female.click();
        }
    }
    public WebElement getSubmit() {
        return submit;
    }
    public String getTextData() {
        return messageText.getText();
    }

    public WebElement getPassButton() {
        return passButton;
    }

    public WebElement getWarningOkButton() {
        return warningOkButton;
    }

    public WebElement getDiscardButton() {
        return discardButton;
    }

    public WebElement getConfirmCancelButton() {
        return confirmCancelButton;
    }

	
}