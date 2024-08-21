package step_definitions;

import com.github.javafaker.Faker;
import entities.RequestBody;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.Assert;
import utilities.CashWiseToken;
import utilities.Config;

public class CreateNewExpenseProductSteps {

    private String productTitle;
    private Integer productPrice;
    private String token;
    private String baseUrl;
    private Response response;
    private static final Logger logger = (Logger) LogManager.getLogger(CreateNewExpenseProductSteps.class);



    @Given("I have a valid token")
    public void iHaveAValidToken() {
        token = CashWiseToken.getToken();
        baseUrl = Config.getProperty("baseCashwiseUrl");
        logger.info("Token and base URL have been initialized");
    }

    @And("I generate random product details")
    public void iGenerateRandomProductDetails() {
        Faker faker = new Faker();
        productTitle = faker.commerce().productName();
        productPrice = faker.number().randomDigit();
        logger.info("Generated random product details: Title - " + productTitle + ", Price - " + productPrice);

    }

    @When("I send a POST request to create the product")
    public void iSendAPostRequestToCreateTheProduct() {
        RequestBody body = new RequestBody();
        body.setProduct_title(productTitle);
        body.setProduct_price(productPrice);
        body.setService_type_id(1);
        body.setCategory_id(1536);

        response = RestAssured.given()
                .auth().oauth2(token)
                .contentType("application/json")
                .body(body)
                .post(baseUrl + "/api/myaccount/expenses");

        logger.info("Sent POST request to create the product. Response status code: " + response.getStatusCode());

    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int statusCode) {
        logger.info("Validating the response status code: Expected - " + statusCode + ", Actual - " + response.getStatusCode());
        Assert.assertEquals(statusCode, response.statusCode());

    }

    @And("the response body should contain the same product title")
    public void theResponseBodyShouldContainTheSameProductTitle() {
        String responseBody = response.getBody().asString();
        logger.info("Validating that the response body contains the product title: " + productTitle);
        Assert.assertTrue(responseBody.contains(productTitle));

    }

    @And("the product price should be greater than {int}")
    public void theProductPriceShouldBeGreaterThan(int price) {
        logger.info("Validating that the product price is greater than " + price + ": Actual price - " + productPrice);

        Assert.assertTrue(productPrice > price);
        response.prettyPrint();
    }
}
