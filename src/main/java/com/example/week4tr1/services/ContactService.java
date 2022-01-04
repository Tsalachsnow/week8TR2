package com.example.week4tr1.services;

import com.example.week4tr1.model.Contact;

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

    List<Contact> findUserContact();



    List<Contact> findd(String freeText);

    Contact findById(Long cotactId);

}


