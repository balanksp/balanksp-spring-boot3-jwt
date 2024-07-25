package com.TeleApps.spring_boot3_jwt_loginPage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TeleApps.spring_boot3_jwt_loginPage.dto.AuthRequest;
import com.TeleApps.spring_boot3_jwt_loginPage.dto.Product;
import com.TeleApps.spring_boot3_jwt_loginPage.entity.UserInformation;
import com.TeleApps.spring_boot3_jwt_loginPage.response.UserResponse;
import com.TeleApps.spring_boot3_jwt_loginPage.service.JwtService;
import com.TeleApps.spring_boot3_jwt_loginPage.service.UserProService;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    @Autowired
    private UserProService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/testing")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    // @PostMapping("/new")
    // public String addNewUser(@RequestBody UserInformation userInfo) {
    // return service.addUser(userInfo);
    // }

    @PostMapping(value = "/new", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> registerNewUser(@RequestBody UserInformation userInfo) {
        UserResponse responseMsg = service.addUser(userInfo);
        // Map<String, String> response = new HashMap<>();
        // response.put("message", response);
        return ResponseEntity.status(responseMsg.getStatus()).body(responseMsg.getMessage());
        // return
        // ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<Product>> getAllTheProducts() {
        List<Product> listOfProducts = service.getProducts();
        return ResponseEntity.status(HttpStatus.OK).body(listOfProducts);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Product product = service.getProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
                        authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

    @PostMapping("/Register")
    public ResponseEntity<UserInformation> addNewUser(@RequestBody UserInformation userInfo) {
        UserInformation result = service.regUser(userInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}
