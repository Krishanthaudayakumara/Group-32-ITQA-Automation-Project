package runners;

import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features", // Feature File Path
        glue = "stepDefinitions", // Step definitions folder path
        plugin = {"pretty", "html:target/cucumber-reports.html"} // Generate reports
)
public class ApiTestRunner {
}

