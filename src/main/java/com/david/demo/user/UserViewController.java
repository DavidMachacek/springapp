package com.david.demo.user;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.validation.Validator;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.PostData;
import org.springframework.social.facebook.api.User;
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

@Controller
public class UserViewController {

    private final UserMapper mapper = UserMapper.INSTANCE;
    @Autowired
    UserServiceImpl userService;

    @Autowired
    Validator validator;

    @GetMapping(value = "/user/registration")
    public String showRegistrationForm(Model model) {
        UserDTO userDto = new UserDTO();
        model.addAttribute("user", userDto);
        return "registration";
    }

    @GetMapping(value = "/users")
    public String showUsersList(Model model) {
        List<UserDTO> all = userService.getAll();
        System.out.println(all.size());
        all.forEach(System.out::println);
        model.addAttribute("userList", all);
        return "userList";
    }

    @GetMapping(value = "/user/test")
    public String showRegistrationTest(Model model) {
        UserDTO userDto = new UserDTO();
        model.addAttribute("user", userDto);
        ResourceBundle messages = ResourceBundle.getBundle("messages", Locale.ENGLISH);
        return messages.getString("label.form.title");
    }
}
