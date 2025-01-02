package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static org.junit.Assert.*;

public class GetABookSteps {

    private static final String BASE_URL = "http://localhost:7081/api/";
    public RequestSpecification httpRequest;
    private static Response response;
    public int responseStatusCode;
    public int expectedStatusCode;

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

        String responseBody = response.getBody().asString();
        assertNotNull("Response body should not be null", responseBody);

        // Verify that the response body contains the correct book id
        Integer bookIdInResponse = response.jsonPath().getInt("id");
        assertEquals("The returned book ID does not match the requested ID.", 1, bookIdInResponse.intValue()); // Check if the returned ID is 1
    }

    @Then("The error message as {string} should be appeared with the status code as {int}")
    public void theErrorMessageAsShouldBeAppearedWithTheStatusCodeAs(String errorMessage, int expectedStatusCode) {
        responseStatusCode = response.getStatusCode();

        // Assert that the actual status code matches the expected status code
        assertEquals("Expected status code does not match with the response status code.", expectedStatusCode, responseStatusCode);

        String responseBody = response.getBody().asString();
        assertNotNull("Response body should not be null", responseBody);
        assertEquals("The book is available.", errorMessage, responseBody);
    }

    @When("The {string} sends the get API request with {string}")
    public void theSendsTheGetAPIRequestWith(String role, String string) {
        httpRequest = RestAssured.given();
        response = httpRequest.get(BASE_URL + "books/" + string);
    }

    @Then("The response body should be empty string with status code as {int}")
    public void theResponseBodyShouldBeEmptyStringWithStatusCodeAs(int expectedStatusCode) {
        responseStatusCode = response.getStatusCode();

        // Assert that the actual status code matches the expected status code
        assertEquals("Expected status code does not match with the response status code.", expectedStatusCode, responseStatusCode);

        String responseBody = response.getBody().asString();
        assertTrue("Response body should be empty or null", responseBody == null || responseBody.isEmpty());
    }
}
