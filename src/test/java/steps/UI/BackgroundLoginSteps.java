package steps.UI;

import io.cucumber.java.en.Given;
import pages.LoginPage_HRMS;
import utils.CommonMethods;

public class BackgroundLoginSteps extends CommonMethods {

    @Given("user is logged in successfully using {string} and {string}")
    public void user_is_logged_in_successfully_using_and(String username, String password) {
        sendText(username, loginPageHrms.usernameField);
        sendText(password, loginPageHrms.passwordField);

    }


}
