package com.apap.tugas1.service;

import com.apap.tugas1.model.PegawaiModel;

import java.util.List;
import java.util.Optional;

public interface PegawaiService {
    Optional<PegawaiModel> getPegawaiDetailById(Long id);
    void addPegawai(PegawaiModel pegawai);
    List<PegawaiModel> getAllPegawai();
}
