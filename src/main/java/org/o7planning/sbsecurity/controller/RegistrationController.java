package org.o7planning.sbsecurity.controller;


import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.o7planning.sbsecurity.model.AppUser;
import org.o7planning.sbsecurity.service.UserService;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView register(){
        ModelAndView modelAndView = new ModelAndView();
        AppUser user = new AppUser();
        modelAndView.addObject("title", "Registration");
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registrationPage");
        return modelAndView;
    }
    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid AppUser user, BindingResult bindingResult) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        AppUser userExists = userService.findUserByUserName(user.getUserLogin());

        if (userExists != null) {
            bindingResult
                    .rejectValue("username", "error.user",
                            "User with the same login already exists");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registrationPage");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "Successful registration");
            modelAndView.addObject("user", new AppUser());
            modelAndView.setViewName("registrationPage");
        }
        return modelAndView;
    }
}