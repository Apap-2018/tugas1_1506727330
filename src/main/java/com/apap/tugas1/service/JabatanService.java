package com.apap.tugas1.service;

import com.apap.tugas1.model.JabatanModel;

import java.util.List;
import java.util.Optional;

public interface JabatanService {
    List<JabatanModel> getAllJabatan();
    void addJabatan(JabatanModel jabatan);

    Optional<JabatanModel> getJabatanById(long idJabatan);
}
