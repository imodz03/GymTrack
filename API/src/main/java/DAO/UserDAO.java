package DAO;

import Entity.User;
import Entity.mappers.UserMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

import static Helpers.Constants.USER;

public interface UserDAO {

    @Mapper(UserMapper.class)
    @SqlQuery("Select UserID, firstname, surname, email, username, bio, photo, preferences, weight, height, BMI from " + USER + ";")
    List<User> getAll();

    @Mapper(UserMapper.class)
    @SqlQuery("Select UserID, firstname, surname, email, username, bio, photo, preferences, weight, height, BMI from " + USER +
    " WHERE UserID = :id")
    User getById(@Bind("id") String id);

    @SqlUpdate("insert into " + USER + "(UserID, firstname, surname, email, username, bio, photo, preferences, weight, height, BMI)" +
            " values(:user.userID, :user.firstname, :user.surname, :user.email, :user.username, :user.bio, :user.photo, :user.preferences, :user.weight, :user.height, :user.bmi);")
    int createUser(@BindBean("user")User user);

}
