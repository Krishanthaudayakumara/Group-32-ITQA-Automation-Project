package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.junit.Assert.*;

public class UpdateBookSteps {

    private static final String BASE_URL = "http://localhost:7081/api"; // API base URL
    private Response response;

    @Given("the {string} user is authenticated for update")
    public void the_user_is_authenticated_for_update(String role) {
        String username = role.equals("admin") ? "admin" : "user";
        String password = "password";
        RestAssured.given().auth().basic(username, password);
    }

    @When("the {string} user updates a book with id {int}, title {string}, and author {string}")
    public void the_user_updates_a_book_with_id_title_and_author(String role, int id, String title, String author) {
        String requestBody = String.format("{\"id\": %d, \"title\": \"%s\", \"author\": \"%s\"}", id, title, author);

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put(BASE_URL + "/books/" + id);
    }

    @When("the request body contains id {int}")
    public void the_request_body_contains_id(int requestBodyId) {
        String requestBody = String.format("{\"id\": %d, \"title\": \"Valid Title\", \"author\": \"Valid Author\"}", requestBodyId);

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put(BASE_URL + "/books/1");
    }

    @Then("the book should be updated successfully with status code {int}")
    public void the_book_should_be_updated_successfully(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }

    @Then("an error response with status code {int} should be returned")
    public void an_error_response_should_be_returned(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }

    @Then("the response message should contain {string}")
    public void the_response_message_should_contain(String expectedMessage) {
        String responseBody = response.getBody().asString();
        assertTrue(responseBody.contains(expectedMessage));
    }
}