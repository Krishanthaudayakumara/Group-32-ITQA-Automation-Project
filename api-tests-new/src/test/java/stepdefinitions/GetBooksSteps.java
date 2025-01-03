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
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "password";

    @Given("The admin is authenticated")
    public void theAdminIsAuthenticated() {
        RestAssured.authentication = RestAssured.basic(USERNAME, PASSWORD);
    }

    @When("The admin sends the get API request")
    public void theAdminSendsTheGetAPIRequest() {
        try {
            // Adding a wait time of 10 seconds to ensure the database is started
            Thread.sleep(10000); // Wait for 10 seconds
            httpRequest = RestAssured.given();
            response = httpRequest.get(BASE_URL + "books");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Then("The available books should be retrieved successfully with status code as {int}")
    public void theAvailableBooksShouldBeRetrievedSuccessfullyWithStatusCodeAs(int expectedStatusCode) {
        // Retrieve the actual status code from the API response
        responseStatusCode = response.getStatusCode();

        // Assert that the actual status code matches the expected status code
        assertEquals("Expected status code does not match with the response status code.", expectedStatusCode, responseStatusCode);

        // Check if the response body is not null or empty
        String responseBody = response.getBody().asString();
        List<Map<String, Object>> books = response.jsonPath().getList("$");
        if (responseBody != null && !responseBody.isEmpty()) {

            // Loop through each book in the list and check if it has the required attributes
            for (int i = 0; i < books.size(); i++) {
                Map<String, Object> book = books.get(i);

                // Assert that the book has an id
                Integer bookId = (Integer) book.get("id");
                assertNotNull("Book " + (i + 1) + " should have an id", bookId);
                assertTrue("Book " + (i + 1) + " id should be a valid number", bookId > 0);

                // Assert that the book has a title
                String bookTitle = (String) book.get("title");
                assertNotNull("Book " + (i + 1) + " should have a title", bookTitle);
                assertFalse("Book " + (i + 1) + " title should not be empty", bookTitle.isEmpty());

                // Assert that the book has an author
                String bookAuthor = (String) book.get("author");
                assertNotNull("Book " + (i + 1) + " should have an author", bookAuthor);
                assertFalse("Book " + (i + 1) + " author should not be empty", bookAuthor.isEmpty());
            }
        } else {
            // If the response body is null or empty, assert that no books are available
            assertEquals("No books available in the response.", books.size(), 0);
        }
    }
}
