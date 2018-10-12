package com.apap.tugas1.controller;

import com.apap.tugas1.service.PegawaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

public class PegawaiController {

    @Autowired
    private PegawaiService pegawaiService;

    @RequestMapping(value = "/")
    private String index(Model model){
        return "index";
    }
}
