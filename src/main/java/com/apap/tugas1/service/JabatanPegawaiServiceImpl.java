package com.apap.tugas1.service;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.JabatanPegawaiModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.repository.JabatanPegawaiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class JabatanPegawaiServiceImpl implements  JabatanPegawaiService{

    @Autowired
    private JabatanPegawaiDb jabatanPegawaiDb;

    @Override
    public Optional<List<JabatanPegawaiModel>> getJabatanByNip(String nip){
        return jabatanPegawaiDb.findAllByPegawai_Nip(nip);
    }

    @Override
    public Optional<List<JabatanPegawaiModel>> getJabatanPegawai(long idJabatan){
        return jabatanPegawaiDb.findJabatanPegawaiModelByJabatanId(idJabatan);
    }
}
