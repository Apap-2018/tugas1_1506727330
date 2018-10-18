package com.apap.tugas1.repository;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.JabatanPegawaiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JabatanPegawaiDb extends JpaRepository<JabatanPegawaiModel,Long> {
    Optional<List<JabatanPegawaiModel>> findAllByPegawai_Nip(String nip);
    Optional<List<JabatanPegawaiModel>> findJabatanPegawaiModelByJabatanId(long idJabatan);
}
