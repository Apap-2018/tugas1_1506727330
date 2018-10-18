package com.apap.tugas1.controller;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.JabatanPegawaiModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.repository.JabatanPegawaiDb;
import com.apap.tugas1.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PegawaiController {

    @Autowired
    private PegawaiService pegawaiService;

    @Autowired
    private JabatanPegawaiService jabatanPegawaiService;

    @Autowired
    private ProvinsiService provinsiService;

    @Autowired
    private InstansiService instansiService;

    @Autowired
    private JabatanService jabatanService;

    @RequestMapping(value = "/pegawai",method = RequestMethod.GET)
    private String getPegawai(@RequestParam(value = "nip") String nipPegawai, Model model){
        PegawaiModel pegawai = pegawaiService.getPegawaiDetailByNip(nipPegawai).get();
        List<JabatanPegawaiModel> ListJabatanPegawai = jabatanPegawaiService.getJabatanByNip(nipPegawai).get();
        double gaji = 0.0;
        for (JabatanPegawaiModel jabatanPegawai : ListJabatanPegawai) {
            double tempGaji = jabatanPegawai.getJabatan().getGajiPokok();
            if (tempGaji > gaji){
                gaji = tempGaji;
            }
        }
        gaji += pegawai.getInstansi().getProvinsi().getPresentaseTunjangan()/100 * gaji;
        model.addAttribute("pegawai",pegawai);
        model.addAttribute("listJabatanPegawai",ListJabatanPegawai);
        model.addAttribute("gaji",(long)gaji);
        return "pegawai";
    }

    @RequestMapping(value = "/pegawai/tambah")
    private String addPegawai(Model model){
        ObjectMapper objectMapper = new ObjectMapper();
        model.addAttribute("pegawai", new PegawaiModel());
        model.addAttribute("listOfProvinsi", provinsiService.getAllProvince());
        model.addAttribute("listOfInstansi", instansiService.getInstansiByProvinsi("ACEH").get());
        model.addAttribute("listOfJabatan", jabatanService.getAllJabatan());
        return "add-pegawai";
    }

    @RequestMapping(value = "/instansi", method = RequestMethod.GET)
    public @ResponseBody
    List<InstansiModel> findAllInstansi(
            @RequestParam(value = "namaProvinsi", required = true) String namaProvinsi) {
        return instansiService.getInstansiByProvinsi(namaProvinsi).get();
    }
}
