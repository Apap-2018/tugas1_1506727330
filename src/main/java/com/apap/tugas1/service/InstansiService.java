package com.apap.tugas1.service;

import com.apap.tugas1.model.InstansiModel;

import java.util.List;
import java.util.Optional;

public interface InstansiService {
    Optional<List<InstansiModel>> getInstansiByProvinsi(String namaProvinsi);
    List<InstansiModel> getAllInstansi();
}
