package steps.UI;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utils.CommonMethods;
import utils.ConfigReader;

public class LoginSteps extends CommonMethods {

    @When("user enters valid username {string} and password {string}")
    public void user_enters_valid_username_and_password(String username, String password) {
        String usernameKey=ConfigReader.read(username);
        String passwordKey=ConfigReader.read(password);
        sendText(usernameKey, loginPageHrms.usernameField);
        sendText(passwordKey, loginPageHrms.passwordField);
    }
    @When("user clicks login button")
    public void user_clicks_login_button() {
        click(loginPageHrms.loginButton);
    }
    @Then("user is successfully logged in with dashboard showing {string}")
    public void user_is_successfully_logged_in_with_dashboard_showing(String expectedMessage) {
        String actualLoginMessage=dashBoardPageHrms.dashboardMessage.getText();
        Assert.assertEquals(expectedMessage,actualLoginMessage);
    }
    @When("user enters invalid username and password")
    public void user_enters_invalid_username_and_password() {
        sendText("wrongUserName", loginPageHrms.usernameField);
        sendText("wrongPassword", loginPageHrms.passwordField);
    }
    @Then("user sees error message {string}")
    public void user_sees_error_message(String expectedMessage) {
        String errorMessage=loginPageHrms.errorMessage.getText();
        Assert.assertEquals(expectedMessage,errorMessage);
    }

    @When("user leaves username field empty")
    public void user_leaves_username_field_empty() {
        sendText("", loginPageHrms.usernameField);
    }
    @When("user enters password")
    public void user_enters_password() {
        sendText("asdf", loginPageHrms.passwordField);
    }

    @When("user enters username")
    public void user_enters_username() {
        sendText("sad32", loginPageHrms.usernameField);
    }
    @When("user leaves password field empty")
    public void user_leaves_password_field_empty() {
        sendText("", loginPageHrms.passwordField);
    }

    @When("employee enters valid username and password")
    public void employee_enters_valid_username_and_password() {
        sendText(ConfigReader.read("essUsername"), loginPageHrms.usernameField);
        sendText(ConfigReader.read("essPassword"), loginPageHrms.passwordField);
    }

}
