package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.group32.utils.ConfigLoader;

import static org.junit.Assert.assertEquals;

public class DeleteBookByUserSteps {

    private static final String BASE_URL = ConfigLoader.getProperty("backend.url"); // API base URL
    private static Response response;

    @Given("user is not authorized to delete book")
    public void the_user_is_not_authorized_to_delete_books() {
        // Set user basic authentication
        RestAssured.authentication = RestAssured.basic("user", "password");
    }

    @When("the user deletes a book with id {int}")
    public void the_user_deletes_a_book_with_id(int bookId) {
        // Send DELETE request to delete the book by ID
        response = RestAssured.given()
                .when()
                .delete(BASE_URL + "books/" + bookId);
    }

    @Then("the server should return status code 403 for unauthorized access for user")
    public void the_server_should_return_status_code_403_for_unauthorized_access() {
        assertEquals(403, response.getStatusCode());
    }
}
