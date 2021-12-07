package com.kon.dao;

import com.kon.entity.User;

/**
 * UserDao
 *
 * @author kon, created on 2021/12/3T11:45.
 * @version 1.0.0-SNAPSHOT
 */
public interface UserDao {

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    User findById(Long id);
}
