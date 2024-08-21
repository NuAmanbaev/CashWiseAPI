package entities;

import lombok.Data;

import java.util.List;

@Data
public class RequestBody {

    private String name_tag;
    private String description;
    private String email;
    private String password;
    private String company_name;
    private String seller_name;
    private String phone_number;
    private String address;
    List<CustomResponse> responses;

    //create category

    private String category_title;
    private String category_description;
    private boolean flag;



  //  create product
    private String product_title;
    private Integer product_price;
    private Integer service_type_id;
    private Integer category_id;
    private String product_description;
    private String date_of_payment;
    private Integer remind_before_day;
    private String do_remind_every_month;








}
