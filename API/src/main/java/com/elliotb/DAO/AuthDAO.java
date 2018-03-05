package com.elliotb.DAO;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;

import static com.elliotb.Helpers.Constants.USER;

public interface AuthDAO {

    @SqlQuery("Select UserID from " + USER
            + " WHERE username = :user AND password = :pass")
    String login(@Bind("user")String username, @Bind("pass")String password);

}
