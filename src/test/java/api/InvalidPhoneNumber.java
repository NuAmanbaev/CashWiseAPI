package api;

import entities.CustomResponse;
import entities.RequestBody;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import utilities.CashWiseToken;

public class InvalidPhoneNumber {
    Response response;
    RequestSpecification request;
    RequestBody customRequest = new RequestBody();
    CustomResponse customResponse = new CustomResponse();
    @Given("base url {string}")
    public void base_url(String url) {
        request = RestAssured.given().contentType(ContentType.JSON)
                .accept(ContentType.JSON).baseUri(url);
    }
    @When("I provide  authorization token")
    public void i_provide_authorization_token() {
        request = RestAssured.given().auth().oauth2(CashWiseToken.getToken());
    }
    @Then("I provide company name {string}")
    public void i_provide_company_name(String companyName) {
        customRequest.setCompany_name(companyName);

    }
    @Then("I provide seller name {string}")
    public void i_provide_seller_name(String sellerName) {
        customRequest.setSeller_name(sellerName);
    }
    @Then("I provide seller email {string}")
    public void i_provide_seller_email(String sellerEmail) {
        customRequest.setEmail(sellerEmail);
    }
    @Then("I provide seller invalid phonenumber {string}")
    public void i_provide_seller_invalid_phonenumber(String phoneNumber) {
      customRequest.setPhone_number(phoneNumber);
    }
    @Then("I provide seller address {string}")
    public void i_provide_seller_address(String sellerAddress) {
        customRequest.setAddress(sellerAddress);
    }
    @Then("I hit POST endpoint {string}")
    public void i_hit_post_endpoint(String endPoint) {
        response = RestAssured.given().auth().oauth2(CashWiseToken.getToken()).body(customRequest)
                .post(endPoint);
    }
    @Then("I verify status Code is {int}")
    public void i_verify_code_status_is(int expectedStatusCode) {
        int statusCode = response.statusCode();
        Assert.assertEquals(expectedStatusCode,statusCode);

    }

}
