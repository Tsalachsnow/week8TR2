package com.example.week4tr1.services.serviceImplementation;

import com.example.week4tr1.model.Contact;
import com.example.week4tr1.model.UserInfo;
import com.example.week4tr1.repositories.ContactRepository;
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
class ContactServiceImplTest {

    @Mock
    private ContactRepository contactRepository;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ContactServiceImpl contactServiceImpl;



    Contact contact;
    UserInfo userInfo;

    @BeforeEach
    void setUp() {

        contact = new Contact();
        contact.setName("snow shadow");
        contact.setPhone("07017509066");
        contact.setEmail("snowshaddy@gmail.com");
        contact.setAddress("22 adeolu str");
        contact.setRemark("nil");

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
    void save() {
        when(contactRepository.save(any(Contact.class))).thenReturn(contact);

        //call the method to be tested

        contactServiceImpl.save(contact);

        verify(contactRepository, times(1)).save(any(Contact.class));
    }

    @Test
    void deleteContact() {
    }

    @Test
    void findById() {
        when(contactRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(contact));
        Contact contact = contactServiceImpl.findById(1L);
        assertNotNull(contact);
        assertEquals(contact, contact);
        verify(contactRepository, times(1)).findById(anyLong());

    }
}