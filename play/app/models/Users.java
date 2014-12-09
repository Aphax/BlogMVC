package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
