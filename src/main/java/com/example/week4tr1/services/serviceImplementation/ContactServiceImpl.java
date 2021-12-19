package com.example.week4tr1.services.serviceImplementation;

import com.example.week4tr1.model.Contact;
import com.example.week4tr1.model.UserInfo;
import com.example.week4tr1.repositories.ContactRepository;
import com.example.week4tr1.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Contact> findUserContact(Long contactId) {
        return contactRepository.findAll();
    }
    @Override
    public List<Contact> findContactsByNameContains(String freeText){
        return contactRepository.findContactsByNameContains(freeText);
    }

    @Override
    public List<Contact> findd(String text) {
        System.err.println(contactRepository.findContactsByNameOrPhoneOrEmailOrAddressOrRemarkContains(text));
        return contactRepository.findContactsByNameOrPhoneOrEmailOrAddressOrRemarkContains(text);
    }

//    @Override
//    public List<Contact> findUserContact1(Long contactId, String txt) {
//        System.err.println( contactRepository.findUserContact1(txt));
//        return contactRepository.findUserContact1(txt);
//    }



    @Override
    public Contact findById(Long contactId) {
        return contactRepository.findById(contactId).orElse(null);
    }
    @Override
    public List<Contact> findAll(Long userId, String freeText) {
        return contactRepository.findAll();
    }

    @Override
    public List<Contact> searchText(String txt) {
        return contactRepository.findContactsByNameContains(txt);
    }

    @Override
    public List<Contact> searchPhone(String txt) {
        return contactRepository.findContactsByPhoneContains(txt);
    }

    public void delete(Contact contactId) {
    }


//    public boolean saveOrUpdateContact1(Long contactId, String name, String email, String address, String phone, String remark) {
//    }
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

