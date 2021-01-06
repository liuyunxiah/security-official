package com.lyx.securityofficial.securityofficial;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;

@SpringBootTest
class SecurityOfficialApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void password() {
        System.out.println(BCrypt.hashpw("123", BCrypt.gensalt()));
        System.out.println(BCrypt.checkpw("123", "$2a$10$cctcNYS/SrEJXviAza1HhOQlAukFEgEkC01r6keoGnE6oHZUOxv8O"));
    }

}
