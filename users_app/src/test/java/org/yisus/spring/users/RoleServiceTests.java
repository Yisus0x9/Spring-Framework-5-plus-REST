package org.yisus.spring.users;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.yisus.spring.users.entities.Profile;
import org.yisus.spring.users.entities.Role;
import org.yisus.spring.users.services.ProfileService;
import org.yisus.spring.users.services.RoleService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleServiceTests {
    @Autowired
    private RoleService roleService;

    @Test(expected = AuthorizationDeniedException.class)
    @WithMockUser(username = "User1",password = "pass",roles ={"ADMIN"})
    public void testServiceWithWrongCredentials(){
        Role role= new Role();
        role.setName("TEST");
        roleService.save(role);
    }

    @Test
    @WithMockUser(username = "User1",password = "pass",roles ={"ADMIN","SUPER_ADMIN"})
    public void testServiceWithGoodCredentials(){
        Role role= new Role();
        role.setName("TEST3");
        Role save = roleService.save(role);

        assert save.getId()!=null;
    }
}
