package stepDefinitions;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;

public class UpdateBookSteps {

    private static final String BASE_URL = "http://localhost:7081/api"; // API base URL

    @Given("the {string} user is authenticated for update")
    public void the_user_is_authenticated_for_update(String role) {
        String username = role.equals("admin") ? "admin" : "user";
        String password = "password";
        RestAssured.authentication = RestAssured.basic(username, password);
    }
}
