package com.apap.tugas1.repository;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.ProvinsiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstansiDb extends JpaRepository<InstansiModel,Long> {
    Optional<List<InstansiModel>> findAllByProvinsi_Nama(String namaProvinsi);
}
