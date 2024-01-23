package com.example.gestioncatalogue.security.service;

import com.example.gestioncatalogue.security.Repository.RoleRepository;
import com.example.gestioncatalogue.security.Repository.UserRepository;
import com.example.gestioncatalogue.security.entities.AppRole;
import com.example.gestioncatalogue.security.entities.AppUser;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.rmi.server.UID;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class AccountService implements IAccountService{
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public void addRole(String role) {
        roleRepository.save(AppRole.builder().role(role).build());
    }

    @Override
    public void addUser(String userName, String password, String mail) {
        AppUser user=userRepository.findAppUserByUsername(userName);
                if(user!=null) throw new RuntimeException("user existe");
        userRepository.save(AppUser.builder()
                        .username(userName)
                        .passsword(passwordEncoder.encode(password))
                        .mail(mail)
                        .id(UUID.randomUUID().toString())
                .build());

    }

    @Override
    public void addRoleToUser(String userName, String role) {
        AppUser user=userRepository.findAppUserByUsername(userName);
        AppRole rol=roleRepository.findById(role).orElse(null);
        user.getRoles().add(rol);


    }

    @Override
    public AppUser loadUserByUserName(String userName) {
        return userRepository.findAppUserByUsername(userName);
    }
}
