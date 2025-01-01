package runners;

import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features", // Adjust if your feature files are stored elsewhere
        glue = "stepdefinitions", // Correct path for step definitions
        plugin = {"pretty", "html:target/cucumber-reports.html"} // Generate reports
)
public class ApiTestRunner {
}

