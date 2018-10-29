package com.apap.tugas1.service;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.JabatanPegawaiModel;

import java.util.List;
import java.util.Optional;

public interface JabatanPegawaiService{
    Optional<List<JabatanPegawaiModel>> getJabatanByNip(String nip);
    Optional<List<JabatanPegawaiModel>> getJabatanPegawai(long idJabatan);

    void addJabatanPegawai(JabatanPegawaiModel jabatanPegawai);

    void deleteJabatanPegawai(JabatanPegawaiModel jabatanPegawai);
}
