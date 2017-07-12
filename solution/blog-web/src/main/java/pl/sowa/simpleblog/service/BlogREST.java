/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sowa.simpleblog.service;


import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import org.jglue.fluentjson.JsonBuilderFactory;
import pl.sowa.ejb.BlogBean;
import pl.sowa.simpleblog.model.Blog;

/**
 *
 * @author pawel
 */
@Stateless  
@Path(BlogREST.ENDPOINT)
public class BlogREST  {
    protected final static String ENDPOINT = "posts";
    
    @EJB
    private BlogBean blogBean;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Blog> findAll(@Context UriInfo uriInfo) {
        return blogBean.findAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createEntry(@Context UriInfo uriInfo, Blog entity) {
        blogBean.create(entity);
        return Response.status(Status.OK).entity(JsonBuilderFactory.buildObject().
                                                 add("postId", entity.getId()).
                                                 add("example", uriInfo.getBaseUri() + ENDPOINT + "/" + entity.getId() ).toString()).build();
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") Long id) {
        Blog result = blogBean.find(id);
        return result!=null?Response.status(Status.OK).entity(result).build()
                : Response.status(Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        Blog remove = blogBean.find(id);
        if(remove!=null){
            blogBean.remove(remove);
            return Response.status(Status.OK).build();
        }
        else{
            return Response.status(Status.NOT_FOUND).build();
        }
    }
}
