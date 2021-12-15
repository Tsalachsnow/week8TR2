package com.example.week4tr1.services;

import com.example.week4tr1.model.Contact;
import com.example.week4tr1.model.UserInfo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface ContactService {
//    void save(Contact contact);
//
//    void update(Contact contact);
//
////    void update(UserInfo userInfo, Long contactId, String name, String phone, String email, String address, String remark);
//
//    void deleteAll(Contact contact);
//
//    void delete(Contact contactId);
//
//    Optional<Contact> findById(Long contactId);
//
//    Contact findUserContact(Long userId);

//    List<Contact> findContactByProperty(String userId, Long userId1, String freeText);

//    List<Contact> findUserContact(Long userId, String freeText);

//    Object findUserContact(Long userId);
//   " ======================================="


    void save(Contact c);

    void update(Contact c);

     void delete(Contact cotactId);

    void deleteAll(Contact cotactIds);

    List<Contact> findUserContact(Long userId);


    List<Contact> findUserContact(Long userId, String txt);

    Contact findById(Long cotactId);
}


