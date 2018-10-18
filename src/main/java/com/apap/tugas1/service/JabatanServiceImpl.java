package com.apap.tugas1.service;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.repository.JabatanDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class JabatanServiceImpl implements JabatanService{

    @Autowired
    private JabatanDb jabatanDb;

    @Override
    public List<JabatanModel> getAllJabatan(){
        return jabatanDb.findAll();
    }

    @Override
    public void addJabatan(JabatanModel jabatan){
        jabatanDb.save(jabatan);
    }

    @Override
    public Optional<JabatanModel> getJabatanById(long idJabatan){
        return jabatanDb.findById(idJabatan);
    }
}
