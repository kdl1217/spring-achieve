import com.kon.App;
import com.kon.entity.Menu;
import com.kon.entity.RoleUser;
import com.kon.entity.Users;
import com.kon.repository.MenuRepository;
import com.kon.repository.RoleUserRepository;
import com.kon.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Repository Test
 *
 * @author kon, created on 2021/12/9T14:15.
 * @version 1.0.0-SNAPSHOT
 */
@Slf4j
@SpringBootTest(classes = App.class)
public class RepositoryTest {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private RoleUserRepository roleUserRepository;

    @Test
    @Disabled
    public void testUsers() {
        Users users = usersRepository.findById(1L).orElse(null);
        log.info("users {}", users);
        Assert.notNull(users, "users is null");
    }

    @Test
    public void testMenu() {
        Menu menu = menuRepository.findById(1L).orElse(null);
        log.info("menu {}", menu);
        Assert.notNull(menu, "menu is null");
    }

    @Test
    public void testRoleUser() {
        List<RoleUser> roleUsers = roleUserRepository.findByUid(1L);
        log.info("roleUsers {}", roleUsers);
        Assert.notNull(roleUsers, "menu is null");
    }
}
