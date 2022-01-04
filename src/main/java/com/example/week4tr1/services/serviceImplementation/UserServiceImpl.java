package com.example.week4tr1.services.serviceImplementation;

import com.example.week4tr1.model.UserInfo;
import com.example.week4tr1.repositories.UserRepository;
import com.example.week4tr1.services.UserService;
import com.example.week4tr1.utils.PasswordHashing;
import lombok.RequiredArgsConstructor;
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

    public List<UserInfo> getUserList() {

        return userRepository.findAll();
    }
    @Override
    public void deleteUser(Long userId){
        userRepository.deleteById(userId);
    }

    public UserInfo changeLoginStatus(UserInfo loginStatus) {
        UserInfo user = userRepository.findByIdAndLoginStatus(loginStatus);
        if(loginStatus != null && loginStatus.equals(LOGIN_STATUS_ACTIVE))
            user.setLoginStatus(LOGIN_STATUS_BLOCKED);
        if(loginStatus != null && loginStatus.equals(LOGIN_STATUS_BLOCKED))
        user.setLoginStatus(LOGIN_STATUS_ACTIVE);
        userRepository.save(user);

        return user;
    }

    public UserInfo getUserById(Long userId) {
        return userRepository.getById(userId);
    }

    @Override
    public Boolean isUsernameExist(String username) {
        if (userRepository.findByLoginName(username)){
            return true;
        } else{
            return false;
        }
    }

    public void addLogin(UserInfo loginStatus) {
        userRepository.save(loginStatus);
    }


//    @Override
//    public void changeStatus(int loginStat) {
//        int loginStatus = 0;
//        if(loginStat == 1) {
//            loginStatus = 2;
//        } else {
//            loginStatus = 1;
//        }
//    }
}
