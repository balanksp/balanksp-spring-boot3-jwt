package com.TeleApps.spring_boot3_jwt_loginPage.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.TeleApps.spring_boot3_jwt_loginPage.dto.Product;
import com.TeleApps.spring_boot3_jwt_loginPage.entity.UserInformation;
import com.TeleApps.spring_boot3_jwt_loginPage.repository.UserInfoRepo;

import jakarta.annotation.PostConstruct;

@Service
public class UserProService {

    List<Product> productList = null;

    @Autowired
    private UserInfoRepo repo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostConstruct
    public void totalProducts() {
        productList = IntStream.rangeClosed(1, 100)
                .mapToObj(i -> Product.builder()
                        .productId(i)
                        .name("product " + i)
                        .quanity(new Random().nextInt(5000))
                        .price(new Random().nextInt(10)).build())
                .collect(Collectors.toList());
    }

    public String addUser(UserInformation userInfo) {
        // userInfo.setPassword(userInfo.getPassword());
        Optional<UserInformation> existingUser = repo.findByName(userInfo.getName());
        if (existingUser.isPresent()) {
            return "Username already exists. User not added.";
        } else {
            userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
            System.out.println("user successfully added");
            repo.save(userInfo);
            return "user successfully added";
        }
    }

    public List<Product> getProducts() {
        return productList;
    }

    public Product getProduct(int id) {
        return productList.stream()
                .filter(product -> product.getProductId() == id)
                .findAny()
                .orElseThrow(() -> new RuntimeException("product " + id + " not found"));
    }

}
