package com.david.demo.customer;

import java.util.List;
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

/**
 *
 */
@Controller
public class CustomerViewController {

    @Autowired
    private Validator validator;

    private final CustomerService customerService;

    public CustomerViewController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = "/customers")
    public String showCustomersList(Model model) {
        List<Customer> all = customerService.getAll();
        model.addAttribute("customerList", all);
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "customerList";
    }

    @GetMapping(value = "/customer/registration")
    public String showRegistrationForm(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "customerList";
    }

    @RequestMapping(value = "/customer/registration", method = RequestMethod.POST)
    public String registerUserAccount(
            @ModelAttribute("customer") CustomerTO customerTO,
            BindingResult result,
            ModelMap modelMap) {

        Customer customer = new Customer();
        customer.setFirstName(customerTO.getFirstName());
        customer.setLastName(customerTO.getLastName());
        customer.setAge(customerTO.getAge());

        Set<ConstraintViolation<Customer>> validationResult = validator.validate(customer);
        if (!validationResult.isEmpty()) {
            throw new ConstraintViolationException(validationResult);
        }
        Customer registered = customerService.addNew(customer);
        if (registered == null) {
            result.rejectValue("email", "message.regError");
        } else {
            modelMap.addAttribute("customer", registered);
            List<Customer> all = customerService.getAll();
            modelMap.addAttribute("customerList", all);
            return "customerList";
        }
        return null;
    }
}
