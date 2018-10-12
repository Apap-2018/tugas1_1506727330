package com.apap.tugas1.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "jabatan_pegawai")
public class JabatanPegawaiModel {

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

}