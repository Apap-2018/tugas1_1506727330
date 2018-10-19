package com.apap.tugas1.controller;

import com.apap.tugas1.service.InstansiService;
import com.apap.tugas1.service.JabatanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApplicationController {

    @Autowired
    private JabatanService jabatanService;

    @Autowired
    private InstansiService instansiService;

    @RequestMapping(value = "/")
    private String index(Model model){
        model.addAttribute("listOfJabatan", jabatanService.getAllJabatan());
        model.addAttribute("listOfInstansi", instansiService.getAllInstansi());
        return "index";
    }
}
