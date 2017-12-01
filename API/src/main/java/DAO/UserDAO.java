package DAO;

import Entity.User;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

import java.util.List;

import static Helpers.Constants.USER;

public interface UserDAO {

    @SqlQuery("Select * from " + USER + ";")
    List<User> getAll();

    @SqlUpdate("insert into " + USER + "(UserID, firstname, surname, email, username, bio, photo, preferences, weight, height, BMI)" +
            " values(:user.userID, :user.firstname, :user.surname, :user.email, :user.username, :user.bio, :user.photo, :user.preferences, :user.weight, :user.height, :user.bmi);")
    int createUser(@BindBean("user")User user);

}
