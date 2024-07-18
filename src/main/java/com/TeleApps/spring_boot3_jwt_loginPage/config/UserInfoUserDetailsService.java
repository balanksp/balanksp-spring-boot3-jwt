package com.TeleApps.spring_boot3_jwt_loginPage.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.TeleApps.spring_boot3_jwt_loginPage.entity.UserInformation;
import com.TeleApps.spring_boot3_jwt_loginPage.repository.UserInfoRepo;
import com.TeleApps.spring_boot3_jwt_loginPage.service.JwtService;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserInfoRepo repository;

    @Autowired
    JwtService jwtService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<UserInformation> userInfo = repository.findByName(username);
        return userInfo.map(UserInfoUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));

    }

}
