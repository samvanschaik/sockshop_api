package nl.hsleiden.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import java.security.Principal;
import nl.hsleiden.View;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;


public class User implements Principal
{
    @NotEmpty
    @Length(min = 3, max = 25)
    @JsonView(View.Public.class)
    private String user_name;
    
    @NotEmpty
    @Length(min = 6, max = 7)
    @JsonView(View.Public.class)
    private String user_postcode;
    
    @NotEmpty
    @Length(min = 1, max = 10)
    @JsonView(View.Public.class)
    private String user_streetnumber;
    
    @NotEmpty
    @Email
    @JsonView(View.Public.class)
    private String user_email;
    
    @NotEmpty
    @Length(min = 8)
    @JsonView(View.Private.class)
    private String user_password;
    
    @JsonView(View.Public.class)
    private String[] user_roles;

    @JsonProperty
    public String getUser_name() {
        return user_name;
    }

    @JsonProperty
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    @JsonProperty
    public String getUser_postcode() {
        return user_postcode;
    }

    @JsonProperty
    public void setUser_postcode(String user_postcode) {
        this.user_postcode = user_postcode;
    }

    @JsonProperty
    public String getUser_streetnumber() {
        return user_streetnumber;
    }

    @JsonProperty
    public void setUser_streetnumber(String user_streetnumber) {
        this.user_streetnumber = user_streetnumber;
    }

    @JsonProperty
    public String getUser_email() {
        return user_email;
    }

    @JsonProperty
    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    @JsonProperty
    public String getUser_password() {
        return user_password;
    }

    @JsonProperty
    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    @JsonProperty
    public String[] getUser_roles() {
        return user_roles;
    }
    
    @JsonProperty
    public void setUser_roles(String[] user_roles) {
        this.user_roles = user_roles;
    }
    
    @JsonProperty
    public boolean hasRole(String roleName)
    {
        if (user_roles != null)
        {
            for(String role : user_roles)
            {
                if(roleName.equals(role))
                {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    @JsonProperty
    public boolean equals(User user)
    {
        return user_email.equals(user.getUser_email());
    }

    @Override
    public String getName() {
        return user_name;
    }
}
