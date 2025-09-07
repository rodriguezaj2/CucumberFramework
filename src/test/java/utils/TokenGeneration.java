package utils;


import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class TokenGeneration {
    private static String token;
    public static RequestSpecification req;
    public static Response resp;

    public static String getToken(){
        if(token==null){
            req=given().header(APIConstants.HEADER_CONTENT_TYPE_KEY,APIConstants.HEADER_CONTENT_TYPE_VALUE).body("{\n" +
                    "  \"email\": \"KingdomHearts2002@gmail.com\",\n" +
                    "  \"password\": \"RikuAndKairi\"\n" +
                    "}");

            resp = req.when().post(APIConstants.GENERATE_TOKEN_URI);
            token="Bearer "+resp.jsonPath().getString("token");
        }
        return token;
    }
}
