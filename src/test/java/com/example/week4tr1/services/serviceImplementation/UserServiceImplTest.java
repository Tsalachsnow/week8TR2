package com.example.week4tr1.services.serviceImplementation;

import com.example.week4tr1.model.UserInfo;
import com.example.week4tr1.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    private UserInfo userInfo;

    @BeforeEach
    void setUp() {
        userInfo = new UserInfo();
        userInfo.setName("emeka");
        userInfo.setPhone("08135139753");
        userInfo.setPassword("er0swccd-snow");
        userInfo.setEmail("snowshaddy@gmail.com");
        userInfo.setAddress("22 mushin moshalashi str");
        userInfo.setLoginName("snow");
        userInfo.setRole(1);
        userInfo.setLoginStatus(1);


    }

    @Test
    void createUser() {

        when(userRepository.save(any(UserInfo.class))).thenReturn(userInfo);

        //call the method to be tested

        userServiceImpl.createUser(userInfo);

        verify(userRepository, times(1)).save(any(UserInfo.class));
    }

    @Test
    void getById() {
        when(userRepository.getById(anyLong())).thenReturn(userInfo);
        UserInfo user = userServiceImpl.getUserById(1L);
        assertNotNull(user);
        assertEquals(userInfo, user);
        verify(userRepository, times(1)).getById(anyLong());
    }

    @Test
    void getUserByEmail() {
        when(userRepository.findUsersByEmail(anyString())).thenReturn(userInfo);
        UserInfo user = userServiceImpl.getUserByEmail("amalahaprosper@gmail.com");
        assertEquals(userInfo, user);
        assertNotNull(user);
        verify(userRepository, times(1)).findUsersByEmail(anyString());
    }
}