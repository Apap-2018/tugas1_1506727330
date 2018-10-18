package com.apap.tugas1.service;

import com.apap.tugas1.model.ProvinsiModel;

import java.util.List;
import java.util.Optional;

public interface ProvinsiService {
    List<ProvinsiModel> getAllProvince();
    Optional<ProvinsiModel> getProvinsiDetailById(int id);
}
