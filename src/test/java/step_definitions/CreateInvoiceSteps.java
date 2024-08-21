package step_definitions;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.JUnit4TestCaseFacade;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import utilities.CashWiseToken;
import utilities.Config;

import java.util.logging.LogManager;
import java.util.logging.Logger;

public class CreateInvoiceSteps {
    private String token;
    RequestSpecification request;
    Response response;
    private String baseUrl;
    String endPoint;
    JSONObject invoiceDetails;
    JSONArray productsArray;

    //  private static final Logger logger = (Logger) LogManager.getLogger(CreateInvoiceSteps.class);


    @Given ("base url {string}")
    public void base_url(String baseUrl) {
            request = RestAssured.given().baseUri(baseUrl).contentType( ContentType.JSON);

    }
    @Given("I have valid token")
    public void i_have_valid_token() {
        token = CashWiseToken.getToken();
        baseUrl = Config.getProperty("baseCashwiseUrl");
     //   logger.info("Token and base URL have been initialized");
    }
    @Given("I have the endpoint {string}")
        public void i_have_the_endpoint(String endPoint) {
            request = request.basePath(endPoint);

    }
    @Given("I have {string} with {string} in request body")
        public void i_have_with_in_request_body(String key, String value) {
            i_have_with_in_request_body(key, value);
    }
    @Given("I have {string} with product")
    public void i_have_with_product() {
        Response response2 = RestAssured.given().baseUri("https://backend.cashwise.us/api/myaccount/products")
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(token)
                .get("/products/1933");
        System.out.println(response2.prettyPrint());

        JSONObject requestBody = new JSONObject();
        JSONObject product = new JSONObject();
        product.put("product_title", response2.jsonPath().getString("product_title"));
        product.put("product_id", response2.jsonPath().getString("product_id"));
        product.put("count_of_product", 0);
        product.put("product_price", response2.jsonPath().getString("product_price"));
        product.put("service_type_id", response2.jsonPath().getString("service_type.service)type_id"));
        product.put("product_price", response2.jsonPath().getString("product_price"));
        product.put("product_description", response2.jsonPath().getString("product_description"));


    }
    @When ("I send POST request")
    public void i_send_post_request( JUnit4TestCaseFacade requestBody) {
        response = request.body(requestBody.toString()).post();

    }
    @Then ("verify status code is {int}")
    public void verify_status_code_is(Integer statusCode) {
        System.out.println(response.prettyPrint());
        //logger.info("Validating the response status code: Expected - " + statusCode + ", Actual - " + response.getStatusCode());
//        Assert.assertEquals(statusCode, response.statusCode());
        Assert.assertEquals(response.statusCode(), 201);
    }
    @Then("verify I have \"invoice_title\" with \"Invoices\" in response body")
    public void verify_i_have_with_in_response_body(String key, String value) {
//        response.then()
//                .body(key, equalTo(value));

        String responseBody = response.getBody().asString();
        //logger.info("Validating that the response body contains invoice title: " + endPoint);
        Assert.assertTrue(responseBody.contains(endPoint));
}}
