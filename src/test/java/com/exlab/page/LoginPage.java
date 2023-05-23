package com.exlab.page;

import com.exlab.request.ExlabRequest;
import com.exlab.utilities.ConfigurationReader;
import com.exlab.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

public class LoginPage extends BasePage {


    @FindBy(css ="#email" )
    public WebElement userEmailInput_loc;

    @FindBy(css = "#yourPassword")
    public WebElement passwordInput_loc;

    @FindBy(xpath = "//*[.='Login']")
    public  WebElement loginBtn_loc;



    public void login(){
        String userEmail= ConfigurationReader.get("userEmail");
        String password= ConfigurationReader.get("password");

        userEmailInput_loc.sendKeys(userEmail);
        passwordInput_loc.sendKeys(password);
        loginBtn_loc.click();
    }

    public void login(String userEmail, String password){
        userEmailInput_loc.sendKeys(userEmail);
        passwordInput_loc.sendKeys(password);
        loginBtn_loc.click();
    }

    public void setup() throws InterruptedException {
        Driver.get().get(ConfigurationReader.get("url"));
        //login(ExlabRequest.email,ConfigurationReader.get("password"));
        login(ConfigurationReader.get("email"),ConfigurationReader.get("password"));
        Thread.sleep(2000);
    }
  /*  public String getExperienceJob(String job) throws InterruptedException {
        navigateToModule(ConfigurationReader.get("name"),"My Profile");
        return Driver.get().findElement(By.xpath("(//div[.='"+job+"'])[1]")).getText();
    }
   */
  public String getExperienceJob(String job) throws InterruptedException {
      navigateToModule(ConfigurationReader.get("name"),"My Profile");
      return Driver.get().findElement(By.xpath("(//span[.='"+job+"'])[1]")).getText();

  }


}
