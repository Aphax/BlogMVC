package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

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

    @Transient()
    public Long count;

    public static List<Categories> findAll() {
        List<Object[]> rows = JPA.em().createQuery("select c, COUNT(p) from Categories c join c.posts p GROUP BY c").getResultList();
        List<Categories> categories = new ArrayList<Categories>();
        for (Object[] row : rows) {
            Categories c = (Categories) row[0];
            c.count = (Long) row[1];
            categories.add(c);
        }
        return categories;
    }
}
