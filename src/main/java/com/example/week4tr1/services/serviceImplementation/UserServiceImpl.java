package com.example.week4tr1.services.serviceImplementation;

import com.example.week4tr1.model.UserInfo;
import com.example.week4tr1.repositories.UserRepository;
import com.example.week4tr1.services.UserService;
import com.example.week4tr1.utils.PasswordHashing;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private UserInfo userInfo;
//    @Autowired
//    public UserServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//
//    }


    @Override
    public boolean createUser(UserInfo user) {
        boolean flag = false;
//        UserInfo userdata = new UserInfo();
        user.setPassword(PasswordHashing.encryptPassword(user.getPassword()));
       UserInfo userdata = userRepository.findUsersByEmail(user.getEmail());
        if (userdata == null) {
            System.out.println(user);
            userRepository.save(user);
            flag = true;
        }
        return flag;
    }

    @Override
    public void delete(UserInfo user) {

    }

    @Override
    public void delete(Long Id) {

    }

    public UserInfo getUserByEmail(String email) {
        return userRepository.findUsersByEmail(email);

    }

    public Object getUserList() {

        return userRepository.findAll();
    }

    public void changeLoginStatus(Long id, Integer loginStatus) {
//        UserInfo user =  userRepository.findByIdAndLoginStatus(id, loginStatus);
        UserInfo user = userRepository.findByIdAndLoginStatus(id, loginStatus);
        user.setId(id);
        user.setLoginStatus(loginStatus);
        userRepository.save(user);
    }

    public UserInfo getUserById(Long userId) {
        return userRepository.getById(userId);
    }

    @Override
    public Boolean isUsernameExist(String username) {
        if (userRepository.findByLoginName(username)) return true;
        else{
            return false;
        }
    }
}
