package step_definition;

import com.github.javafaker.Faker;
import entities.RequestBody;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import utilities.CashWiseToken;
import utilities.Config;

public class CreateClient {


   // String baseUrl =  Config.getProperty("baseCashwiseUrl");

    RequestBody body = new RequestBody();
    String token = CashWiseToken.getToken();
    String baseUrl = Config.getProperty("baseCashwiseUrl") + "/api/myaccount/clients";
    Faker faker = new Faker();


    @Given("user hits base url {string}")
    public void user_hits_base_url(String baseUrl) {
        Config.getProperty("baseCashwiseUrl");


    }
    @When("user provides valid authorization  {string}")
    public void user_provides_valid_authorization(String token) {
        CashWiseToken.getToken();


    }

    @When("user provides company name")
    public void user_provides_company_name() {

      String companyName = faker.name().title();
      body.setCompany_name(companyName);


    }
    @When("user provides user name")
    public void user_provides_user_name() {
        String userName = faker.name().fullName();
        body.setClient_name(userName);

    }
    @When("user provides email")
    public void user_provides_email() {
        String email = faker.internet().emailAddress();
        body.setEmail(email);

    }
    @When("user provides phone number")
    public void user_provides_phone_number() {

        String phoneNumber = "3124396556";
        body.setPhone_number(phoneNumber);

    }
    @When("user provides address")
    public void user_provides_address() {
        String  address = faker.address().fullAddress();
        body.setAddress(address);

    }
    @When("user provides id tag")
    public void user_provides_id_tag() {


        body.setTags_id(new int[] {239});

    }
    @Then("verify client was created")
    public void verify_client_was_created() {


        Response response = RestAssured
                .given()
                .auth()
                .oauth2(token)
                .contentType(ContentType.JSON)
                .body(body)
                .post(baseUrl);

        int status = response.getStatusCode();
        System.out.println(status);
        Assert.assertEquals(201, status);



    }

}
