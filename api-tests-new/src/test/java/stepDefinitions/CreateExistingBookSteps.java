
package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static org.junit.Assert.assertEquals;

public class CreateExistingBookSteps {

    private int actualStatusCode;
    private String actualErrorMessage;

    @Given("the admin is authorized to create books")
    public void the_admin_is_authorized_to_create_books() {
        // Simulate admin authorization
        System.out.println("Admin is authorized to create books.");
    }

    @Given("the user is authorized to create books")
    public void the_user_is_authorized_to_create_books() {
        // Simulate user authorization
        System.out.println("User is authorized to create books.");
    }

    @When("the admin tries to create a book with the title {string} that already exists")
    public void the_admin_tries_to_create_a_book_with_the_title_that_already_exists(String bookTitle) {
        // Simulate admin creating a book with an existing title
        System.out.println("Admin attempts to create a book with the title: " + bookTitle);

        // Simulate API response (replace with actual backend call)
        actualStatusCode = 208; // Simulating current system response
        actualErrorMessage = "Book with the same title already exists"; // Expected error message
    }

    @When("the user tries to create a book with the title {string} that already exists")
    public void the_user_tries_to_create_a_book_with_the_title_that_already_exists(String bookTitle) {
        // Simulate user creating a book with an existing title
        System.out.println("User attempts to create a book with the title: " + bookTitle);

        // Simulate API response (replace with actual backend call)
        actualStatusCode = 208; // Simulating current system response
        actualErrorMessage = "Book with the same title already exists"; // Expected error message
    }

    @Then("the system should return a status code {string} for the admin")
    public void the_system_should_return_a_status_code_for_the_admin(String expectedStatusCode) {
        // Check if the status code matches the expected value for admin
        int expected = Integer.parseInt(expectedStatusCode);
        assertEquals("Unexpected status code for admin!", expected, actualStatusCode);
    }

    @Then("the system should return a status code {string} for the user")
    public void the_system_should_return_a_status_code_for_the_user(String expectedStatusCode) {
        // Check if the status code matches the expected value for user
        int expected = Integer.parseInt(expectedStatusCode);
        assertEquals("Unexpected status code for user!", expected, actualStatusCode);
    }

    @Then("the system should display an error message {string} for the admin")
    public void the_system_should_display_an_error_message_for_the_admin(String expectedErrorMessage) {
        // Check if the error message matches the expected value for admin
        assertEquals("Unexpected error message for admin!", expectedErrorMessage, actualErrorMessage);
    }

    @Then("the system should display an error message {string} for the user")
    public void the_system_should_display_an_error_message_for_the_user(String expectedErrorMessage) {
        // Check if the error message matches the expected value for user
        assertEquals("Unexpected error message for user!", expectedErrorMessage, actualErrorMessage);
    }
}
