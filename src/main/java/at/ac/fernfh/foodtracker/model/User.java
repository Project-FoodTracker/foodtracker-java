package at.ac.fernfh.foodtracker.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "User")
public class User {

    @Id
    private String id;

    private String lastName;
    private String firstName;
    private String username;
    private String password;

    /**
     * Constructor
     *
     * @param lastName  last name of the user
     * @param firstName first name of the user
     * @param username  username of the user
     * @param password  password of the user
     */
    public User(final String lastName, final String firstName, final String username, final String password) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.username = username;
        this.password = password;
    }

    public User() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", lastName='" + lastName + '\'' + ", firstName='" + firstName + '\'' + ", username='" + username +
                '\'' + '}';
    }
}
