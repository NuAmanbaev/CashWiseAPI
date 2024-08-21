package entities;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import utilities.CashWiseToken;
import utilities.Config;

public class test {
    @Test
    public void test(){

        String token = CashWiseToken.getToken();
        String url  = Config.getProperty("baseCashwiseUrl") + "/api/myaccount/clients";

        Faker faker = new Faker();
        RequestBody requestBody = new RequestBody();
        requestBody.setCompany_name("whatever");
        requestBody.setClient_name("myName");
        requestBody.setEmail(faker.internet().emailAddress());
        requestBody.setPhone_number("2132312313");
        requestBody.setAddress("whatever234");
        requestBody.setTags_id(new int[]{12});

        Response response = RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON).body(requestBody).post(url);

        int status = response.statusCode();
        System.out.println(status);
    }
}
