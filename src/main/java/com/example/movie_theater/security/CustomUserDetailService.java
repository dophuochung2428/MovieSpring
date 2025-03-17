package com.example.movie_theater.security;

import com.example.movie_theater.entities.Role;
import com.example.movie_theater.entities.User;
import com.example.movie_theater.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRole()));

    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(Role roles){
        String roleName = roles.getName().startsWith("ROLE_") ? roles.getName() : "ROLE_" + roles.getName();
        return Collections.singletonList(new SimpleGrantedAuthority(roleName));
    }
}
