package com.example.week4tr1.services.serviceImplementation;

import com.example.week4tr1.model.Contact;
import com.example.week4tr1.model.UserInfo;
import com.example.week4tr1.repositories.ContactRepository;
import com.example.week4tr1.repositories.UserRepository;
import com.example.week4tr1.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {

    private ContactRepository contactRepository;
    private UserRepository userRepository;


    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository, UserRepository userRepository) {
        this.contactRepository = contactRepository;
        this.userRepository = userRepository;
    }


    @Override
    public void save(Contact c) {

        contactRepository.save(c);
    }


//    contactRepository.delete(cotactId);

//    @Override
//    public  deleteContact(Long contactId) {
//        boolean status = false;
//        Contact contact = contactRepository.findContactByContactIdAndUserId(contactId);
//        if(contact != null) {
//            contactRepository.delete(contact);
//            status = true;
//        }
//        return status;
//
//    }
    @Override
    public void deleteContact(Long contactId){
        contactRepository.deleteById(contactId);
    }

    @Override
    public void deleteAll() {
    contactRepository.deleteAll();
    }

    @Override
    public List<Contact> findUserContact() {
        return contactRepository.findAll();
    }

    @Override
    public List<Contact> findd(String freeText) {
        if (freeText != null) {
            System.err.println(contactRepository.findContactsByNameOrPhoneOrEmailOrAddressOrRemark(freeText));
            return contactRepository.findContactsByNameOrPhoneOrEmailOrAddressOrRemark(freeText);
        }
        return contactRepository.findAll();
    }

    @Override
    public Contact findById(Long contactId) {

        return contactRepository.findById(contactId).orElse(null);
    }

    public Optional<UserInfo> findUserContact1(Long id) {
       return userRepository.findById(id);
    }

    public Contact findContactByEmail(String email) {
        return contactRepository.findByEmail(email).orElse(null);
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

