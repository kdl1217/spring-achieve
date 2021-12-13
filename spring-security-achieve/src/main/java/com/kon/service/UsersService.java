package com.kon.service;

import com.kon.entity.Users;
import com.kon.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 用户接口信息
 *
 * @author kon, created on 2021/12/10T17:17.
 * @version 1.0.0-SNAPSHOT
 */
@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Users getUsers(Long id) {

        return usersRepository.findById(id).orElse(null);
    }

}
