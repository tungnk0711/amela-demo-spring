package com.codegym.controller;

import com.codegym.exception.CustomerNotFoundException;
import com.codegym.exception.DuplicateLastNameException;
import com.codegym.model.Customer;
import com.codegym.model.CustomerForm;
import com.codegym.model.Items;
import com.codegym.model.Province;
import com.codegym.service.ICustomerService;
import com.codegym.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/customer")
@PropertySource("classpath:global_config_app.properties")
public class CustomerController {

    @Autowired
    private Environment env;


    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IProvinceService provinceService;

    @ModelAttribute("_provinces")
    public List<Province> provinces(){

        List<Province> _provinces = provinceService.findAll();
        return _provinces;
    }

    @GetMapping("")
    public ModelAndView findAll() {

        List<Customer> customers = customerService.findAll();
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView findById(@PathVariable("id") Long id) throws CustomerNotFoundException {

        Customer customer = customerService.findCustomerById(id);
        ModelAndView modelAndView = new ModelAndView("/customer/detail");
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customerForm", new CustomerForm());
        return modelAndView;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "html/css;charset=UTF-8")
    public ModelAndView save(@ModelAttribute("customerForm") CustomerForm customerForm, BindingResult bindingResult){

        String fileUpload = env.getProperty("file_upload").toString();

        if(bindingResult.hasErrors()){
            ModelAndView modelAndView = new ModelAndView("/error/404");
            return modelAndView;
        }

        MultipartFile multipartFile = customerForm.getAvatar();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(customerForm.getAvatar().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Customer customer = new Customer(customerForm.getFirstName(), customerForm.getLastName(), customerForm.getProvince(), fileName);

        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customerForm", new CustomerForm());
        modelAndView.addObject("message", "Thêm mới khách hàng thành công!");
        return modelAndView;

    }

    @ExceptionHandler(DuplicateLastNameException.class)
    public ModelAndView showInputNotAcceptable() {

        return new ModelAndView("/customer/inputs-not-acceptable");
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ModelAndView findCustomerNotFound() {

        return new ModelAndView("/error/404");
    }

}
