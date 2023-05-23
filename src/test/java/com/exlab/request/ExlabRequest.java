package com.exlab.request;

import com.exlab.common.DataForApi;
import com.exlab.utilities.ApiUtils;
import com.exlab.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class ExlabRequest {

    public static Response response;
    public static int userId;
    public static String token;
    public static String name;
    public static String email;
    public static Integer experience_ID;
    public static String experience_job;

    public static Response registerNewUser(String name,String email,String password,String about,String terms){
         response = RestAssured.given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(DataForApi.registerUserBody(name, email, password, about, terms))
                .when()
                .post(ConfigurationReader.get("postRegisterUser"));

         userId = response.path("id");
        System.out.println("userId = " + userId);
        token=response.path("token");
        System.out.println("token = " + token);
        return response;
    }

    public static Response patchSaveProfile(String name,String password,String admin,String about, String terms,
                                            String job,String company,String website,String location, String skills){
        response = RestAssured.given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .header("token",ConfigurationReader.get("newUserToken"))
                .body(DataForApi.saveProfileBody(name,password,admin,about,terms,job,company,website,location,skills))
                .when()
                .patch(ConfigurationReader.get("patchSaveProfile"));
        response.prettyPrint();
        return response;
    }
    public static Response getOwnProfile(){
        response = RestAssured.given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .header("token",ConfigurationReader.get("newUserToken"))
                .when()
                .get(ConfigurationReader.get("getOwnProfile"));
        name=response.path("name[0]");
        email=response.path("email[0]");
        System.out.println("name = " + name);
        System.out.println("email = " + email);
        return response;
    }
  public static Response postExperience(String job,String company,String location,String fromdate, String todate,
                                        String current,String description){

      response = given().accept(ContentType.JSON)
              .and()
              //.headers( ApiUtils.getToken(email,ConfigurationReader.get("password")))
              .headers( ApiUtils.getToken(ConfigurationReader.get("email"),ConfigurationReader.get("password")))
              .and()
              .body(DataForApi.experienceBody( job, company, location, fromdate,  todate, current, description))
              .when()
              .post(ConfigurationReader.get("postExperience"));

      experience_ID=response.path("id");
      experience_job=response.path("job");
      return response;

  }

}
