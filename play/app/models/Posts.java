package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NoResultException;
import javax.persistence.ManyToOne;

import play.db.jpa.JPA;

import org.joda.time.DateTime;
import org.hibernate.annotations.Type;

@Entity
public class Posts {

    @Id
    @GeneratedValue
    public Long id;
    public String name;
    public String slug;
    public String content;

    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    public DateTime created;

    @ManyToOne
    public Categories category;

    @ManyToOne
    public Users user;

    public String getCreated() {
        return new DateTime().toString("dd/MM/YYYY");
    }

    public String getResume(Integer length) {

        if(length == null)
            length = 300;
        if(this.content.length() > length)
            return this.content.substring(0,length) + "...";
        else
            return this.content + "...";
    }

    public String toString() {
        return "id : " + this.id + ", name : " + this.name + " ";
    }

/*    public Date created;

    @ManyToOne
    public Author author;

    @OneToMany
    public List<Comment> comments;*/
}
