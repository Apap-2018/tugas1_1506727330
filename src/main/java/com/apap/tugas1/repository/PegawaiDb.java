package com.apap.tugas1.repository;

import com.apap.tugas1.model.PegawaiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PegawaiDb extends JpaRepository<PegawaiModel,Long> {
    Optional<PegawaiModel> findByNip(String nip);
    List<PegawaiModel> findAllByInstansiId(long idInstansi);
}
