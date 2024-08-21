package entities;

import lombok.Data;
@Data
public class RequestBody {

    private String name_tag;
    private String description;
    private String email;
    private String password;

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



    


    public void setEmail ( String email ) {
        this.email = email;
    }

    public String getEmail () {
        return email;
    }

    public void setPassword ( String password ) {
        this.password = password;
    }

    public String getPassword () {
        return password;
    }
}