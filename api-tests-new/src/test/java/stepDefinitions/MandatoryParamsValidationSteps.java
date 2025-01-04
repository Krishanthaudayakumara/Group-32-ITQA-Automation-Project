package stepDefinitions;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.group32.utils.ConfigLoader;

import static org.junit.Assert.*;

public class MandatoryParamsValidationSteps {

    private static final String BASE_URL = ConfigLoader.getProperty("backend.url"); // API base URL
    private static Response response;

    @When("the {string} attempts to create a book with title {string} and author {string}")
    public void the_attempts_to_create_a_book_with_empty_title_and_author(String role, String title, String author) {
        String requestBody = String.format("{\"title\": \"%s\", \"author\": \"%s\"}", title, author);

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(BASE_URL + "books");
    }

    @Then("the book should not be created and should return status code 400")
    public void the_book_should_not_be_created() {
        // Evaluate response status code is 400 (Bad Request)
        assertEquals(400, response.getStatusCode());
    }
}
