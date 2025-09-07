package steps.API;

import APISteps.APIPayload;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.APIConstants;
import utils.TokenGeneration;

import static io.restassured.RestAssured.given;

public class createEmployeeAPISteps {

    RequestSpecification req;
    Response resp;
    static String empID;

    @Given("JWT Token is generated")
    public void jwt_token_is_generated() {
        String token= TokenGeneration.getToken();
    }
    @Given("Request  is prepared to make a POST call to create an employee")
    public void request_is_prepared_to_make_a_post_call_to_create_an_employee() {
      req=given().header(APIConstants.HEADER_CONTENT_TYPE_KEY,APIConstants.HEADER_CONTENT_TYPE_VALUE).header(APIConstants.HEADER_AUTHORIZATION_KEY,TokenGeneration.getToken()).body(APIPayload.createEmployeePayload());
    }
    @When("POST call is made to create an employee")
    public void post_call_is_made_to_create_an_employee() {
        resp=req.when().post(APIConstants.CREATE_EMPLOYEE_URI);
        empID=resp.jsonPath().getString("Employee.employee_id");

    }
    @Then("The response code should be {int}")
    public void the_response_code_should_be(Integer expectedStatusCode) {
        resp.then().statusCode(expectedStatusCode);
    }

}
