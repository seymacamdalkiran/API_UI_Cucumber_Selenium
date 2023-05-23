package com.exlab.stepDefs;

import com.exlab.utilities.ConfigurationReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import static io.restassured.RestAssured.*;

public class Hooks {

    @Before
    public void setup(){
        baseURI= ConfigurationReader.get("baseURL");
    }
    @After
    public void tearDown(){
        reset();
    }
}
