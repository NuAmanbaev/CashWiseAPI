package step_definitions;

import api.CreateNewExpenseProductSteps;
import com.github.javafaker.Faker;
import entities.CustomResponse;
import entities.RequestBody;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.Assert;
import utilities.CashWiseToken;
import utilities.Config;

import java.util.Optional;

public class CreateCategorySteps {

    RequestBody body = new RequestBody();
    String token = CashWiseToken.getToken();
    String baseUrl = Config.getProperty("baseCashwiseUrl") + "/api/myaccount/categories";
    Faker faker = new Faker();
    private static final Logger logger = (Logger) LogManager.getLogger(CreateNewExpenseProductSteps.class);
    private Response response;


    @Given("user base url {string}")
    public void user_base_url(String url) {
        Config.getProperty("baseCashwiseUrl");
        logger.info("Token and base URL have been initialized");



    }

    @Then("the user will provide a valid token")
    public void the_user_will_provide_a_valid_token() {
        CashWiseToken.getToken();
        logger.info("The valid token has been provided");
    }


    @Then("the user will provide name of the category with numbers")
    public void the_user_will_provide_name_of_the_category_with_numbers() {
        int companyName = (int) faker.number().randomNumber();
        body.setCompany_name(String.valueOf(companyName));
        logger.info("User name provided with numbers ");

    }


    @Then("the user provides the description of the category")
    public void the_user_provides_the_description_of_the_category() {

        body.setDescription(faker.aviation().airport());
        logger.info("Description was filled out");

    }

    @Then("the user hits the api endpoint {string}")
    public void the_user_hits_the_api_endpoint(String endPoint) {
        RequestBody body = new RequestBody();
        body.setCompany_name(faker.company().name());
        body.setCategory_id(1536);


        response = RestAssured
                .given()
                .auth()
                .oauth2(token)
                .contentType("application/json")
                .body(body).post(baseUrl + "/api/myaccount/categories");

        logger.info("Sent POST request to create the category. Response status code: " + response.getStatusCode());


    }
    @Then("verify the status code {int}")
    public void verify_the_status_code(Integer expectedCode) {

        Assert.assertEquals(404, response.statusCode());

        response.prettyPrint();
        logger.info("I am expecting the code 404");
    }

    ////Positive scenario

    @Given("user will provide base url {string}")
    public void user_will_provide_base_url(String url) {
        Config.getProperty("baseCashwiseUrl");
        logger.info("The token and base URL have been initialized");

    }


    @Then("the user  provide a valid token")
    public void the_user_provide_a_valid_token() {
        CashWiseToken.getToken();
        logger.info("The valid token provided");


    }


    @Then("the user will provide name of the category")
    public void the_user_will_provide_name_of_the_category() {
        String companyName = faker.company().name();
        body.setCompany_name(companyName);
        logger.info("The user provided the company name ");

    }


    @Then("the user provides the description of  category")
    public void the_user_provides_the_description_of_category() {
        body.setDescription(faker.company().profession());
        logger.info("The description of the company has been provided");
    }



    @Then("the user hits  api endPoint {string}")
    public void the_user_hits_api_end_point(String endPoint) {
        RequestBody body = new RequestBody();
        body.setCompany_name(faker.company().name());
        body.setCategory_id(1536);
        body.setFlag(true);


        response = RestAssured
                .given()
                .auth()
                .oauth2(token)
                .contentType("application/json")
                .body(body).post(baseUrl + "/api/myaccount/categories");

        logger.info("Sent POST request to create the category. Response status code: " + response.getStatusCode());



    }


    @Then("verify the status code is  {int}")
    public void verify_the_status_code_is(Integer int1) {
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