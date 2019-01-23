package nl.hsleiden.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import nl.hsleiden.View;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;


public class Sock {
    @NotEmpty
    @Length(min = 1, max = 10)
    @JsonView(View.Public.class)
    private String sock_name;

    @NotEmpty
    @Length(min = 1, max = 5)
    @JsonView(View.Public.class)
    private String sock_size;
    
    @NotEmpty
    @Length(min = 1, max = 10)
    @JsonView(View.Public.class)
    private String sock_color;
    
    @JsonView(View.Public.class)
    private int sock_price;
    
    @JsonView(View.Public.class)
    private int sock_amount_stocked;
    
    @JsonView(View.Public.class)
    private String sock_url; 

    public String getSock_url() {
        return sock_url;
    }

    public void setSock_url(String sock_url) {
        this.sock_url = sock_url;
    }
   

    public String getSock_name() {
        return sock_name;
    }

    public void setSock_name(String sock_name) {
        this.sock_name = sock_name;
    }

    public String getSock_size() {
        return sock_size;
    }

    public void setSock_size(String sock_size) {
        this.sock_size = sock_size;
    }

    public String getSock_color() {
        return sock_color;
    }

    public void setSock_color(String sock_color) {
        this.sock_color = sock_color;
    }

    public int getSock_price() {
        return sock_price;
    }

    public void setSock_price(int sock_price) {
        this.sock_price = sock_price;
    }

    public int getSock_amount_stocked() {
        return sock_amount_stocked;
    }

    public void setSock_amount_stocked(int sock_amount_stocked) {
        this.sock_amount_stocked = sock_amount_stocked;
    }
}
