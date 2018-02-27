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

    @SqlQuery("Select preferences from " + USER + " WHERE UserID = :id;")
    String getPrefs(@Bind("id") String id);

    @SqlUpdate("insert into " + USER + "(UserID, firstname, surname, email, username, bio, photo, preferences, weight, height, BMI, password)" +
            " values(:user.userID, :user.firstname, :user.surname, :user.email, :user.username, :user.bio, :user.photo, :user.preferences, :user.weight, :user.height, :user.bmi, :user.password);")
    int createUser(@BindBean("user")User user);

    @SqlUpdate("update " + USER + " set firstname = :user.firstname, surname = :user.surname, email = :user.email, username = :user.username, preferences = :user.preferences, bio = :user.bio, photo = :user.photo, height = :user.height, weight = :user.weight, BMI = :user.bmi, bodyFatPercentage = :user.bodyfatPerc")
    int update(@BindBean("user")User user);

    @SqlUpdate("delete from "+ USER + " Where UserID = :id")
    int delete(@Bind("id")String id);

    @SqlQuery("select username from " + USER + " WHERE username = :name LIMIT 1;")
    String getUsernames(@Bind("name")String username);

}
