package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.group32.utils.ConfigLoader;

import static org.junit.Assert.*;

public class UpdateBookSteps {

    private static final String BASE_URL = ConfigLoader.getProperty("backend.url");
    private RequestSpecification httpRequest;
    private static Response response;
    private int responseStatusCode;

    @Given("The {string} user is authenticated for update")
    public void theUserIsAuthenticatedForUpdate(String role) {
        String username = role.equals("admin") ? "admin" : "user";
        String password = ConfigLoader.getProperty("password");
        RestAssured.authentication = RestAssured.basic(username, password);
    }

    @When("The {string} user updates a book with id {int}, title {string}, and author {string}")
    public void theUserUpdatesABookWithIdTitleAndAuthor(String role, int bookId, String title, String author) {
        httpRequest = RestAssured.given()
                .header("Content-Type", "application/json")
                .body("{ \"id\": " + bookId + ", \"title\": \"" + title + "\", \"author\": \"" + author + "\" }");

        response = httpRequest.put(BASE_URL + "books/" + bookId);
    }

    @When("The {string} user updates a book with mismatched ids {int} and {int}")
    public void theUserUpdatesABookWithMismatchedIds(String role, int pathId, int bodyId) {
        httpRequest = RestAssured.given()
                .header("Content-Type", "application/json")
                .body("{ \"id\": " + bodyId + ", \"title\": \"Valid Title\", \"author\": \"Valid Author\" }");

        response = httpRequest.put(BASE_URL + "books/" + pathId);
    }

    @Then("The book should be updated successfully with status code {int}")
    public void theBookShouldBeUpdatedSuccessfully(int expectedStatusCode) {
        responseStatusCode = response.getStatusCode();
        assertEquals("Expected status code does not match the response status code.", expectedStatusCode, responseStatusCode);
    }

    @Then("An error response with status code {int} should be returned")
    public void anErrorResponseWithStatusCodeShouldBeReturned(int expectedStatusCode) {
        responseStatusCode = response.getStatusCode();
        assertEquals("Expected error status code does not match the response status code.", expectedStatusCode, responseStatusCode);
    }

    @Then("The response message should contain {string}")
    public void theResponseMessageShouldContain(String expectedMessage) {
        String responseBody = response.getBody().asString();
        assertTrue("Response message does not contain the expected text.", responseBody.contains(expectedMessage));
    }
}
