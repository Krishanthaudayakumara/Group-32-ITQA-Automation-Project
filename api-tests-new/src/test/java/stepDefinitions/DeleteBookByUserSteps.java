package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.junit.Assert.assertEquals;

public class DeleteBookByUserSteps {

    private static final String BASE_URL = "http://localhost:7081/api"; // API base URL
    private Response response; // Holds the response from the API

    // Given step: "user" attempts to delete books (but is not authorized)
    @Given("the user attempts to delete a book")
    public void the_user_attempts_to_delete_books() {
        // Basic authentication for the user
        RestAssured.authentication = RestAssured.basic("user", "password");
    }

    // When step: User deletes a book with a specific ID
    @When("the user deletes a book with id {int}")
    public void the_user_deletes_a_book_with_id(Integer bookId) {
        // Send DELETE request to delete the book
        response = RestAssured.given()
                .when()
                .delete(BASE_URL + "/books/" + bookId);
    }

    // Then step: Validate the server returns status code 403 for unauthorized access
    @Then("the server should return status code 403 for unauthorized access")
    public void the_server_should_return_status_code_403_for_unauthorized_access() {
        // Validate the response status code is 403
        assertEquals(403, response.getStatusCode());
    }
}
