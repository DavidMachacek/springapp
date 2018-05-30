package com.david.demo.user;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.david.demo.errorHandling.EmailExistsException;

@Controller
public class UserViewController {

    @Autowired
    UserService userService;

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
        List<User> all = userService.getAll();
        System.out.println(all.size());
        all.forEach(System.out::println);
        model.addAttribute("customerList", all);
        return "customerList";
    }

    @GetMapping(value = "/user/test")
    public String showRegistrationTest(Model model) {
        UserDTO userDto = new UserDTO();
        model.addAttribute("user", userDto);
        ResourceBundle messages = ResourceBundle.getBundle("messages", Locale.ENGLISH);
        return messages.getString("label.form.title");
    }

    @RequestMapping(value = "/user/registration", method = RequestMethod.POST)
    public ModelAndView registerUserAccount(
            @ModelAttribute("user") UserDTO accountDto,
            BindingResult result,
            ModelMap modelMap) throws EmailExistsException {

        Set<ConstraintViolation<UserDTO>> validationResult = validator.validate(accountDto);
        if (!validationResult.isEmpty()) {
            throw new ConstraintViolationException(validationResult);
        }

        User registered = userService.registerNewUserAccount(accountDto);
        if (registered == null) {
            result.rejectValue("email", "message.regError");
        } else {
            modelMap.addAttribute("user", accountDto);
            return new ModelAndView("successRegister", "user", accountDto);
        }
        return null;
    }
}
