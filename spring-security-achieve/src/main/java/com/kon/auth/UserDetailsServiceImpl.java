package com.kon.auth;

import com.kon.entity.Users;
import com.kon.repository.MenuRepository;
import com.kon.repository.RoleRepository;
import com.kon.repository.UsersRepository;
import com.kon.security.UserExtend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户详情Service接口实现
 *
 * @author kon, created on 2021/12/10T14:55.
 * @version 1.0.0-SNAPSHOT
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名查询用户
        Users users = usersRepository.findByUsername(username);

        if (null != users) {
            List<String> roles = menuRepository.findPermissionByUid(users.getId());

            String[] roleArr = new String[roles.size()];
            roleArr = roles.toArray(roleArr);

            return new UserExtend(users.getId(),
                    users.getUsername(),
                    users.getPassword(),
                    AuthorityUtils.createAuthorityList(roleArr));
        }
        return null;
    }
}
