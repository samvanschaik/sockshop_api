/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hsleiden.resource;

import com.fasterxml.jackson.annotation.JsonView;
import io.dropwizard.auth.Auth;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.basic.BasicCredentials;
import java.util.Optional;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import nl.hsleiden.View;
import nl.hsleiden.model.Sock;
import nl.hsleiden.model.User;
import nl.hsleiden.service.AuthenticationService;
import nl.hsleiden.service.UserService;


/**
 *
 * @author Sam
 */

@Singleton
@Path("/auth")
public class AuthenticationResource {
    
    private final AuthenticationService authService;
    private final UserService userService;
    
    @Inject
    public AuthenticationResource(
            AuthenticationService authService,
            UserService userService){
        this.authService = authService; 
        this.userService = userService;
    }
    
    
    @Path("login")
    @GET
    @JsonView(View.Public.class)
    @RolesAllowed("GUEST")
    @Produces(MediaType.APPLICATION_JSON)
    public User authenticate(@Auth User user) throws AuthenticationException {
           return user;
    }
}
