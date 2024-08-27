package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import entities.CustomResponse;
import entities.RequestBody;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.CashWiseToken;
import utilities.Config;

import java.util.HashMap;
import java.util.Map;

public class CreateCategory {

    Faker faker = new Faker();

    @Test
    public void createCategory() throws JsonProcessingException, com.fasterxml.jackson.core.JsonProcessingException {
        String url = Config.getProperty("baseCashwiseUrl")+ "/api/myaccount/sellers";
        String token = CashWiseToken.getToken();


        RequestBody requestBody = new RequestBody();
        requestBody.setCompany_name(faker.name().title());
        requestBody.setCategory_description(faker.address().fullAddress());
        requestBody.setFlag(true);

        Response response = RestAssured
                .given().auth()
                .oauth2(token)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(url);

        int status = response.getStatusCode();
        Assert.assertEquals(201,status);
        ObjectMapper mapper = new ObjectMapper();
        CustomResponse customResponse = mapper.readValue(response.asString(), CustomResponse.class);
        System.out.println(customResponse.getCategory_id());


    }

    @Test
    public void getCategory(){


        String url = Config.getProperty("baseCashwiseUrl") + "/api/myaccount/categories";
        String token1 = CashWiseToken.getToken();
        RequestBody requestBody = new RequestBody();
        requestBody.setCategory_title(faker.name().title());
        requestBody.setCategory_description(faker.address().firstName());
        requestBody.setFlag(true);

        Response response = RestAssured
                .given()
                .auth()
                .oauth2(token1)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(url);
        int status1 = response.statusCode();

        String id = response.jsonPath().getString("category_id");
        Assert.assertEquals(201,status1);

        String url2 = Config.getProperty("baseCashwiseUrl") + "/api/myaccount/sellers/" + id;


        Response response1 = RestAssured
                .given()
                .auth()
                .oauth2(token1)
                .get(url2);
        Assert.assertEquals(200,response1.getStatusCode());
        System.out.println(id);

    }

}