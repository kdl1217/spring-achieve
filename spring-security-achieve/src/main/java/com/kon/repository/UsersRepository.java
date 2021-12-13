package com.kon.repository;

import com.kon.entity.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 用户Repository
 *
 * @author kon, created on 2021/12/9T14:05.
 * @version 1.0.0-SNAPSHOT
 */
@Repository
public interface UsersRepository extends CrudRepository<Users, Long> {

    /**
     * 根据用户名查询用户
     * @param username  用户名
     * @return 用户
     */
    Users findByUsername(String username);

}
