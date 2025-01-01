package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "steps",      // <--- Make sure this matches the package of your steps
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html",
                "json:target/cucumber-reports/cucumber.json"
        },
        monochrome = true,
        tags = "@smoke"
)
public class TestRunner {
}