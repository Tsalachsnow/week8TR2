package com.example.week4tr1.controllers;

import com.example.week4tr1.exception.UserBlockedException;
import com.example.week4tr1.model.Contact;
import com.example.week4tr1.model.UserInfo;
import com.example.week4tr1.services.ContactService;
import com.example.week4tr1.services.UserService;
import com.example.week4tr1.services.serviceImplementation.ContactServiceImpl;
import com.example.week4tr1.services.serviceImplementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.Optional;

import static com.example.week4tr1.services.UserService.ROLE_ADMIN;
import static com.example.week4tr1.services.UserService.ROLE_USER;

@Controller
public class UserController {

    private final UserServiceImpl userService;

    private final ContactServiceImpl contactService;

    @Autowired
    public UserController(UserServiceImpl userService, ContactServiceImpl contactService) {
        this.userService = userService;
        this.contactService = contactService;
    }

    private void addUserInSession(UserInfo userInfo, HttpSession session) {
        session.setAttribute("user", userInfo);
        session.setAttribute("userId", userInfo.getId());
        session.setAttribute("role", userInfo.getRole());
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView showRegister(HttpServletRequest request) {

        HttpSession session = request.getSession();
        ModelAndView model = new ModelAndView("index");
        model.addObject("user", new UserInfo());
        

        return model;
    }

    @PostMapping(value = "/login")
    public String login(@ModelAttribute("user") UserInfo userInfo, HttpSession session, Model m) throws UserBlockedException {
        UserInfo userVal = userService.getUserByEmail(userInfo.getEmail());
        if (userVal == null) {
            m.addAttribute("invalid", "email does not exist, please register or check email");
            return "index";
        }
        if (Objects.equals(userVal.getLoginStatus(), UserService.LOGIN_STATUS_BLOCKED)) {
            throw new UserBlockedException("Your account has been blocked. Contact to admin.");

        } else if (Objects.equals(userVal.getRole(), ROLE_ADMIN)) {
            //add user detail in session (assign session to logged in user)
            addUserInSession(userVal, session);
            return "dashboard_admin";
        } else if (Objects.equals(userVal.getRole(), UserService.ROLE_USER)) {
            //add user detail in session (assign session to logged in user)
            addUserInSession(userVal, session);
            return "dashboard_user";
        } else {
            //add error message and go back to login-form
            m.addAttribute("err", "Invalid User ROLE");
            return "index";//JSP - Login FORM
        }

    }

    @GetMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:index?act=lo";
    }

    @GetMapping(value = "/user/dashboard")
    public String userDashboard(){
        return "dashboard_user"; //JSP
    }

    @GetMapping(value = "/admin/dashboard")
    public String adminDashboard() {
        return "dashboard_admin"; //JSP
    }

    @GetMapping(value = "/admin/ulist")
    public String getUserList(Model m) {
        m.addAttribute("userList", userService.getUserList());
        System.out.println("===============");
        return "admin/ulist"; //JSP
    }

    @GetMapping(value = "/reg_form")
    public String getRegistrationFormPage(Model m) {
        m.addAttribute("userinfo", new UserInfo());
        return "reg_form";//JSP
    }

    @PostMapping(value = "/reg_form")
    public String registerUser(@ModelAttribute UserInfo userInfo, Model m) {
        System.out.println("Code reached here");
        UserInfo userdata = userService.getUserByEmail(userInfo.getEmail());
        if (userdata == null) {
//                UserInfo user = new UserInfo();
            System.out.println("And here");
            userInfo.setRole(UserService.ROLE_USER);
            userInfo.setLoginStatus(UserService.LOGIN_STATUS_ACTIVE);
            userService.createUser(userInfo);
            System.out.println("========");
            return "redirect:index";

        }
        //Login Page
        m.addAttribute("err", "Username is already registered. Please select another username.");
        return "reg_form";//JSP
    }

//    @GetMapping("/admin/ulist")
//    public String getDashboard1(Model m) {
//        m.addAttribute("userList", userService.getUserList());
//        return "admin/ulist";
//    }

//    @GetMapping(value = "/admin/change_status")
//    @ResponseBody
//    public String changeLoginStatus1(@ModelAttribute UserInfo userInfo, Model m) {
////           userService.changeStatus(loginStatus);
//        UserInfo userdata = userService.getUserById(userInfo.getId());
//        if (userdata != null)
//            if (userInfo.getRole() == ROLE_ADMIN)
//                m.addAttribute("err", "you are the Admin");
//            if (userInfo.getRole() == ROLE_USER) {
//                userService.changeLoginStatus(userdata.getId(), userdata.getLoginStatus());
//                return "admin/ulist";
//            }
//            m.addAttribute("err", "Failed to Change status");
//            return "admin/ulist";
//
//        }

    @GetMapping("/admin/change_status/{id}")
    public String changeLoginStatus(Model m, HttpSession session, @PathVariable(value = "id") Long id, @RequestBody UserInfo loginStatus) {
//        UserInfo user = (UserInfo) session.getAttribute("user");
        UserInfo existLoginStatus = userService.getUserById(id);
        if (existLoginStatus.getRole() == ROLE_ADMIN)
            m.addAttribute("err", "you are the Admin");
        if (existLoginStatus.getRole() == ROLE_USER){
            existLoginStatus.setLoginStatus(loginStatus.getLoginStatus());
            UserInfo updated_loginStatus = userService.changeLoginStatus(existLoginStatus);
//       userService.changeLoginStatus(Long.parseLong(id), user.getLoginStatus());
            return "admin/ulist";
        }
        m.addAttribute("err", "Failed to Change status");
        return "admin/ulist";
    }

//



        @GetMapping(value = "/check_avail/username")
        @ResponseBody
        public String checkAvailability(@RequestParam(value = "username") String username) {
            if(userService.isUsernameExist(username)){
                return "This username is already taken. Choose another name";
            }else{
                return "Yes! You can take this";
            }
        }

    @RequestMapping(value = "/admin/del_user/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response, HttpSession session) {

        UserInfo person = (UserInfo) session.getAttribute("person");


//        Long contactId = Long.parseLong(request.getParameter("contactId"));

        userService.deleteUser(id);
        session.setAttribute("message", "contact deleted successfully");
        return "redirect:/admin/ulist";
    }

    @GetMapping(value = "/admin/bulk_udelete")
    public String deleteBulkContact() {
        contactService.deleteAll();
        return "admin/ulist";
    }

    }

