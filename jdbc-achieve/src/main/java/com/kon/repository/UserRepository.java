package com.kon.repository;

import com.kon.entity.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository
 *
 * @author kon, created on 2021/12/2T16:41.
 * @version 1.0.0-SNAPSHOT
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * 查询
     * @param name
     * @return
     */
    @Query("SELECT * FROM user WHERE name =:name")
    List<User> findByName(String name);

}
