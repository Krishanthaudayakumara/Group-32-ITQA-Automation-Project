package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.junit.Assert.*;

public class CreateBookSteps {

    private static final String BASE_URL = "http://localhost:7081/api"; // API base URL
    private static Response response;  // Holds the response from the API

    @Given("the {string} user is authorized")
    public void the_user_is_authorized(String role) {
        String username = role.equals("admin") ? "admin" : "user";
        String password = "password";
        RestAssured.authentication = RestAssured.basic(username, password);
    }

    @When("the {string} user creates a book with title {string} and author {string}")
    public void the_user_creates_a_book_with_title_and_author(String role, String title, String author) {
        String requestBody = String.format("{\"title\": \"%s\", \"author\": \"%s\"}", title, author);

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(BASE_URL + "/books");
    }

    @Then("the book should be created successfully with status code 201")
    public void the_book_should_be_created_successfully() {
        assertEquals(201, response.getStatusCode());
    }
}

