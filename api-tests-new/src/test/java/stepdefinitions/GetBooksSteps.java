package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.utils.ConfigLoader;

import static org.junit.Assert.*;

public class GetBooksSteps {

    private static final String BASE_URL = ConfigLoader.getProperty("backend.url");;
    public RequestSpecification httpRequest;
    private static Response response;
    public int responseStatusCode;

    @Given("The {string} is authenticated")
    public void theIsAuthenticated(String role) {
        String username = role.equals("admin") ? "admin" : "user";
        String password = ConfigLoader.getProperty("password");
        RestAssured.authentication = RestAssured.basic(username, password);
    }

    @When("The user sends the get API request")
    public void theUserSendsTheGetAPIRequest() {
            httpRequest = RestAssured.given();
            response = httpRequest.get(BASE_URL + "books");
    }

    @Then("The all available books should be retrieved successfully with status code as {int}")
    public void theAllAvailableBooksShouldBeRetrievedSuccessfullyWithStatusCodeAs(int expectedStatusCode) {
        // Retrieve the actual status code from the API response
        responseStatusCode = response.getStatusCode();

        // Assert that the actual status code matches the expected status code
        assertEquals("Expected status code does not match with the response status code.", expectedStatusCode, responseStatusCode);
    }
}
