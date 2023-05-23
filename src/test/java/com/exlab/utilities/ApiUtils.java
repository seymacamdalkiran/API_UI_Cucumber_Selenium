package com.exlab.utilities;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class ApiUtils {

        public static Map<String ,Object> getToken(String email,String password){
            Response response = RestAssured.given()
                    .accept(ContentType.JSON)
                    .formParam("email", email)
                    .formParam("password", password)
                    .log().all()
                    .when()
                    .post("/allusers/login");
            String token = response.path("token");

            Map<String,Object> authorization=new HashMap<>();
            authorization.put("token",token);
            return authorization;
        }
}
