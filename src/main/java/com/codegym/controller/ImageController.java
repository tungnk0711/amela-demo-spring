package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.CustomerForm;
import com.codegym.model.Image;
import com.codegym.model.ImageForm;
import com.codegym.service.ICustomerService;
import com.codegym.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/image")
@PropertySource("classpath:global_config_app.properties")
public class ImageController {

    @Autowired
    private Environment env;

    @Autowired
    private IImageService imageService;

    @GetMapping("")
    public ModelAndView findAll() {
        List<Image> images = imageService.findAll();
        ModelAndView modelAndView = new ModelAndView("/image/list");
        modelAndView.addObject("images", images);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/image/create");
        modelAndView.addObject("imageForm", new ImageForm());
        return modelAndView;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute("imageForm") ImageForm imageForm, BindingResult bindingResult){

        String fileUpload = env.getProperty("file_upload").toString();

        if(bindingResult.hasErrors()){
            ModelAndView modelAndView = new ModelAndView("/error/404");
            return modelAndView;
        }

        MultipartFile[] multipartFile = imageForm.getFileName();
        String fileNames[] = new String[multipartFile.length];
        for (int i=0; i< multipartFile.length;i++){
            fileNames[i] = multipartFile[i].getOriginalFilename();
        }

        try {
            for (int i=0; i< fileNames.length; i++) {
                FileCopyUtils.copy(imageForm.getFileName()[i].getBytes(), new File(fileUpload + fileNames[i]));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        for(int i=0; i< fileNames.length; i++) {
            Image image = new Image(fileNames[i]);
            imageService.save(image);
        }

        ModelAndView modelAndView = new ModelAndView("/image/create");
        modelAndView.addObject("imageForm", new ImageForm());
        modelAndView.addObject("message", "Thêm mới thành công!");
        return modelAndView;

    }

}
