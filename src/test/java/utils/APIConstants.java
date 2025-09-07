package utils;

import io.restassured.RestAssured;

public class APIConstants {

    //endpoints
    public static final String BaseURI= RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    public static final String GENERATE_TOKEN = BaseURI+"/generateToken.php";
    public static final String CREATE_USER_URI= BaseURI+"/createUser.php";
    public static final String CREATE_EMPLOYEE_URI= BaseURI+"/createEmployee.php";
    public static final String GET_ONE_EMPLOYEE_URI = BaseURI+"/getOneEmployee.php";
    public static final String UPDATE_EMPLOYEE = BaseURI+"/updateEmployee.php";
    public static final String DELETE_EMPLOYEE_URI= BaseURI+"/deleteEmployee.php";

    //headers
    public static final String HEADER_CONTENT_TYPE_KEY = "Content-Type";
    public static final String HEADER_CONTENT_TYPE_VALUE= "application/json";
    public static final String HEADER_AUTHORIZATION_KEY = "Authorization";
}
