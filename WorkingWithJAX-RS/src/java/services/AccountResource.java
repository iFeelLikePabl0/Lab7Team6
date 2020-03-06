/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author colli
 */
@Path("Account")
public class AccountResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AccountResource
     */
    public AccountResource() {
    }

    /**
     * Retrieves representation of an instance of services.AccountResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/html")
    public String getUsers() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }
    
    @DELETE
    @Consumes("text/html")
    public String deleteUser(String content)
    {
        
    }

    /**
     * PUT method for updating or creating an instance of AccountResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("text/html")
    public void putUser(String content) {
    }
}
