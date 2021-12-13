package com.kon.repository;

import com.kon.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 权限Repository
 *
 * @author kon, created on 2021/12/10T15:10.
 * @version 1.0.0-SNAPSHOT
 */
@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

}
