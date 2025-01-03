import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UpdateBookSteps {

    @Then("the book should be updated successfully with status code {int}")
    public void the_book_should_be_updated_successfully(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }

    @Then("an error response with status code {int} should be returned")
    public void an_error_response_should_be_returned(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }
}
