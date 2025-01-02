package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",      // Path to your feature files
        glue = "steps",                                // Package where your step definitions are located
        plugin = {
                "pretty",                              // Pretty output in console
                "html:target/cucumber-reports.html",   // HTML report
                "json:target/cucumber-reports/cucumber.json" // JSON report for integrations (optional)
        },
        monochrome = true    ,                          // Output is easier to read
        tags = "@changePassword"

)
public class TestRunner {
}
