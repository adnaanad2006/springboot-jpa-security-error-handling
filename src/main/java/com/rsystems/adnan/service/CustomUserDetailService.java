package com.rsystems.adnan.service;

import com.rsystems.adnan.model.CustomUserDetails;
import com.rsystems.adnan.model.User;
import com.rsystems.adnan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByName(username);

        User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found."));

        return new CustomUserDetails(user);
    }
}
