package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class DashBoardPage_HRMS extends CommonMethods {

    @FindBy (xpath = "//h1[text()='Dashboard']")
    public WebElement dashboardMessage;

    public DashBoardPage_HRMS(){
        PageFactory.initElements(driver,this);
    }
}
