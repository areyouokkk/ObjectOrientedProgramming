public class User {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;


    // constructor
    public User(String userId, String firstName, String lastName, String email) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // return user id
    public String getUserId() {
        return userId;
    }

}
