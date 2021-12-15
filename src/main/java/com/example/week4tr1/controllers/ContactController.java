package com.example.week4tr1.controllers;

import com.example.week4tr1.model.Contact;
import com.example.week4tr1.services.ContactService;
import com.example.week4tr1.services.serviceImplementation.ContactServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class ContactController {

   private final ContactServiceImpl contactService;

    @Autowired
    public ContactController(ContactServiceImpl contactService) {
        this.contactService = contactService;

}

    @GetMapping(value = "/user/contact_form")
    public String contactForm(Model m) {
        Contact contact = new Contact();
        m.addAttribute("command", contact);
        return "contact_form";//JSP form view
    }

    @GetMapping(value = "/user/edit_contact")
    public String prepareEditForm(Model m, HttpSession session, @RequestParam("cid") Long contactId) {
        session.setAttribute("aContactId", contactId);
        Optional<Contact> c = Optional.ofNullable(contactService.findById(contactId));
        m.addAttribute("command", c);
        return "contact_form";//JSP form view
    }

    @PostMapping(value = "/user/save_contact/{contactId}")
    public String saveOrUpdateContact(@ModelAttribute("command") Contact c, Model m, HttpSession session) {
        System.out.println("Code reached here");
        Long contactId = (Long) session.getAttribute("ContactId");
        if (contactId == null) {
            //save task
            System.out.println("Code reached here1");
            try {
                Long userId = (Long) session.getAttribute("userId");
                c.setContactId(userId);//FK ; logged in userId
                System.out.println("Code reached here2");
                contactService.save(c);
                return "clist";//redirect user to contact list url
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Code reached here3");
                m.addAttribute("err", "Failed to save contact");
                return "contact_form";
            }
        } else {
            //update task
            try {
                System.out.println("Code reached here4");
                c.setContactId(contactId); //PK
                contactService.update(c);
                session.removeAttribute("aContactId");
                System.out.println("Code reached here5");
                return "clist";//redirect user to contact list url
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Code reached here6");
                m.addAttribute("err", "Failed to Edit contact");
                return "contact_form";
            }
        }
    }

    @GetMapping(value = "/user/clist")
    public String contactList(Model m, HttpSession session) {
        Long userId = (Long)session.getAttribute("userId");
        m.addAttribute("contactList", contactService.findUserContact(userId));
        return "clist"; //JSP
    }

    @PostMapping(value = "/user/contact_search")
    public String contactSearch(Model m, HttpSession session, @RequestParam("freeText") String freeText) {
        Long userId = (Long) session.getAttribute("userId");
        m.addAttribute("contactList", contactService.findUserContact(userId, freeText));
        return "clist"; //JSP
    }

    @GetMapping(value = "/user/del_contact/{contactId}")
    public String deleteContact(@RequestParam("cid") Contact contactId) {
        contactService.delete(contactId);
        return "redirect:clist";
    }

    @GetMapping(value = "/user/bulk_cdelete/{contactIds}")
    public String deleteBulkContact(@RequestParam("cid") Contact contactIds) {
        contactService.delete(contactIds);
        return "redirect:clist";
    }
}
