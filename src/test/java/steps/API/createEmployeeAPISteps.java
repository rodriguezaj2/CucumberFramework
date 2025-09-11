package steps.API;

import APISteps.APIPayload;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.APIConstants;
import utils.DBUtils;
import utils.TokenGeneration;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class createEmployeeAPISteps {

    RequestSpecification req;
    Response resp;
    static String empID;
    static String firstName;


    @Given("Request  is prepared to make a POST call to create an employee")
    public void request_is_prepared_to_make_a_post_call_to_create_an_employee() {
      req=given().header(APIConstants.HEADER_CONTENT_TYPE_KEY,APIConstants.HEADER_CONTENT_TYPE_VALUE).header(APIConstants.HEADER_AUTHORIZATION_KEY,TokenGeneration.getToken()).body(APIPayload.createEmployeePayload());
    }
    @When("POST call is made to create an employee")
    public void post_call_is_made_to_create_an_employee() {
        resp=req.when().post(APIConstants.CREATE_EMPLOYEE_URI);
        empID=resp.jsonPath().getString("Employee.employee_id");
        firstName=resp.jsonPath().getString("Employee.emp_firstname");
      /*  System.out.println(empID);
        System.out.println(firstName);
        System.out.println("*******************");*/

    }
    @Then("The response code should be {int}")
    public void the_response_code_should_be(Integer expectedStatusCode) {
        resp.then().statusCode(expectedStatusCode);
    }
    @Then("the {string} and {string} is same as the created employee")
    public void the_and_is_same_as_the_created_employee(String employeeIDColumn, String employeeFirstNameColumn) {
        List<Map<String,String>> employeeData= DBUtils.fetch("select * from hs_hr_employees where employee_id  = '"+empID+"';");
        String dbFirstName=employeeData.get(0).get(employeeFirstNameColumn);
        String dbEmpID=employeeData.get(0).get(employeeIDColumn);
      /*  System.out.println(dbFirstName);
        System.out.println(dbEmpID);*/
        assertThat(dbFirstName,equalTo(firstName));
        assertThat(dbEmpID,equalTo(empID));

    }

}
