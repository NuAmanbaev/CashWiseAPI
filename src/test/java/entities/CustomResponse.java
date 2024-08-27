package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)



public class CustomResponse {

    private int category_id;
    private String category_title;
    private String category_description;
    public boolean flag;
    List<CustomResponse> responses;
    private String email;



}
