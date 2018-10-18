package com.apap.tugas1.controller;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.JabatanPegawaiModel;
import com.apap.tugas1.service.JabatanPegawaiService;
import com.apap.tugas1.service.JabatanService;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.templateparser.text.CSSTemplateParser;

@Controller
public class JabatanController {
    @Autowired
    private JabatanService jabatanService;

    @Autowired
    private JabatanPegawaiService jabatanPegawaiService;

    @RequestMapping(value = "/jabatan/tambah", method = RequestMethod.GET)
    private String index(Model model){
        model.addAttribute("jabatan", new JabatanModel());
        return "add-jabatan";
    }

    @RequestMapping(value = "/jabatan/tambah", method = RequestMethod.POST)
    private String addDealerSubmit(@ModelAttribute JabatanModel jabatan, Model model){
        System.out.println(jabatan.getNama());
        System.out.println(jabatan.getDeskripsi());
        System.out.println(jabatan.getGajiPokok());
        jabatanService.addJabatan(jabatan);

        return "tambah";
    }

    @RequestMapping(value = "/jabatan/view", method = RequestMethod.GET)
    private String viewJabatan(@RequestParam(value = "idJabatan") String idJabatan, Model model){
        long idJabatanLong = Long.parseLong(idJabatan);
        JabatanModel jabatan =  jabatanService.getJabatanById(idJabatanLong).get();
        model.addAttribute("jabatan", jabatan);
        return "view-jabatan";
    }

    @RequestMapping(value = "/jabatan/ubah", method = RequestMethod.GET)
    private String updateJabatan(@RequestParam(value = "idJabatan") String idJabatan, Model model){
        long idJabatanLong = Long.parseLong(idJabatan);
        JabatanModel jabatan =  jabatanService.getJabatanById(idJabatanLong).get();
        model.addAttribute("jabatan", jabatan);
        return "update-jabatan";
    }

    @RequestMapping(value = "/jabatan/ubah", method = RequestMethod.POST)
    private String updateJabatan(@ModelAttribute JabatanModel jabatan, Model model){
        JabatanModel jabatanNow = jabatanService.getJabatanById(jabatan.getId()).get();
        jabatanNow.setNama(jabatan.getNama());
        jabatanNow.setDeskripsi(jabatan.getDeskripsi());
        jabatanNow.setGajiPokok(jabatan.getGajiPokok());
        jabatanService.addJabatan(jabatan);

        return "update";
    }

    @RequestMapping(value = "/jabatan/hapus", method = RequestMethod.POST)
    private String deleteJabatan(@ModelAttribute JabatanModel jabatan, Model model){
        JabatanModel jabatanNow = jabatanService.getJabatanById(jabatan.getId()).get();
        System.out.println(jabatanPegawaiService.getJabatanPegawai(jabatan.getId()));
        if (jabatanPegawaiService.getJabatanPegawai(jabatan.getId()).isPresent()){
            return "delete-failed";
        }
        jabatanService.deleteJabatan(jabatanNow);

        return "delete";
    }
}
