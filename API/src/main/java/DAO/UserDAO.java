package DAO;

import Entity.User;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

import java.util.List;

public interface UserDAO {

    @SqlQuery("Select * from User;")
    List<User> getAll();

    @SqlUpdate("")
    int createUser(@BindBean("user")User user);

}
