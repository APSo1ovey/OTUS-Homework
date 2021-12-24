package org.o7planning.sbsecurity.controller;

import java.security.Principal;

import org.o7planning.sbsecurity.model.AppUser;
import org.o7planning.sbsecurity.service.UserService;
import org.o7planning.sbsecurity.utils.WebUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class MainController {
    private final UserService userService;
    public MainController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
    public String welcomePage(Model model) {

        return "welcomePage";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);

        return "adminPage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {

        return "loginPage";
    }

    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }
    /*
        @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
        public String userInfo(Model model, Principal principal) {

            String userName = principal.getName();

            System.out.println("User Name: " + userName);

            User loginedUser = (User) ((Authentication) principal).getPrincipal();

            String userInfo = WebUtils.toString(loginedUser);
            model.addAttribute("userInfo", userInfo);

            return "userInfoPage";
        }
    */
        @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
        public ModelAndView userInfo() throws Exception {
            ModelAndView modelAndView = new ModelAndView();
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            AppUser user = userService.findUserByUserName(auth.getName());
            modelAndView.addObject("currentUser", user);
            modelAndView.addObject("title", "userInfo");
            modelAndView.setViewName("userInfoPage");

            return modelAndView;
        }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();

            String userInfo = WebUtils.toString(loginedUser);

            model.addAttribute("userInfo", userInfo);

            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);

        }

        return "403Page";
    }

}