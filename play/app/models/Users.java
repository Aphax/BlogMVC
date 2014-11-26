package models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NoResultException;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;

import play.db.jpa.JPA;

@Entity
public class Users {

    @Id
    @GeneratedValue
    public Long id;
    public String username;
    public String password;

    @OneToMany(mappedBy="user")
    public List<Posts> posts;
}
