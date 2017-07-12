/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sowa.simpleblog.service;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.jglue.fluentjson.JsonBuilderFactory;

/**
 *
 * @author pawel
 */

@Stateless  
@Path(HelloPierceREST.ENDPOINT)
public class HelloPierceREST {
    protected final static String ENDPOINT = "hello-pierce";
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello() {
        return Response.ok(JsonBuilderFactory.buildObject().add("message", "Hello Pierce").
                            toString()).build();
    } 
}
