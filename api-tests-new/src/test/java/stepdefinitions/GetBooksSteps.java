package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class GetBooksSteps {

    private static final String BASE_URL = "http://localhost:7081/api/";
    public RequestSpecification httpRequest;
    private static Response response;
    public int responseStatusCode;
    public int expectedStatusCode;

    @Given("The {string} is authenticated")
    public void theIsAuthenticated(String role) {
        String username = role.equals("admin") ? "admin" : "user";
        String password = "password";
        RestAssured.authentication = RestAssured.basic(username, password);
    }

    @When("The {string} sends the get API request")
    public void theSendsTheGetAPIRequest(String role) {
            httpRequest = RestAssured.given();
            response = httpRequest.get(BASE_URL + "books");
    }

    @Then("The all available {int} books should be retrieved successfully with status code as {int}")
    public void theAllAvailableBooksShouldBeRetrievedSuccessfullyWithStatusCodeAs(int numberOfBooks, int expectedStatusCode) {
        // Retrieve the actual status code from the API response
        responseStatusCode = response.getStatusCode();

        // Assert that the actual status code matches the expected status code
        assertEquals("Expected status code does not match with the response status code.", expectedStatusCode, responseStatusCode);

        List<Map<String, Object>> books = response.jsonPath().getList("$");
        assertEquals("The number of books retrieved does not match the expected number.", numberOfBooks, books.size());
    }
}
