package com.kon.repository;

import com.kon.entity.Menu;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 目录Repository
 *
 * @author kon, created on 2021/12/9T15:41.
 * @version 1.0.0-SNAPSHOT
 */
@Repository
public interface MenuRepository extends CrudRepository<Menu, Long> {

    /**
     * 根据UID查询权限信息
     * @param uid 用户ID
     * @return 权限信息
     */
    @Query("SELECT m.permission\n" +
            "FROM menu m\n" +
            "LEFT JOIN role_menu rm ON m.id = rm.mid\n" +
            "LEFT JOIN role r ON r.id = rm.rid\n" +
            "LEFT JOIN role_user ru ON r.id = ru.rid\n" +
            "LEFT JOIN users u ON ru.uid = u.id\n" +
            "WHERE u.id = :uid")
    List<String> findPermissionByUid(Long uid);
}
