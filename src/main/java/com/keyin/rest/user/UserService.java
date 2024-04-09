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
}
