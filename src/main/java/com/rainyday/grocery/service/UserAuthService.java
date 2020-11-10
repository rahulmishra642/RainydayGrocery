package com.rainyday.grocery.service;

import java.util.*;
import java.util.stream.Collectors;

import com.rainyday.grocery.requestresponse.UserInfo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserAuthService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static List<UserInfo> userList = new ArrayList<>();

    private static Set<String> allRoles = new HashSet<String>(){{
        add("ROLE_ADMIN");
        add("ROLE_EMPLOYEE");

    }};
    private static Set<String> employeeRole = Collections.singleton("ROLE_EMPLOYEE");

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo userInfo = userList.stream().filter(user -> user.getUserName().equals(username)).findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        List<GrantedAuthority> grantedAuthorities = Collections.emptyList();
        return new org.springframework.security.core.userdetails.
                User(userInfo.getUserName(), userInfo.getPassword(),
                grantedAuthorities);
    }

    static {
        userList.add(new UserInfo("Rahul","$2a$10$.SLc94mN/MnnBFunMc937.N7d12UhQ4ReNfBbodN6tfkPHXnIAGV6",allRoles));
        userList.add(new UserInfo("cradle","$2a$04$ak8uqta6hiVZ8KDaYi9LWOqoQYJaueHzwODoxpjS3KK1tGBIk7YNy",allRoles));
        userList.add(new UserInfo("crayon","$2a$04$thQ99TG5AWYlPSPSeAK/c.Oe9XvIxKm.hya0F6zlELABe964U58ye",employeeRole));
        userList.add(new UserInfo("John","$2a$10$z6mM/sGKZNT9/tphlsOt0.3SiWwQZqq.nDx0itO.XgS.aik3.QHta",employeeRole));
        userList.add(new UserInfo("Trump","$2a$10$mgvRY/1q51kHGZ8TdmqxoOW7JarXGtGe8dNqZo3LZAYFrs/SHZs..",employeeRole));
        userList.add(new UserInfo("Smith","$2a$10$v56zl/ClE9gS5zlbKV7kcOsYQLfIR/F6i1P1Bwdi8CZRG047ObFMy",employeeRole));
        userList.add(new UserInfo("Jack","$2a$10$zZB4u7YQqSs.4c3L5zg.9ezCrOklZ26.nQYlZxRt7a8uAmR4Dn/Q.",employeeRole));
    }

}
