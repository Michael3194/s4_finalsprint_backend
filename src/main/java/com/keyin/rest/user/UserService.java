package com.keyin.rest.user;

import com.keyin.domain.User;
import com.keyin.service.PasswordHashingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordHashingService passwordHashingService;

    public User createUser(User user) {

        // Hash the password before saving it
        String hashedPassword = passwordHashingService.hashPassword(user.getPassword());

        // Set the hashed password to the user
        user.setPassword(hashedPassword);

        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    public User authenticateUser(LoginRequest loginRequest) {
        // Retrieve the user by username from the database
        User user = userRepository.findByUsername(loginRequest.getUsername());

        // Check if the user exists and if the password matches
        if (user != null && passwordHashingService.verifyPassword(loginRequest.getPassword(), user.getPassword())) {
            user.setPassword(null); // Set password to null from response for security
            return user;
        } else {
            return null;
        }
    }

}
