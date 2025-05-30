package jdbc;

public class User {
    private static long idCounter = 0;  // static counter to generate unique IDs

    private long id;
    private String firstname;
    private String lastname;
    private String email;
   

    public User() {
        this.id = generateId();
    }

    //paramaetized constructor
    public User(String firstname, String lastname, String email) {
        this.id = generateId();
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

  //paramaetized constructor with ID
    public User(long id, String firstname, String lastname, String email) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    private synchronized long generateId() {
        return ++idCounter;  // increment and return new ID
    }

    // Getters and setters

    public long getId() {
        return id;
    }

    // You may want to remove setId if ID is always auto-generated
    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
