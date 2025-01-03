package stepdefinitions;

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

    // Admin credentials for basic authentication
    // Need to add to env file later
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "password";

    // Given step: Admin user is authorized
    @Given("the admin user is authorized")
    public void the_admin_user_is_authorized() {
        RestAssured.authentication = RestAssured.basic(USERNAME, PASSWORD);  // Set basic authentication
    }

    // When step: Admin creates a book with title and author
    @When("the admin creates a book with title {string} and author {string}")
    public void the_admin_creates_a_book_with_title_and_author(String title, String author) {
        // Create the book body (JSON) with title and author
        String requestBody = String.format("{\"title\": \"%s\", \"author\": \"%s\"}", title, author);

        // Send the POST request to create the book
        response = RestAssured.given()
                .contentType(ContentType.JSON)  // Set the content type to JSON
                .body(requestBody)  // Pass the request body (book details)
                .when()
                .post(BASE_URL + "/books");  // Send the POST request to the books endpoint
    }

    // Then step: Assert that the book is created with status code 201
    @Then("the book should be created successfully with status code 201")
    public void the_book_should_be_created_successfully() {
        // Assert that the response status code is 201 (Created)
        assertEquals(201, response.getStatusCode());

        // Optionally, check if the response contains the expected details (title or author)
        String responseBody = response.getBody().asString();
        assertTrue(responseBody.contains("Jungle Book 1st Edition"));
        assertTrue(responseBody.contains("JK Rowling"));
    }
}
