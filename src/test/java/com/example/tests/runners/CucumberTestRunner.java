package com.example.tests.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/category_page.feature",
        glue = "com.example.tests.steps",
        plugin = {"pretty", "html:target/cucumber-reports", "json:target/cucumber.json"} // Report options
)
public class CucumberTestRunner {
}
