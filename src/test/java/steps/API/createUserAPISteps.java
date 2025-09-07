package steps.API;

import APISteps.APIPayload;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.APIConstants;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class createUserAPISteps {

    RequestSpecification req;
    Response resp;

    @When("a request is prepared to make a POST call for creation of user")
    public void a_request_is_prepared_to_make_a_post_call_for_creation_of_user() {
        req=given().header(APIConstants.HEADER_CONTENT_TYPE_KEY,APIConstants.HEADER_CONTENT_TYPE_VALUE).body(APIPayload.createUserPayload());
    }
    @When("a POST call is made to create a user")
    public void a_post_call_is_made_to_create_a_user() {
        resp = req.when().post(APIConstants.CREATE_USER_URI);
    }
    @Then("the status code is {int}")
    public void the_status_code_is(Integer expectedStatusCode) {
        resp.then().statusCode(expectedStatusCode);
    }
    @Then("the employee created contains the key {string} and value {string}")
    public void the_employee_created_contains_the_key_and_value(String key, String value) {
        resp.then().assertThat().body(key,equalTo(value));

    }
}
