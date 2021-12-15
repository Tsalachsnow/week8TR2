package com.example.week4tr1.services.serviceImplementation;

import com.example.week4tr1.model.Contact;
import com.example.week4tr1.model.UserInfo;
import com.example.week4tr1.repositories.ContactRepository;
import com.example.week4tr1.rm.ContactRowMapper;
import com.example.week4tr1.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {

    private ContactRepository contactRepository;

    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;

    }

    @Override
    public void save(Contact c) {
        contactRepository.save(c);
    }

    @Override
    public void update(Contact c) {
        contactRepository.update(c);
    }

    @Override
    public void delete(Contact cotactId) {

    contactRepository.delete(cotactId);
    }

    @Override
    public void deleteAll(Contact cotactIds) {
    contactRepository.deleteAll((Iterable<? extends Contact>) cotactIds);
    }

    @Override
    public List<Contact> findUserContact(Long contactId) {
        return contactRepository.findAll();
    }

    @Override
    public List<Contact> findUserContact(Long userId, String txt) {
        return contactRepository.findUserContact("txt");
    }


    @Override
    public Contact findById(Long cotactId) {
        return contactRepository.findById(cotactId).get();
    }

}


// public Optional<Contact> findById(Long contactId) {
     //   return contactRepository.findById(contactId);
  //  }

//    @Override
//    public Contact findUserContact(Long userId) {
//        return contactRepository.findById(userId).get();
//    }
//
//
//    public void save(Contact c) {
//    contactRepository.save(c);
//    }
//
////    @Override
////    public void update(UserInfo userInfo, Long contactId, String name, String phone, String email, String address, String remark) {
////    contactRepository.update();
////
////    }
//
//    public void update(Contact c) {
//    contactRepository.update(c);
//    }
//
////    public List<Contact> findContactByProperty(String userId, Long userId1, String freeText) {
//////    return contactRepository.findContactByProperty("userId", userId, freeText);
////        return contactRepository.findContactByProperty(userId, userId1, freeText);
////    }
//
//
//    public void delete(Contact contactId) {
//    contactRepository.delete(contactId);
//    }
//
//
//    @Override
//    public void deleteAll(Contact contact) {
//    contactRepository.deleteAll();
//    }

