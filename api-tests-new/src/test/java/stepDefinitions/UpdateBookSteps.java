import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UpdateBookSteps {

    private static Response response;

    @When("the {string} user updates a book with id {int}, title {string}, and author {string}")
    public void the_user_updates_a_book_with_id_title_and_author(String role, int id, String title, String author) {
        String requestBody = String.format("{\"id\": %d, \"title\": \"%s\", \"author\": \"%s\"}", id, title, author);

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put(BASE_URL + "/books/" + id);
    }
}
