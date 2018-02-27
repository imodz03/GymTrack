package Entity.mappers;

import Entity.User;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import jdk.nashorn.internal.parser.JSONParser;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements ResultSetMapper<User> {

    @Override
    public User map(int index, ResultSet r, StatementContext ctx) throws SQLException {

        User mapped = new User();

        mapped.setUserID(r.getString(1));
        mapped.setFirstname(r.getString(2));
        mapped.setSurname(r.getString(3));
        mapped.setEmail(r.getString(4));
        mapped.setUsername(r.getString(5));
        mapped.setBio(r.getString(6));
        mapped.setPhoto(r.getString(7));
        mapped.setPreferences(r.getString(8)); //fix this
        mapped.setWeight(r.getInt(9));
        mapped.setHeight(r.getInt(10));
        mapped.setBmi(r.getInt(11));

        return mapped;
    }

    //Select UserID, firstname, surname, email, username, bio, photo, preferences, weight, height, BMI from
}
