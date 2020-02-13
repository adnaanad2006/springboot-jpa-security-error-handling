package com.rsystems.adnan.resource;

import com.rsystems.adnan.exception.UserNotFoundException;
import com.rsystems.adnan.model.User;
import com.rsystems.adnan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloResource {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/all")
    public String getAll() {
        return "Hello All Open";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/secured/all")
    public String getSecuredAll() {
        return "Hello All Secured";
    }

    @GetMapping("/secured/youtube")
    public String getSecuredYourube() {
        return "Hello youtube Secured";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/secured/users")
    public ResponseEntity<List<User>> getSecuredUsers() {
        return new ResponseEntity<List<User>>(userRepository.findAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/secured/users/{id}")
    public ResponseEntity<List<User>> getSecuredUser(@PathVariable("id") String id) {
        throw new UserNotFoundException("User Not found");
    }
}
