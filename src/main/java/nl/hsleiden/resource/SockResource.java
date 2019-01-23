package nl.hsleiden.resource;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.inject.Singleton;
import io.dropwizard.auth.Auth;
import java.util.Collection;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import nl.hsleiden.View;
import nl.hsleiden.model.Sock;
import nl.hsleiden.service.SockService;

@Singleton
@Path("/socks")
@Produces(MediaType.APPLICATION_JSON)
public class SockResource
{
    private final SockService service;
    
    @Inject
    public SockResource(SockService service)
    {
        this.service = service;
    }
    
    @GET
    @JsonView(View.Public.class)
    @RolesAllowed("GUEST")
    public Collection<Sock> retrieveAll()
    {
        return service.getAll();
    }
    
    @GET
    @Path("/{sock_name}")
    @JsonView(View.Public.class)
    @RolesAllowed("GUEST")
    public Sock retrieve(@PathParam("sock_name") String sock_name)
    {
        return service.get(sock_name);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Public.class)
    @RolesAllowed("ADMIN")
    public void create(Sock sock)
    {
        service.add(sock);
    }
    
    @PUT
    @Path("/{sock_name}")
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    @RolesAllowed("GUEST")
    public void update(@PathParam("sock_name") String sock_name, Sock sock)
    {
        service.update(sock_name, sock);
    }
    
    @DELETE
    @Path("/{sock_name}")
    @RolesAllowed("ADMIN")
    public void delete(@PathParam("sock_name") String sock_name)
    {
        service.delete(sock_name);
    }
    

}
