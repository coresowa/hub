/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sowa.simpleblog.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author pawel
 */
@Entity
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="id", insertable=true, updatable=true, unique=true, nullable=false)
    private Long id;
    
    private String title;
    
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   
    @Override
    public String toString() {
        return "pl.sowa.simpleblog.model[ id=" + id + " ]";
    }
    
}
