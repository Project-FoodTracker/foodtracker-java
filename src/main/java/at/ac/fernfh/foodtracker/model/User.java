package at.ac.fernfh.foodtracker.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "User")
public class User {

    @Transient
    public static final String SEQUENCE_NAME = "user_sequence";

    @Id
    private Long id;

    private String lastName;
    private String firstName;
    private String username;
    private String password;
    private String oAuthProvider;
    private String oAuthUid;

    /**
     * Constructor
     * @param lastName last name of the user
     * @param firstName first name of the user
     * @param username username of the user
     * @param password password of the user
     * @param oAuthProvider oauth provider
     * @param oAuthUid oauth uid
     */
    public User(final String lastName, final String firstName, final String username, final String password,
                final String oAuthProvider, final String oAuthUid) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.username = username;
        this.password = password;
        this.oAuthProvider = oAuthProvider;
        this.oAuthUid = oAuthUid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getoAuthProvider() {
        return oAuthProvider;
    }

    public void setoAuthProvider(String oAuthProvider) {
        this.oAuthProvider = oAuthProvider;
    }

    public String getoAuthUid() {
        return oAuthUid;
    }

    public void setoAuthUid(String oAuthUid) {
        this.oAuthUid = oAuthUid;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", oAuthProvider='" + oAuthProvider + '\'' +
                ", oAuthUid='" + oAuthUid + '\'' +
                '}';
    }
}
