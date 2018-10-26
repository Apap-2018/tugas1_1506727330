package com.apap.tugas1.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "jabatan_pegawai")
public class JabatanPegawaiModel {

    public void setId(long id) {
        this.id = id;
    }

    public void setPegawai(PegawaiModel pegawai) {
        this.pegawai = pegawai;
    }

    public void setJabatan(JabatanModel jabatan) {
        this.jabatan = jabatan;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pegawai",referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private PegawaiModel pegawai;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_jabatan",referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private JabatanModel jabatan;

    public long getId() {
        return id;
    }

    public PegawaiModel getPegawai() {
        return pegawai;
    }

    public JabatanModel getJabatan() {
        return jabatan;
    }
}
