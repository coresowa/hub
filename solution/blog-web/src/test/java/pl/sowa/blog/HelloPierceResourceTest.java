/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sowa.blog;


import javax.ws.rs.core.Response;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pl.sowa.simpleblog.service.HelloPierceREST;

/**
 *
 * @author pawel
 */
public class HelloPierceResourceTest {
    
    public HelloPierceResourceTest() {
    }
    
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testHello(){
        Response helloResponse = new HelloPierceREST().hello();
        String hello = (String)helloResponse.getEntity();
        assertEquals("{\"message\":\"Hello Pierce\"}", hello); 
    }
}
