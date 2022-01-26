package com.example.week4tr1.controllers;

import com.example.week4tr1.model.Contact;
import com.example.week4tr1.model.UserInfo;
import com.example.week4tr1.services.serviceImplementation.ContactServiceImpl;
import com.example.week4tr1.services.serviceImplementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
public class ContactController {

   private final ContactServiceImpl contactService;
   private final UserServiceImpl userService;

    @Autowired
    public ContactController(ContactServiceImpl contactService, UserServiceImpl userService) {
        this.contactService = contactService;

        this.userService = userService;
    }



    @GetMapping(value = "/user/contact_form")
    public String contactForm(Model m) {
        Contact contact = new Contact();
        m.addAttribute("command", contact);
        return "contact_form";//JSP form view
    }

    @GetMapping(value = "/user/save_contact/{contactId}")
    public String prepareEditForm(@PathVariable (value="contactId") Long contactId, Model m, HttpSession session) {
        session.setAttribute("aContactId", contactId);
        Contact c = contactService.findById(contactId);
        m.addAttribute("command", c);
        return "edit";//JSP form view
    }

    @GetMapping("/userDash")
    public String getDashboard() {
        return "dashboard_user";
    }

    @PostMapping(value = "/user/save_contact")
    public String saveOrUpdateContact(@ModelAttribute("command") Contact c, Model m, HttpSession session) {

        Contact contact = contactService.findContactByEmail(c.getEmail());

        UserInfo user = (UserInfo) session.getAttribute("user");
        System.out.println(user);
        if (contact == null) {
            //save task

            Contact newContact = new Contact();
            newContact.setName(c.getName());
            newContact.setEmail(c.getEmail());
            newContact.setPhone(c.getPhone());
            newContact.setRemark(c.getRemark());
            newContact.setAddress(c.getAddress());
            newContact.setUserInfo(user);

                contactService.save(newContact);
                m.addAttribute("Success1", "contact successfully added");
                return "redirect:/userDash";}//redirect user to contact list url

                m.addAttribute("err", "Failed to save contact");
                return "/contact_form";
    }



    @PostMapping("/user/save_contact/{id}")
    public String saveOrUpdateContact1(@PathVariable("id") Long id, @ModelAttribute("command") Contact c, Model m, HttpSession session){
//        Long contactId = (Long) session.getAttribute("ContactId");
        System.out.println("Update request: " + c);
        Contact contact = contactService.findById(id);

        if (contact!= null) {
            contact.setName(c.getName());
            contact.setEmail(c.getEmail());
            contact.setPhone(c.getPhone());
            contact.setAddress(c.getAddress());
            contact.setRemark(c.getRemark());
            contactService.save(contact);
            System.out.println("Contact of id: " + contact.getContactId() + " was updated!");
            return "dashboard_user";
        }

        m.addAttribute("err", "Failed to Edit contact");
        return "/edit";
    }

    @GetMapping(value = "/admin/clist/{userId}")
    public String contactList(@PathVariable("userId") Long userId1, Model m, HttpSession session) {
        Contact contact = new Contact();

            m.addAttribute("contactList", contactService.findUserContact());
            m.addAttribute("contact", contact);
            m.addAttribute("userId", userId1);
            return "admin/clist"; //JSP

    }

    @GetMapping(value = "/user/clist")
    public String cotactList1(Model m, HttpSession session) {
        Long id = (Long)session.getAttribute("id");
        m.addAttribute("contactList1", contactService.findUserContact());
        return "user/clist"; //JSP
    }

    @GetMapping(value = "/user/slist{id}")
    public String contactList1(@PathVariable("id") Model m, HttpSession session) {;
        UserInfo user = (UserInfo) session.getAttribute("user");
        String freeText = (String)session.getAttribute("freeText");
        m.addAttribute("contactList1", contactService.findd(freeText));
        return "user/slist"; //JSP
    }

    @GetMapping("/contact_search/freeText")
    public String contactSearch(Model model, @RequestParam(value = "freeText") String freeText) {
        List<Contact> listContact = contactService.findd(freeText);
        model.addAttribute("listContact", listContact);
            model.addAttribute("freeText", freeText);

        return "user/slist";
    }

    @GetMapping(value = "/user/del_contact/{id}")
    public String deleteContact1(@PathVariable("id") Long id,HttpSession session) {

////        UserInfo person = (UserInfo) session.getAttribute("person");
//        if(person.getLoginName().equalsIgnoreCase(user1.getLoginName()))
            contactService.deleteContact(id);
        session.setAttribute("message", "contact deleted successfully");
        return "redirect:/user/clist";
    }


    @GetMapping(value = "/user/bulk_cdelete")
    public String deleteBulkContact() {
        contactService.deleteAll();
        return "redirect:/user/clist";
    }

    @DeleteMapping(value = "/admin/del_contact/{id}")
    public String deleteContact(@PathVariable("id") Long id,HttpSession session) {

        UserInfo person = (UserInfo) session.getAttribute("person");


//        Long contactId = Long.parseLong(request.getParameter("contactId"));

        contactService.deleteContact(id);
        session.setAttribute("message", "contact deleted successfully");
        return "redirect:/admin/clist";
    }

    @DeleteMapping(value = "/admin/bulk_cdelete")
    public String deleteBulkContact1() {
        System.out.println("got here");
        contactService.deleteAll();
        return "admin/clist";
    }
}
