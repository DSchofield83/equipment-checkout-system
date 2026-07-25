package service;

import model.User;
import java.util.ArrayList;
import java.util.List;
import service.UserStore;

public class AuthenticationService 
{

    private final List<User> users = new ArrayList<>();
    

    /**
     * Adds a user to the authentication store.
     * (Temporary in-memory version)
     */
    public void addUser(User user) 
    {
        users.add(user);
    }

    /**
     * Attempts to authenticate a user.
     *
     * @param employeeId the user-entered employee ID
     * @param password the user-entered password
     * @return the authenticated User, or null if invalid
     */
    public User authenticate(String employeeId, String password) 
    {
        
        
        // Pull the user from your existing in-memory store
        User user = UserStore.findByUsername(employeeId);  // name is odd, but use it as-is

        if (user == null) 
        {
            return null;
        }

        // Use your User model’s password check (or compare directly if you don’t have checkPassword
        if (!user.checkPassword(password)) 
        {
            return null;
        }

    return user;
    }
    
    private boolean isPasswordValidFormat( String password )
    {
        // governs the character length of the password
        if( password.length() < 8 || password.length() > 16 )
        {
            return false;
        }
        
        // forces the inclusion of special characters
        return password.matches(".*[!@#$%^&*()].*");
    }
}
