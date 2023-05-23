package com.exlab.stepDefs;

import com.exlab.page.LoginPage;
import com.exlab.request.ExlabRequest;
import com.exlab.utilities.ConfigurationReader;
import com.exlab.utilities.Driver;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class SignUp_StepDefs {

    @Given("The user creates a POST request with {string} and {string} and {string} and {string} and {string}")
    public void the_user_creates_a_POST_request_with_and_and_and_and(String name, String email, String password, String about, String terms) {
        ExlabRequest.registerNewUser(name, email, password, about, terms);
        ExlabRequest.response.prettyPrint();
    }

    @Then("The user verifies that the status code is {int}")
    public void the_user_verifies_that_the_status_code_is(int expectedStatusCode) {
        Assert.assertEquals(expectedStatusCode,ExlabRequest.response.statusCode());
    }
    @Then("The user verifies that body contains {string}")
    public void the_user_verifies_that_body_contains(String token) {
        Assert.assertTrue(ExlabRequest.response.body().asString().contains(token));
    }
    @Then("The compiler gets the token")
    public void the_compiler_gets_the_token() {
        ConfigurationReader.set("newUserToken",ExlabRequest.token);
    }

    @When("The user creates a PATCH request and send the token {string} and {string} and {string} and {string} and {string} and {string} and {string} and {string} and {string} and {string}")
    public void the_user_creates_a_PATCH_request_and_send_the_token_and_and_and_and_and_and_and_and_and(String name,String password,String admin,String about, String terms,
                                                                                                        String job,String company,String website,String location, String skills) {
        ExlabRequest.patchSaveProfile(name,password,admin,about,terms,job,company,website,location,skills);
    }

    @Then("The user creates a GET request to get user own profile with token")
    public void the_user_creates_a_GET_request_to_get_user_own_profile_with_token() {
        ExlabRequest.getOwnProfile();
    }
    @Then("The user verifies that name as {string} and email as {string}")
    public void the_user_verifies_that_name_as_and_email_as(String expectedName, String expectedEmail) {
        Assert.assertEquals(expectedName,ExlabRequest.name);
        //Assert.assertEquals(expectedEmail,ExlabRequest.email);
        Assert.assertEquals(expectedEmail,ExlabRequest.response.path("email[0]"));
    }

    @When("The user creates a POST request for add a new experience with {string} and {string} and {string} and {string} and  {string} and {string} and {string}")
    public void the_user_creates_a_POST_request_for_add_a_new_experience_with_and_and_and_and_and_and(String job,String company,String location,String fromDate,String toDate,String current,String description) {

        ExlabRequest.postExperience(job, company, location, fromDate, toDate, current, description);
    }

    @Then("The user is on the Dashboard page")
    public void the_user_is_on_the_Dashboard_page() throws InterruptedException {
        new LoginPage().setup();
    }
    @Then("The user verifies that UI experience and API experience must be match job is {string}")
    public void the_user_verifies_that_UI_experience_and_API_experience_must_be_match_job_is(String expectedJob) throws InterruptedException {
    String actualJobFromUI=new LoginPage().getExperienceJob(expectedJob);
    String actualJobFromAPI=ExlabRequest.experience_job;
    Assert.assertEquals(actualJobFromUI,actualJobFromAPI);
        Driver.closeDriver();
    }



}
