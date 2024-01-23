package com.example.gestioncatalogue.security.service;

import com.example.gestioncatalogue.security.Repository.UserRepository;
import com.example.gestioncatalogue.security.entities.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class UserDetailsServiceImp implements UserDetailsService {
    IAccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user= accountService.loadUserByUserName(username);
        List<GrantedAuthority> authorityList= new ArrayList<>();
        user.getRoles().forEach(e -> authorityList.add(new SimpleGrantedAuthority(e.getRole())));
        return new User(user.getUsername(),user.getPasssword(),authorityList);
    }
}
