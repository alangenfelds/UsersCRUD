package org.mytraining.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.mytraining.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;



@Repository
public class UserDAOImpl implements UserDAO{

    private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }


    //pagination   http://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/html/repositories.html


//    String countQ = "Select count (u.id) from User u";
//    Query countQuery = this.sessionFactory.getCurrentSession().createQuery(countQ);
//    Long countResults = (Long) countQuery.uniqueResult();
//
//    int pageSize = 10;
//    int lastPageNumber = (int) ((countResults / pageSize) + 1);




    //end of pagination


    @Override
    public void addUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(user);
        logger.info("User saved successfully, User Details="+user);
    }

    @Override
    public void updateUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(user);
        logger.info("Person updated successfully, Person Details="+user);
    }

    @Override
    public List<User> listUsers() {

        Session session = this.sessionFactory.getCurrentSession();

        List<User> userList = session.createQuery("from User").list();

        for(User u : userList){
            logger.info("User List::"+u);
        }

        return userList;
    }

    @Override
    public List<User> listUsersByName(String name) {
        Session session = this.sessionFactory.getCurrentSession();

        String hql = "from User U where U.name = '"+name+"'";
        //String hql = "from User U where U.name like '"+name+"%'";


        List<User> userList = session.createQuery(hql).list();

        for(User u : userList){
            logger.info("UsersByName List::"+u);
        }

        return userList;
    }

    @Override
    public User getUserById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, new Integer(id));
        logger.info("User loaded successfully, User details="+user);
        return user;
    }

    @Override
    public void removeUser(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, new Integer(id));
        if(null != user){
            session.delete(user);
        }
        logger.info("User deleted successfully, user details="+user);
    }
}
