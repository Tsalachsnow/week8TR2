package com.example.week4tr1.appCreator;

import com.example.week4tr1.model.UserInfo;
import com.example.week4tr1.repositories.UserRepository;
import com.example.week4tr1.utils.PasswordHashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Admin implements ApplicationRunner {

    private UserRepository userRepository;

     @Autowired
    public Admin(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

   String pass = PasswordHashing.encryptPassword("12345");
    @Override
    public void run(ApplicationArguments args) throws Exception {
        UserInfo userInfo = new UserInfo(1L, "Prosper", "08135139753", "snowshaddy@gmail.com", "decagon", "snowkill", pass, 1, 1);
        userRepository.save(userInfo);
    }
}
