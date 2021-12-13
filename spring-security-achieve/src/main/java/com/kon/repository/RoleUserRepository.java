package com.kon.repository;

import com.kon.entity.RoleUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 权限用户Repository
 *
 *      不能用跟ID相关的接口，否则会报错
 *
 * @author kon, created on 2021/12/9T14:04.
 * @version 1.0.0-SNAPSHOT
 */
@Repository
public interface RoleUserRepository extends CrudRepository<RoleUser, Long> {

    /**
     * 根据uid查询
     * @param uid uid
     * @return RoleUser
     */
    List<RoleUser> findByUid(Long uid);
}
