package com.example.gestioncatalogue.security.service;

import com.example.gestioncatalogue.security.entities.AppUser;

public interface IAccountService {

    public void addRole(String role);
    public void addUser(String userName, String password, String mail);
    public void addRoleToUser(String userName,String role);
    public AppUser loadUserByUserName(String userName);
}
