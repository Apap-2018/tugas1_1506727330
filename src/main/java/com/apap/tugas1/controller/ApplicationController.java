package com.apap.tugas1.controller;

import com.apap.tugas1.service.JabatanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApplicationController {

    @Autowired
    private JabatanService jabatanService;

    @RequestMapping(value = "/")
    private String index(Model model){
        model.addAttribute("listOfJabatan", jabatanService.getAllJabatan());
        return "index";
    }
}
