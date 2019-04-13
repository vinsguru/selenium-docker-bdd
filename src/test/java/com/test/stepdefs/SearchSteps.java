package com.test.stepdefs;

import com.searchmodule.pages.SearchPage;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.junit.Assert;

import java.net.MalformedURLException;
import java.net.URL;

public class SearchSteps{

    private SearchPage searchPage;
    private WebDriver driver;

    @Given("^I am on the website duck-duck-go$")
    public void launchSite() {
        searchPage = new SearchPage(driver);
        searchPage.goTo();
    }

    @And("^I enter the \"([^\"]*)\" to search$")
    public void enterKeyword(String keyword) {
        searchPage.doSearch(keyword);
    }

    @And("^I navigate to videos search$")
    public void navigateTovideos()  {
        searchPage.goToVideos();
    }

    @Then("^I should get minimum (\\d+) search results$")
    public void verifySearchResults(int min) {
        int size = searchPage.getResult();
        Assert.assertTrue(size > min);
    }

    @Before
    public void setupDriver() throws MalformedURLException {
        // BROWSER => chrome / firefox
        // HUB_HOST => localhost / 10.0.1.3 / hostname
        String host = "localhost";
        DesiredCapabilities dc;

        if(System.getProperty("BROWSER") != null &&
                System.getProperty("BROWSER").equalsIgnoreCase("firefox")){
            dc = DesiredCapabilities.firefox();
        }else{
            dc = DesiredCapabilities.chrome();
        }

        if(System.getProperty("HUB_HOST") != null){
            host = System.getProperty("HUB_HOST");
        }

        String completeUrl = "http://" + host + ":4444/wd/hub";
        this.driver = new RemoteWebDriver(new URL(completeUrl), dc);
    }

    @After
    public void quitDriver(){
        this.driver.quit();
    }

}