package com.apap.tugas1.service;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.repository.JabatanDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class JabatanServiceImpl implements JabatanService{

    @Autowired
    private JabatanDb jabatanDb;

    @Override
    public List<JabatanModel> getAllJabatan(){
        return jabatanDb.findAll();
    }
}
