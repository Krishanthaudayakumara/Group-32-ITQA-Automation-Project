package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.junit.Assert.*;

public class CreateBookSteps {

    private static final String BASE_URL = "http://localhost:7081/api"; // API base URL, need to add later to env file
    private static Response response;  // Holds the response from the API

    // Given step: User (admin or regular user) is authorized
    @Given("the {string} user is authorized")
    public void the_user_is_authorized(String role) {
        // Set username based on the role (admin or user)
        String username = role.equals("admin") ? "admin" : "user";
        String password = "password"; // Password is the same for both roles

        // Set basic authentication for the user
        RestAssured.authentication = RestAssured.basic(username, password);
    }

    // When step: User (admin or regular user) creates a book with title and author
    @When("the {string} user creates a book with title {string} and author {string}")
    public void the_user_creates_a_book_with_title_and_author(String role, String title, String author) {
        // Create the book body (JSON) with title and author
        String requestBody = String.format("{\"title\": \"%s\", \"author\": \"%s\"}", title, author);

        // Send the POST request to create the book
        response = RestAssured.given()
                .contentType(ContentType.JSON)  // Setting content to JSON
                .body(requestBody)  // Pass the request body - (book details)
                .when()
                .post(BASE_URL + "/books");  // Sending the POST
    }

    // Then step: Checking  that the book is created with status code 201
    @Then("the book should be created successfully with status code 201")
    public void the_book_should_be_created_successfully() {
        // Validating response status code is 201 (Created)
        assertEquals(201, response.getStatusCode());

        // Checking that response contains the expected details (title or author) - Optional
        String responseBody = response.getBody().asString();
        assertTrue(responseBody.contains("title"));
        assertTrue(responseBody.contains("author"));
    }
}
