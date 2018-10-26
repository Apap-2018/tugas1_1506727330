package com.apap.tugas1.service;

import com.apap.tugas1.model.PegawaiModel;

import java.util.List;
import java.util.Optional;

public interface PegawaiService {
    Optional<PegawaiModel> getPegawaiDetailByNip(String nip);
    void addPegawai(PegawaiModel pegawai);
    List<PegawaiModel> getAllPegawai();

    List<PegawaiModel> getAllPegawaiByIdInstansi(long idInstansiLong);

    List<PegawaiModel> getAllByInstansiAndTahunMasukAndTanggalLahir(PegawaiModel pegawai);
}
