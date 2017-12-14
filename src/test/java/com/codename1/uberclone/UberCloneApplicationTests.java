package com.codename1.uberclone;

import com.codename1.uberclone.api.UserService;
import com.codename1.uberclone.dao.UserDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class UberCloneApplicationTests {
    @Autowired
    private UserService userAPI;
    
    @Test
    public void contextLoads() {
    }

    @Test
    public void addRemoveUser() {
        /*assertThat(userAPI.countUsers()).isEqualTo(0);
        UserDAO ud = new UserDAO(null, "Shai", "Almog", "999", "bla@bla.com", null, null, false, null, 0, 0, 0, null);
        userAPI.addUser(ud);
        assertThat(userAPI.countUsers()).isEqualTo(1);
        UserDAO ud2 = userAPI.getUserByPhone("999");
        ud.setId(ud2.getId());
        assertThat(ud).isEqualTo(ud2);
        userAPI.deleteUser(ud2.getId());
        ud2 = userAPI.getUserByPhone("999");
        assertThat(ud2).isNull();*/
    }
}
