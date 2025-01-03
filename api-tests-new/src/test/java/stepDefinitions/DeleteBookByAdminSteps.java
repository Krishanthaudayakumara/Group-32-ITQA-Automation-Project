package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.utils.ConfigLoader;

import static org.junit.Assert.*;

public class DeleteBookByAdminSteps {

    private static final String BASE_URL = ConfigLoader.getProperty("backend.url"); // API base URL
    private static Response response;

    @Given("the admin user is authorized to delete books")
    public void the_admin_user_is_authorized_to_delete_books() {
        // Set admin basic authentication
        RestAssured.authentication = RestAssured.basic("admin", "password");
    }

    @When("the admin deletes a book with id {int}")
    public void the_admin_deletes_a_book_with_id(int bookId) {
        // Send DELETE request to delete the book by ID
        response = RestAssured.given()
                .when()
                .delete(BASE_URL + "/books/" + bookId);
    }

    @Then("the book should be deleted successfully with status code 200")
    public void the_book_should_be_deleted_successfully() {
        // Assert that the response status code is 200 (OK)
        assertEquals(200, response.getStatusCode());
    }

    @Then("the server should return status code 404 if the book does not exist")
    public void the_server_should_return_status_code_404_if_the_book_does_not_exist() {
        // Assert that the response status code is 404 (Not Found)
        assertEquals(404, response.getStatusCode());
    }
}
