package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import play.db.jpa.JPA;

@Entity
public class Categories {

    @Id
    @GeneratedValue
    public Long id;
    public String name;
    public String slug;
    public Long post_count;

    @OneToMany(mappedBy="category")
    public List<Posts> posts;
}
