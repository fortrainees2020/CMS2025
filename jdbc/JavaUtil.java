package jdbc;

import java.sql.*;

public class JavaUtil  {

	// Establishing the connection 
	/* username, password, url
	 * */
    public Connection getConnection() {
        String dbURL = "jdbc:mysql://localhost:3306/florida?useSSL=false";
        String username = "root";
        String password = "rootroot";

        try {
            Connection conn = DriverManager.getConnection(dbURL, username, password);
            if (conn != null) {
                return conn;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    
    // Insert User using User object
    public void insertUser(User user) {
    	// Set up a query which you would like to fire 
        String sql = "INSERT INTO Users (id, email, firstname, lastname) VALUES (?, ?, ?, ?)"; // parameters
        //Get a connection reference 
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
        	
        	//Set the values of paramters
            statement.setLong(1, user.getId());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getFirstname());
            statement.setString(4, user.getLastname());
            
            //Execute this Query 
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Error inserting user: " + e.getMessage());
        }
    }
   
    public void displayRecord() {
        String sql = "SELECT * FROM Users";
        try (Connection conn = getConnection();
             Statement statement = conn.createStatement();
             ResultSet result = statement.executeQuery(sql)) {
        	
        	//Fetch the record from result till next() return true
            while (result.next()) {
                long id = result.getLong("id");
                String email = result.getString("email");
                String firstname = result.getString("firstname");
                String lastname = result.getString("lastname");
                System.out.println("Id:" + id + ", Name: " + firstname + " " + lastname + ", Email: " + email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    
    
    
    
    
    
    
    
    
    
    
    public void deleteRecord(long id) {
    	//Set up a query
        String sql = "DELETE FROM Users WHERE id=?";
        
        try (Connection conn = getConnection();
        	//Create the preparedStatement
             PreparedStatement statement = conn.prepareStatement(sql)) {
        	// Pass the userid which you want to delete 
            statement.setLong(1, id);
            //Execute the query
            int rowsDeleted = statement.executeUpdate();
            // 
            if (rowsDeleted > 0) {
                System.out.println("A user was deleted successfully!");
            } else {
                System.out.println("No user found with id: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update User info using User object (only id needed, other fields updated here for demo)
    public void updateRecord(User user) {
    	// Set up query for update 
        String sql = "UPDATE Users SET firstname=?, lastname=?, email=? WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, user.getFirstname());
            statement.setString(2, user.getLastname());
            statement.setString(3, user.getEmail());
            statement.setLong(4, user.getId());

            int rowsUpdated = statement.executeUpdate();
            
            if (rowsUpdated > 0) {
                System.out.println("An existing user was updated successfully!");
            } else {
                System.out.println("No user found with id: " + user.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
