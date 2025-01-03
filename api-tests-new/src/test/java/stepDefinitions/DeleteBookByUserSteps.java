package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class DeleteBookByUserSteps {

    private int actualStatusCode;

    @Given("the user attempts to delete books")
    public void the_user_attempts_to_delete_books() {
        // Simulate the user attempting to delete books
        System.out.println("User is attempting to delete books.");
    }

    @When("the user deletes a book with id {int}")
    public void the_user_deletes_a_book_with_id(Integer bookId) {
        // Simulate deleting a book with the given ID
        System.out.println("User deletes a book with ID: " + bookId);

        // Simulate API response (replace with actual backend call)
        actualStatusCode = 403; // Unauthorized access response
    }

    @Then("the server should return status code {int} for unauthorized access")
    public void the_server_should_return_status_code_403_for_unauthorized_access(Integer expectedStatusCode) {
        // Check if the status code matches the expected value
        assertEquals("Unexpected status code for unauthorized access!", Optional.ofNullable(expectedStatusCode), actualStatusCode);
    }
}
