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
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
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

    @RequestMapping(value = "/pegawai/tambah", method = RequestMethod.POST)
    private String submitAddPegawai(@ModelAttribute PegawaiModel pegawai, Model model){
        String kodeInstansi = String.valueOf(pegawai.getInstansi().getId());

        String tanggalLahir = pegawai.getTanggalLahir().toString();
        String hariLahir = tanggalLahir.substring(8,10);
        String bulanLahir = tanggalLahir.substring(5,7);
        String tahunLahir = tanggalLahir.substring(2,4);
        tanggalLahir = hariLahir+bulanLahir+tahunLahir;

        String tahunBekerja = pegawai.getTahunMasuk();

        int jumlahPegawaiSama = pegawaiService.getAllByInstansiAndTahunMasukAndTanggalLahir(pegawai).size();
        int urutan = jumlahPegawaiSama+1;
        String kodeUrut = "";
        if (urutan/10 < 1){
            kodeUrut = "0"+ urutan;
        }
        else {
            kodeUrut = String.valueOf(urutan);
        }
        pegawai.setNip(kodeInstansi + tanggalLahir + tahunBekerja + kodeUrut);
        System.out.println(pegawaiService.getAllByInstansiAndTahunMasukAndTanggalLahir(pegawai).size());
        pegawaiService.addPegawai(pegawai);
        for (JabatanPegawaiModel jabatanPegawai : pegawai.getJabatanPegawaiModelList()) {
            jabatanPegawai.setPegawai(pegawai);
            jabatanPegawaiService.addJabatanPegawai(jabatanPegawai);
        }
        return "redirect:http://localhost:8080/pegawai?nip="+pegawai.getNip();
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

    @RequestMapping(value = "/pegawai/ubah", method = RequestMethod.GET)
    private String addPegawai(@RequestParam(value = "nip") String nip,Model model){
        PegawaiModel pegawai = pegawaiService.getPegawaiDetailByNip(nip).get();
        model.addAttribute("pegawai", pegawai);
        model.addAttribute("listOfProvinsi", provinsiService.getAllProvince());
        model.addAttribute("listOfInstansi", instansiService.getAllInstansi());
        model.addAttribute("listOfJabatan", jabatanService.getAllJabatan());
        return "update-pegawai";
    }


}
