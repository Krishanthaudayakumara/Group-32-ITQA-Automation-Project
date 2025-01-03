package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.utils.ConfigLoader;

import static org.junit.Assert.*;

public class GetABookSteps {

    private static final String BASE_URL = ConfigLoader.getProperty("backend.url");
    public RequestSpecification httpRequest;
    private static Response response;
    public int responseStatusCode;

    @When("The {string} sends the get API request with {int}")
    public void theSendsTheGetAPIRequestWith(String role, int bookId) {
        httpRequest = RestAssured.given();
        response = httpRequest.get(BASE_URL + "books/" + bookId);
    }

    @Then("The requested book should be retrieved successfully with status code as {int}")
    public void theRequestedBookShouldBeRetrievedSuccessfullyWithStatusCodeAs(int expectedStatusCode) {
        // Retrieve the actual status code from the API response
        responseStatusCode = response.getStatusCode();

        // Assert that the actual status code matches the expected status code
        assertEquals("Expected status code does not match with the response status code.", expectedStatusCode, responseStatusCode);

        // Verify that the response body contains the correct book id
        int bookIdInResponse = response.jsonPath().getInt("id");
        assertEquals("The returned book ID does not match the requested ID.", 1, bookIdInResponse); // Check if the returned ID is 1
    }

    @Then("The message as {string} should be appeared")
    public void theMessageAsShouldBeAppeared(String message) {
        String responseBody = response.getBody().asString();
        assertNotNull("Response body should not be null", responseBody);
    }

    @And("The status code should be retrieved as {int}")
    public void theStatusCodeShouldBeRetrievedAs(int expectedStatusCode) {
        responseStatusCode = response.getStatusCode();

        // Assert that the actual status code matches the expected status code
        assertEquals("Expected status code does not match with the response status code.", expectedStatusCode, responseStatusCode);
    }

    @When("The {string} sends the get API request with {string}")
    public void theSendsTheGetAPIRequestWith(String role, String string) {
        httpRequest = RestAssured.given();
        response = httpRequest.get(BASE_URL + "books/" + string);
    }

    @Then("The response body should be empty with status code as {int}")
    public void theResponseBodyShouldBeEmptyWithStatusCodeAs(int expectedStatusCode) {
        responseStatusCode = response.getStatusCode();

        // Assert that the actual status code matches the expected status code
        assertEquals("Expected status code does not match with the response status code.", expectedStatusCode, responseStatusCode);

        String responseBody = response.getBody().asString();
        assertTrue("Response body should be empty or null", responseBody == null || responseBody.isEmpty());
    }
}
