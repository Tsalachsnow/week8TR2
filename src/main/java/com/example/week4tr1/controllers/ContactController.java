package com.example.week4tr1.controllers;

import com.example.week4tr1.model.Contact;
import com.example.week4tr1.model.UserInfo;
import com.example.week4tr1.services.ContactService;
import com.example.week4tr1.services.serviceImplementation.ContactServiceImpl;
import com.example.week4tr1.services.serviceImplementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
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
        System.out.println("Code reached here");
        Long contactId = (Long) session.getAttribute("ContactId");
        if (contactId == null) {
            //save task
            System.out.println("Code reached here1");

                Long userId = (Long) session.getAttribute("userId");
                c.setContactId(userId);//FK ; logged in userId
                System.out.println("Code reached here2");
                contactService.save(c);
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

    @GetMapping(value = "/admin/clist")
    public String contactList(Model m, HttpSession session) {
        Long userId = (Long)session.getAttribute("userId");
        m.addAttribute("contactList", contactService.findUserContact(userId));
        return "admin/clist"; //JSP
    }

    @PostMapping(value = "/user/contact_search/{freeText}")
    public String contactSearch(Model m, HttpSession session, @PathVariable String freeText) {
        Long contactId = (Long) session.getAttribute("contactId");
        m.addAttribute("freeText", freeText);
        Contact contact = new Contact();
            m.addAttribute("contact", contact);
        m.addAttribute("contactList", contactService.findContactsByNameContains(freeText));

        System.out.println("Returned results: " + contactService.findContactsByNameContains(freeText));
        return "user/slist"; //JSP
//        String regex = "0";
//
//        try{
//            long x = Long.parseLong(freeText);
//            m.addAttribute("contactList", contactService.searchPhone(Long.toString(x)));
//        }catch(NumberFormatException n){
//            m.addAttribute("contactList", contactService.searchText(freeText));
//        }
////        m.addAttribute("contactList", contactService.findUserContact1(contactId, freeText));
//
//        return "user/clist"; //JSP
    }

//    @RequestParam("cid") Contact contactId,
    @RequestMapping(value = "/admin/del_contact/{id}", method = RequestMethod.GET)
    public String deleteContact(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response, HttpSession session) {

        UserInfo person = (UserInfo) session.getAttribute("person");


//        Long contactId = Long.parseLong(request.getParameter("contactId"));

        contactService.deleteContact(id);
            session.setAttribute("message", "contact deleted successfully");
        return "redirect:/user/clist";
    }

    @RequestMapping(value = "/user/del_contact", method = RequestMethod.GET)
    public String deleteContact1(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response, HttpSession session) {

        UserInfo person = (UserInfo) session.getAttribute("person");


//        Long contactId = Long.parseLong(request.getParameter("contactId"));

        contactService.deleteContact(id);
        session.setAttribute("message", "contact deleted successfully");
        return "user/clist";
    }

    @GetMapping(value = "/user/bulk_cdelete")
    public String deleteBulkContact() {
        contactService.deleteAll();
        return "user/clist";
    }

    @GetMapping(value = "/admin/bulk_cdelete")
    public String deleteBulkContact1() {
        System.out.println("got here");
        contactService.deleteAll();
        return "admin/clist";
    }
}
