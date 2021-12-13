package com.kon.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * 扩展Security用户
 *
 * @author kon, created on 2021/12/10T15:07.
 * @version 1.0.0-SNAPSHOT
 */
public class UserExtend extends User {

    /**
     * 用户ID
     */
    private Long id;

    public UserExtend(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public UserExtend(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        setId(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
