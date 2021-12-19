package org.o7planning.sbsecurity.controller;


import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.o7planning.sbsecurity.model.AppUser;
import org.o7planning.sbsecurity.dao.AppUserDAO;
import org.o7planning.sbsecurity.mapper.AppUserMapper;
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
        modelAndView.addObject("user", userLogin);
        modelAndView.setViewName("registration");
        return modelAndView;
    }
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid AppUser user, BindingResult bindingResult) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        AppUser userExists = AppUserDAO.findUserAccount(user.getUsername());
//        User userExists = user;
        if (userExists != null) {
            bindingResult
                    .rejectValue("username", "error.user",
                            "Пользователь с таким именем уже существует");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "Пользователь зарегистрирован");
            modelAndView.addObject("user", new AppUser());
            modelAndView.setViewName("registration");

        }
        return modelAndView;
    }
}