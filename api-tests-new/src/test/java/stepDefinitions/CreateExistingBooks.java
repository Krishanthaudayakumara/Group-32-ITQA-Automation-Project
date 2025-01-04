package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.group32.utils.ConfigLoader;

import static org.junit.Assert.assertEquals;

public class CreateExistingBooks {

    private static final String BASE_URL = ConfigLoader.getProperty("backend.url"); // API base URL
    private static final String ADMIN_PASSWORD = "password";
    private static final String USER_PASSWORD = "password";
    private static Response response;  // Holds the response from the API

    @Given("the {string} can not able to create existing book")
    public void the_user_is_not_authorized_recreate_existing_book(String role) { //user refers to any user
        String username = role.equals("admin") ? "admin" : "user";
        String password = role.equals("admin") ? ADMIN_PASSWORD : USER_PASSWORD;
        RestAssured.authentication = RestAssured.basic(username, password);
    }

    @When("the {string} tries to recreate a book with the title {string} and author {string}")
    public void the_user_creates_a_book_with_existing_title(String role, String title, String author) {
        String requestBody = String.format("{\"title\": \"%s\", \"author\": \"%s\"}", title, author);

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(BASE_URL + "books");
    }

    @Then("system should return a status code {string}")
    public void the_book_should_not_be_created_successfully(String expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
        assertEquals("Unexpected status code!", Integer.parseInt(expectedStatusCode), actualStatusCode);
    }
}
