package com.kon.service;

import com.kon.dao.UserDao;
import com.kon.entity.User;
import com.kon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * note
 *
 * @author kon, created on 2021/12/6T10:44.
 * @version 1.0.0-SNAPSHOT
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRepository userRepository;

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findUserById(Long id) {
        return userDao.findById(id);
    }
}
