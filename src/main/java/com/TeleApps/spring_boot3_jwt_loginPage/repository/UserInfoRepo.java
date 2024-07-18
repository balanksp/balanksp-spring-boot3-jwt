package com.TeleApps.spring_boot3_jwt_loginPage.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TeleApps.spring_boot3_jwt_loginPage.entity.UserInformation;

public interface UserInfoRepo extends JpaRepository<UserInformation, Integer> {
    Optional<UserInformation> findByName(String username);
}
