package runners;

import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features",  // Feature file path
        glue = "stepDefinitions",  // Step definitions folder path
        plugin = {
                "pretty",  // Format output
                "html:target/cucumber-reports.html",  // HTML report
                "json:target/cucumber-reports/cucumber.json"  // JSON report for Serenity
        },
        tags = " @createBook or @createExistingBook @updateBook or @deleteBookByAdmin or @deleteBookByUser or @getABook or @getBooks",  // Optional: Tag filter (can be used for specific tests)

        monochrome = true  // Make console output more readable
)
public class ApiTestRunner { }
