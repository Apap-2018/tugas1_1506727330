package com.apap.tugas1.service;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.repository.InstansiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InstansiServiceImpl implements InstansiService{

    @Autowired
    private InstansiDb instansiDb;

    @Override
    public Optional<List<InstansiModel>> getInstansiByProvinsi(String namaProvinsi){
        return instansiDb.findAllByProvinsi_Nama(namaProvinsi);
    }

    @Override
    public List<InstansiModel> getAllInstansi(){
        return instansiDb.findAll();
    }
}
