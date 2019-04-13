package com.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.test.stepdefs"},
        tags = {"~@ignore"},
        format = {
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty",
                "json:target/cucumber-reports/json-reports/CucumberTestReport.json",
                "rerun:target/cucumber-reports/rerun-reports/rerun.txt"
        })
@RunWith(Cucumber.class)
public class TestRunner {

}
