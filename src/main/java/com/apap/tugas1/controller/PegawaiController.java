package com.apap.tugas1.controller;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.JabatanPegawaiModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.repository.JabatanPegawaiDb;
import com.apap.tugas1.service.JabatanPegawaiService;
import com.apap.tugas1.service.PegawaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PegawaiController {

    @Autowired
    private PegawaiService pegawaiService;

    @Autowired
    private JabatanPegawaiService jabatanPegawaiService;

    @RequestMapping(value = "/")
    private String index(Model model){
        return "index";
    }

    @RequestMapping(value = "/pegawai",method = RequestMethod.GET)
    private String getPegawai(@RequestParam(value = "nip") String nipPegawai, Model model){
        PegawaiModel pegawai = pegawaiService.getPegawaiDetailByNip(nipPegawai).get();
        List<JabatanPegawaiModel> ListJabatanPegawai = jabatanPegawaiService.getJabatanByNip(nipPegawai).get();
        Double gaji = 0.0;
        for (JabatanPegawaiModel jabatanPegawai : ListJabatanPegawai) {
            gaji += jabatanPegawai.getJabatan().getGajiPokok();
        }
        model.addAttribute("pegawai",pegawai);
        model.addAttribute("listJabatanPegawai",ListJabatanPegawai);
        model.addAttribute("gaji",gaji);
        return "pegawai";
    }

}
