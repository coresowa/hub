/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sowa.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pl.sowa.simpleblog.model.Blog;

/**
 *
 * @author pawel
 */
@Stateless
public class BlogBean extends AbstractCRUD<Blog> {

    @PersistenceContext(unitName = "pl.sowa_simpleblogPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    protected Class<Blog> getEntityClass() {
        return Blog.class;
    }

    public String sayHallo(){
        return "I'm the bean em!=null? " + (em!=null);
    }


    
}
