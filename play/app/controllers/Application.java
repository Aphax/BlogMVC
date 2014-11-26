package controllers;

import java.util.List;

import play.*;
import play.mvc.*;
import play.db.jpa.*;
import models.*;
import views.html.*;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;


public class Application extends Controller {

    public static Result admin() {
        return ok(views.html.admin.render());
    }

    @Transactional
    public static Result author(String author) {
        TypedQuery<Users> query = JPA.em().createQuery("select u from Users u WHERE u.username=:author", Users.class)
            .setParameter("author",author)
            .setMaxResults(1);
        Users user = query.getSingleResult();
        return ok(views.html.posts.render(user.posts));
    }

    @Transactional
    public static Result category(String slug) {
        TypedQuery<Categories> query = JPA.em().createQuery("select c from Categories c WHERE c.slug=:slug", Categories.class);
        query.setParameter("slug",slug);
        query.setMaxResults(1);
        Categories category = query.getSingleResult();
        return ok(views.html.posts.render(category.posts));
    }

    @Transactional
    public static Result index() {
        Query query = JPA.em().createQuery("select p from Posts p");
        List<Posts> posts = query.getResultList();
        return ok(views.html.posts.render(posts));
    }

    public static Result login() {
        return ok(views.html.login.render());
    }

    /**
     * Utilisation avancée de l'EntityManager de JPA
     * EntityManager.getCriteriaBuilder() -> CriteriaBuilder
     *
     * CriteriaBuilder : Used to construct criteria queries, compound selections, expressions, predicates, orderings.
     * It serves as the main factory of criteria queries and criteria query elements
     * https://docs.oracle.com/javaee/6/api/index.html
     * CriteriaBuilder.createQuery(Class.class) -> CriteriaQuery
     *
     * CriteriaQuery : Représente la requête construite, permet d'utiliser les fonctionnalité de haut niveau des requêtes (select,from,where,groupby,having...)
     * https://docs.oracle.com/javaee/6/api/index.html
     * CriteriaQuery.from -> Root
     *
     * A Root instance is created to define a range variable in the FROM clause.
     * The range variable is also used in the SELECT clause as the query result expression.
     *
     */
    @Transactional
    public static Result post(String slug) {

        // Requête plus complexe en utilisant le CriteriaBuilder et CriteriaQuery
        // Use an EntityManager instance to create a CriteriaBuilder object.
        EntityManager em = JPA.em();
        // Create a query object by creating an instance of the CriteriaQuery interface. This query object’s attributes will be modified with the details of the query.
        CriteriaBuilder cb = em.getCriteriaBuilder();
        // Set the query root by calling the from method on the CriteriaQuery object.
        CriteriaQuery<Posts> cq = cb.createQuery(Posts.class);
        // Specify what the type of the query result will be by calling the select method of the CriteriaQuery object.
        Root<Posts> root = cq.from(Posts.class);
        cq.select(root);
        cq.where(cb.equal(root.get("slug"),slug));
        // Prepare the query for execution by creating a TypedQuery<T> instance, specifying the type of the query result.
        TypedQuery<Posts> q = em.createQuery(cq);

        // Requête simple en utilisant du JPQL (Java Persistence Query Langage)
        // TypedQuery<Posts> q = JPA.em().createQuery("select p from Posts p WHERE p.slug=:slug",Posts.class);
        // q.setParameter("slug",slug);
        // q.setMaxResults(1);

        return ok(views.html.post.render(q.getSingleResult()));
    }
}
