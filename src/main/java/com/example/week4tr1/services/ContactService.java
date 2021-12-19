package com.example.week4tr1.services;

import com.example.week4tr1.model.Contact;
import com.example.week4tr1.model.UserInfo;

import java.util.List;


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

//    void update(Contact c);

//     void delete(Contact cotactId);

    void deleteContact(Long contactId);

    void deleteAll();

    List<Contact> findUserContact(Long userId);


//    List<Contact> findUserContact1(Long userId, String txt);

//    List<Contact> findUserContact1(Long contactId, String txt);

    List<Contact> findContactsByNameContains(String freeText);

    List<Contact> findd(String text);

//    List<Contact> findUserContact1(Long contactId, String txt);

    Contact findById(Long cotactId);

    List<Contact> findAll(Long userId, String freeText);

    List<Contact> searchText(String txt);

    List<Contact> searchPhone(String txt);
}


