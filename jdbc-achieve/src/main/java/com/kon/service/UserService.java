package com.kon.service;

import com.kon.entity.User;
import com.kon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * note
 *
 * @author kon, created on 2021/12/2T17:56.
 * @version 1.0.0-SNAPSHOT
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    public User save() {
        User user = new User();
        user.setName("KonJJ");
        user.setAge(17);
        user.setText("This is Text");
        return userRepository.save(user);
    }
}
