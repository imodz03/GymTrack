package Entity;

import com.google.gson.JsonObject;

public class User {

    private String userID;
    private String firstname;
    private String surname;
    private String username;
    private String preferences;

    private String email;
    private String bio;
    private String photo;

    private int height;
    private int weight;
    private int bmi;
    private int bodyfatPerc;

    private String password;

    public User(){}

    public User(String userID){
        this.userID = userID;
    }

    public User(String userID, String firstname, String surname, String username, String preferences) {
        this();
        this.userID = userID;
        this.firstname = firstname;
        this.surname = surname;
        this.username = username;
        this.preferences = preferences;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getBmi() {
        return bmi;
    }

    public void setBmi(int bmi) {
        this.bmi = bmi;
    }

    public int getBodyfatPerc() {
        return bodyfatPerc;
    }

    public void setBodyfatPerc(int bodyfatPerc) {
        this.bodyfatPerc = bodyfatPerc;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "\nuserID='" + userID + '\'' +
                ", \nfirstname='" + firstname + '\'' +
                ", \nsurname='" + surname + '\'' +
                ", \nusername='" + username + '\'' +
                ", \npreferences=" + preferences +
                ", \nemail='" + email + '\'' +
                ", \nbio='" + bio + '\'' +
                ", \nphoto='" + photo + '\'' +
                ", \nheight=" + height +
                ", \nweight=" + weight +
                ", \nbmi=" + bmi +
                ", \nbodyfatPerc=" + bodyfatPerc +
                "\n}";
    }
}
