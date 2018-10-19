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

    @RequestMapping(value = "/pegawai/termuda-tertua",method = RequestMethod.GET)
    private String viewYoungestAndOldestPegawai(@RequestParam(value = "idInstansi")String idInstansi, Model model){
        long idInstansiLong = Long.parseLong(idInstansi);
        List<PegawaiModel> filteredInstansiPegawai = pegawaiService.getAllPegawaiByIdInstansi(idInstansiLong);
        filteredInstansiPegawai.sort(
                (pegawai1, pegawai2) -> {return pegawai1.getTanggalLahir().compareTo(pegawai2.getTanggalLahir());}
        );
        PegawaiModel youngestPegawai = filteredInstansiPegawai.get(filteredInstansiPegawai.size()-1);
        PegawaiModel oldestPegawai = filteredInstansiPegawai.get(0);
        List<JabatanPegawaiModel> listOfYoungestJabatan = jabatanPegawaiService.getJabatanByNip(youngestPegawai.getNip()).get();
        List<JabatanPegawaiModel> listOfOldestJabatan = jabatanPegawaiService.getJabatanByNip(oldestPegawai.getNip()).get();
        model.addAttribute("youngestPegawai",youngestPegawai);
        model.addAttribute("oldestPegawai",oldestPegawai);
        model.addAttribute("listOfYoungestJabatanPegawai",listOfYoungestJabatan);
        model.addAttribute("listOfOldestJabatanPegawai",listOfOldestJabatan);
        return "youngest-oldest-pegawai";
    }
}
