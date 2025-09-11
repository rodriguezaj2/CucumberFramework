package steps.API;

import io.cucumber.java.en.Given;
import utils.TokenGeneration;

public class createTokenSteps {

    @Given("JWT Token is generated")
    public void jwt_token_is_generated() {
        String token= TokenGeneration.getToken();
    }
}
