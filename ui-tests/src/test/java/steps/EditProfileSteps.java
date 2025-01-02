package steps;

import io.cucumber.java.en.*;
import net.serenitybdd.annotations.Steps;
import org.assertj.core.api.Assertions;
import pages.EditProfilePage;

public class EditProfileSteps {

    @Steps
    LoginSteps loginSteps;

    @Steps
    EditProfilePage editProfilePage;

    @Given("the user is logged in")
    public void the_user_is_logged_in() {
        loginSteps.the_user_is_on_the_login_page();
        loginSteps.the_user_enters_valid_email_and_password();
        loginSteps.the_user_clicks_the_login_button();
    }

    @And("the user navigates to the edit profile page")
    public void the_user_navigates_to_edit_profile_page() {
        editProfilePage.navigateToEditProfilePage();
    }

    @When("the user updates their profile with first name {string}, last name {string}, email {string}, and telephone {string}")
    public void the_user_updates_profile(String firstName, String lastName, String email, String telephone) {
        editProfilePage.updateFirstName(firstName);
        editProfilePage.updateLastName(lastName);
        editProfilePage.updateEmail(email);
        editProfilePage.updateTelephone(telephone);
        editProfilePage.clickContinueButton();
    }

    @Then("a success message should be displayed")
    public void a_success_message_should_be_displayed() {
        Assertions.assertThat(editProfilePage.isUpdateSuccessful())
                .isTrue();
    }
}
