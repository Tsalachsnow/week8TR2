package com.example.week4tr1.services;

import com.example.week4tr1.model.UserInfo;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    Integer LOGIN_STATUS_ACTIVE=1;
    Integer LOGIN_STATUS_BLOCKED=2;

    Integer ROLE_ADMIN=1;
    Integer ROLE_USER=2;

    boolean createUser(UserInfo user);

    void delete(UserInfo user);

    void delete(Long Id);

//    UserInfo login(String loginName, String password) throws UserBlockedException;
//
//
//    public List<UserInfo> getUserList();
//
//
//    public void changeLoginStatus(Integer userId, Integer loginStatus);
//
//
     Boolean isUsernameExist(String username);

    UserInfo getUserByEmail(String email);


}

