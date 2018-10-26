package com.apap.tugas1.service;

import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.repository.PegawaiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PegawaiServiceImpl implements  PegawaiService{

    @Autowired
    private PegawaiDb pegawaiDb;

    @Override
    public Optional<PegawaiModel> getPegawaiDetailByNip(String nip){
        return  pegawaiDb.findByNip(nip);
    }

    @Override
    public void addPegawai(PegawaiModel pegawai){
        pegawaiDb.save(pegawai);
    }

    @Override
    public List<PegawaiModel> getAllPegawai(){
        return pegawaiDb.findAll();
    }

    @Override
    public List<PegawaiModel> getAllPegawaiByIdInstansi(long idInstansi){
        return pegawaiDb.findAllByInstansiId(idInstansi);
    }

    @Override
    public List<PegawaiModel> getAllByInstansiAndTahunMasukAndTanggalLahir(PegawaiModel pegawai){
        return pegawaiDb.findAllByInstansiAndTahunMasukAndTanggalLahir(pegawai.getInstansi(), pegawai.getTahunMasuk(), pegawai.getTanggalLahir());
    }
}
