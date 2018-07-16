package com.david.demo.social;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.david.demo.errorHandling.EmailExistsException;
import com.david.demo.user.UserDTO;
import com.david.demo.user.UserService;
import com.david.demo.user.UserSocialDetails;

@Controller
public class LoginController {

    @Autowired
    FacebookProvider facebookProvider;

    @Autowired
    UserService userService;

    public LoginController(@Qualifier("loggedUser") UserDTO loggedUser) {
        this.loggedUser = loggedUser;
    }

    private UserDTO loggedUser;

    @RequestMapping(value = "/facebook", method = RequestMethod.GET)
    public String loginToFacebook(Model model) {
        return facebookProvider.getFacebookUserData(model, new UserDTO());
    }

    @GetMapping(value = {"/", "/login"})
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/user/registration", method = RequestMethod.POST)
    public ModelAndView registerUserAccount(
            @ModelAttribute("user") UserDTO accountDto,
            BindingResult result,
            ModelMap modelMap) throws EmailExistsException {
        UserDTO registered = userService.registerNewUserAccount(accountDto);
        if (registered == null) {
            result.rejectValue("email", "message.regError");
            System.out.println("UZIVATE NEPRIDAAAAAAAAAAAAAAAAAAAAAAAAAAAAN");
        } else {
            modelMap.addAttribute("user", accountDto);
            return new ModelAndView("successRegister", "user", accountDto);
        }

        return null;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginUserAccount(Model model,
                                   @ModelAttribute("user") UserDTO userDTO, BindingResult result) throws EmailExistsException {
        setAsLoggedIn(userService.loginUserAccount(userDTO.getUsername(), userDTO.getPassword()));
        if (loggedUser == null) {
            result.rejectValue("email", "message.regError");
            System.out.println("UZIVATE NEPIHLAAASEN");
        }
        System.out.println("Prihlaseni uspesne jako: " + loggedUser);
        model.addAttribute("user", loggedUser);
        return "hello";
    }

    private void setAsLoggedIn(UserDTO newLoggedUser) {
        loggedUser.setFirstName(newLoggedUser.getFirstName());
        loggedUser.setLastName(newLoggedUser.getLastName());
        loggedUser.setEmail(newLoggedUser.getEmail());
        loggedUser.setGender(newLoggedUser.getGender());
        loggedUser.setUsername(newLoggedUser.getUsername());
        loggedUser.setUserOrigin(newLoggedUser.getUserOrigin());
        loggedUser.setAuthorities(newLoggedUser.getAuthorities());
        loggedUser.setPassword(newLoggedUser.getPassword());
        loggedUser.setRole(newLoggedUser.getRole());
        loggedUser.setId(newLoggedUser.getId());
    }
}