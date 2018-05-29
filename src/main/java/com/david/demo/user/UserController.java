package com.david.demo.user;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.validation.metadata.ConstraintDescriptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    UserService service;

    @Autowired
    MessageSource messageSource;

    @Autowired
    Validator validator;

    @GetMapping(value = "/user/registration")
    public String showRegistrationForm(Model model) {
        UserDTO userDto = new UserDTO();
        model.addAttribute("user", userDto);
        return "registration";
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
            ModelMap modelMap) {
        Set<ConstraintViolation<UserDTO>> validationResult = validator.validate(accountDto);
        if (!validationResult.isEmpty()) {
            throw new ConstraintViolationException(validationResult);
        }
        System.out.println("INSIDE registerUserAccount POST");
        User registered = new User();
        if (!result.hasErrors()) {
            registered = createUserAccount(accountDto, result);
        }
        if (registered == null) {
            result.rejectValue("email", "message.regError");
        }
        if (result.hasErrors()) {
            System.out.println("has ERRORS");

            result.getAllErrors().stream().forEach(objectError -> {
                System.out.println(objectError);
                System.out.println(objectError.getObjectName());
                System.out.println(objectError.getCode());
                System.out.println(objectError.getCodes());
                ConstraintViolation constraintViolation = new ConstraintViolation() {
                    @Override
                    public String getMessage() {
                        return null;
                    }

                    @Override
                    public String getMessageTemplate() {
                        return null;
                    }

                    @Override
                    public Object getRootBean() {
                        return null;
                    }

                    @Override
                    public Class getRootBeanClass() {
                        return null;
                    }

                    @Override
                    public Object getLeafBean() {
                        return null;
                    }

                    @Override
                    public Object[] getExecutableParameters() {
                        return new Object[0];
                    }

                    @Override
                    public Object getExecutableReturnValue() {
                        return null;
                    }

                    @Override
                    public Path getPropertyPath() {
                        return null;
                    }

                    @Override
                    public Object getInvalidValue() {
                        return null;
                    }

                    @Override
                    public ConstraintDescriptor<?> getConstraintDescriptor() {
                        return null;
                    }

                    @Override
                    public Object unwrap(Class aClass) {
                        return null;
                    }
                };
                //throw new ConstraintViolationException(result.getAllErrors());
            } );
            return new ModelAndView("registration", "user", accountDto);
        }
        else {
            System.out.println("successRegister");
            modelMap.addAttribute("user", accountDto);
            return new ModelAndView("successRegister", "user", accountDto);
        }
    }
    private User createUserAccount(UserDTO accountDto, BindingResult result) {
        User registered = null;
        try {
            registered = service.registerNewUserAccount(accountDto);
        } catch (EmailExistsException e) {
            return null;
        }
        return registered;
    }
}
